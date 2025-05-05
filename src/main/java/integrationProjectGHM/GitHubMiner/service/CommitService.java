package integrationProjectGHM.GitHubMiner.service;

import integrationProjectGHM.GitHubMiner.model.commit.Commit;
import integrationProjectGHM.GitHubMiner.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static integrationProjectGHM.GitHubMiner.util.RestTemplateHelper.getResponseEntity;
import static integrationProjectGHM.GitHubMiner.util.RestTemplateHelper.getResponseEntityById;

@Service
public class CommitService {
    @Autowired
    RestTemplate restTemplate;
// Recomendable la creación de un constructor
    @Autowired
    public CommitService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    @Value("${github.baseuri}")
    private String baseUri; // El valor de baseUrl se inyecta desde el archivo application.properties gracias al @Value

    // Añadimos la posibilidad de poder usar tokens para aumentar el límite de peticiones
    @Value("${github.token}")
    private String token;


    // A partir de aquí comenzamos a definir métodos para cada operación que queramos hacer con la API de GitHub para los Commits
    public ResponseEntity<Commit[]> getAllCommits (String owner, String repo){
        String uri = baseUri + owner + "/" + repo + "/commits";
        return getResponseEntity(restTemplate, uri, token, Commit[].class);
    }

    public ResponseEntity<Commit> getCommitById (String owner, String repo, String id){
        String uri = baseUri + owner + "/" + repo + "/commits/" + id;
        return getResponseEntityById(restTemplate, uri, token, Commit.class);
    }

    // Implementamos la función que equivale a "sinceCommits": Devuelve los commits enviados los últimos X días
    // GET https://api.github.com/repos/{owner}/{repo}/commits?since={ISO-8601-datetime}
    public List<Commit> getAllCommitsSince(String owner, String repoName, Integer updatedSinceDays, Integer maxPages) throws HttpClientErrorException {
        List<Commit> commits = new ArrayList<>();
        int pagesNumber = (maxPages != null) ? maxPages : 2; // Default: 2 páginas

        String initialUrl = String.format("%s%s/%s/commits?since=%s",
                baseUri,
                owner,
                repoName,
                OffsetDateTime.now(ZoneOffset.UTC)
                        .minusDays(updatedSinceDays != null ? updatedSinceDays : 2)
                        .toString()
        );

        String nextPageUrl = initialUrl;
        int currentPage = 1;

        while (nextPageUrl != null && currentPage <= pagesNumber) {
            System.out.println("Fetching page " + currentPage + ": " + nextPageUrl); // debug improvisado

            ResponseEntity<Commit[]> response = getResponseEntity(restTemplate, nextPageUrl, token, Commit[].class);
            commits.addAll(Arrays.asList(response.getBody()));

            // Aquí es donde la URL devuelta por GitHub puede contener `after=...`
            nextPageUrl = Util.getNextPageUrl(response.getHeaders()); //Aquí no hace falta que usemos el "cleanPaginationUrl" porque "commits" no trata con "cursors"
            currentPage++;
        }

        return commits;
    }
}
