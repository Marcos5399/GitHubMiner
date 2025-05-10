package integrationProjectGHM.GitHubMiner.service;

import integrationProjectGHM.GitHubMiner.model.issue.Issue;
import integrationProjectGHM.GitHubMiner.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import static integrationProjectGHM.GitHubMiner.util.RestTemplateHelper.getResponseEntity;
import static integrationProjectGHM.GitHubMiner.util.RestTemplateHelper.getResponseEntityById;

@Service
public class IssueService {
    @Autowired
    RestTemplate restTemplate;
    // Recomendable la creación de un constructor
    @Autowired
    public IssueService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    @Value("${github.baseuri}")
    private String baseUri;

    // Añadimos la posibilidad de poder usar tokens para aumentar el límite de peticiones
    @Value("${github.token}")
    private String token;


    // A partir de aquí comenzamos a definir métodos para cada operación que queramos hacer con la API de GitHub para los Issues
    // https://api.github.com/repos/{owner}/{repo}/issues
    public ResponseEntity<Issue[]> getAllIssues (String owner, String repo){
        String uri = baseUri + owner + "/" + repo + "/issues";
        return getResponseEntity(restTemplate, uri, token, Issue[].class);
    }
    // https://api.github.com/repos/{owner}/{repo}/issues/{issue_number}
    public ResponseEntity<Issue> getIssueById (String owner, String repo, String id){
        String uri = baseUri + owner + "/" + repo + "/issues/" + id;
        return getResponseEntityById(restTemplate, uri, token, Issue.class);
    }

    /* La siguiente función se ejecuta para seguir la lógica del parámetro opcional
        "sinceIssues" que se pasa al POST a la base de datos de GitMiner
    */
    //GET https://api.github.com/repos/{owner}/{repo}/issues?since={fecha}
    public List<Issue> getAllIssuesSince(String owner, String repoName, Integer updatedSinceDays, Integer maxPages) throws HttpClientErrorException {
        List<Issue> issues = new ArrayList<>();
        int pagesNumber = (maxPages != null) ? maxPages : 2; // Default: 2 páginas

        String initialUrl = String.format("%s%s/%s/issues?since=%s&state=all",
                baseUri,
                owner,
                repoName,
                OffsetDateTime.now(ZoneOffset.UTC)
                        .minusDays(updatedSinceDays != null ? updatedSinceDays : 20)
                        .toString()
        );

        String nextPageUrl = initialUrl;
        int currentPage = 1;

        while (nextPageUrl != null && currentPage <= pagesNumber) {
            System.out.println("Fetching page " + currentPage + ": " + nextPageUrl); // debug improvisado

            ResponseEntity<Issue[]> response = getResponseEntity(restTemplate, nextPageUrl, token, Issue[].class);
            issues.addAll(Arrays.asList(response.getBody()));

            // Aquí es donde la URL devuelta por GitHub puede contener `after=...`
            nextPageUrl = cleanPaginationUrl(Util.getNextPageUrl(response.getHeaders()));
            currentPage++;
        }

        return issues;
    }

    // Método auxiliar para eliminar cursors (el "after=...") de la URL
    private String cleanPaginationUrl(String url) {
        if (url == null) return null;
        return url.replaceAll("&after=[^&]*", ""); // Elimina `after=...`
    }


}