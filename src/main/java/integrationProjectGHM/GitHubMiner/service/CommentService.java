package integrationProjectGHM.GitHubMiner.service;
import integrationProjectGHM.GitHubMiner.model.comment.Comment;
import integrationProjectGHM.GitHubMiner.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static integrationProjectGHM.GitHubMiner.util.RestTemplateHelper.getResponseEntity;
import static integrationProjectGHM.GitHubMiner.util.RestTemplateHelper.getResponseEntityById;

@Service
public class CommentService {
    @Autowired
    RestTemplate restTemplate;
    // Recomendable la creación de un constructor
    @Autowired
    public CommentService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    @Value("${github.baseuri}")
    private String baseUri; // El valor de baseUrl se inyecta desde el archivo application.properties gracias al @Value

    @Value("${github.token}")
    private String token;
    // A partir de aquí comenzamos a definir métodos para cada operación que queramos hacer con la API de GitHub para los Comments
    // https://api.github.com/repos/{owner}/{repo}/issues/{issue_number}/comments
    public ResponseEntity<Comment[]> getAllComments (String owner, String repo, String issue_number){
        String uri = baseUri + owner + "/" + repo + "/issues/" + issue_number + "/comments";
        return getResponseEntity(restTemplate, uri, token, Comment[].class);
    }

    // https://api.github.com/repos/{owner}/{repo}/issues/comments/{id}
    public ResponseEntity<Comment> getCommentById (String owner, String repo, String id){
        String uri = baseUri + owner + "/" + repo + "/issues/comments/" + id;
        return getResponseEntityById(restTemplate, uri, token, Comment.class);
    }

    // Definimos una función que devuelva lo comments de una issue
    // Existe una URI que devuelve los comments de una issue, pero no existe una URI que devuelva los comments de una issue
    // https://api.github.com/repos/{owner}/{repo}/issues/{issue_number}/comments    public ResponseEntity<Comment[]> getCommentsForIssue (String owner, String repoName) {
    public ResponseEntity<Comment[]> getCommentsForIssue (String owner, String repoName, Integer issue_number) {
        String uri = baseUri + owner + "/" + repoName + "/issues/" + issue_number + "/comments";
        return getResponseEntity(restTemplate, uri, token, Comment[].class);
    }

    // Definimos una función que devuelva los comments asociados a una issue, pero, además, aplicando la paginación
    // https://api.github.com/repos/{owner}/{repo}/issues/{issue_number}/comments?per_page={cantidad_por_página}&page={número_de_página}
    public List<Comment> getIssueCommentsPaginated (String owner, String repoName, Integer issue_number, Integer maxPages) throws HttpClientErrorException {
        List<Comment> comments = new ArrayList<>();
        int pagesNumber = (maxPages != null) ? maxPages : 2; // Default: 2 páginas

        String initialUrl = baseUri + owner + "/" + repoName + "/issues/" + issue_number + "/comments"; // No hace falta que pongamos "?page=1" porque ya está definido por defecto
        String nextPageUrl = initialUrl;
        int currentPage = 1;

        while (nextPageUrl != null && currentPage <= pagesNumber) {
            System.out.println("Fetching page " + currentPage + ": " + nextPageUrl); // debug improvisado

            ResponseEntity<Comment[]> response = getResponseEntity(restTemplate, nextPageUrl, token, Comment[].class);
            comments.addAll(Arrays.asList(response.getBody()));

            // Aquí es donde la URL devuelta por GitHub puede contener `after=...`
            nextPageUrl = Util.getNextPageUrl(response.getHeaders());
            currentPage++;
        }

        return comments;
    }

}

