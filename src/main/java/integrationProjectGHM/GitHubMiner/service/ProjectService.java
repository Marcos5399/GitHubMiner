package integrationProjectGHM.GitHubMiner.service;

import integrationProjectGHM.GitHubMiner.model.project.Project;
import integrationProjectGHM.GitHubMiner.modelsGitHubMiner.ProjectGitHubMiner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import static integrationProjectGHM.GitHubMiner.util.RestTemplateHelper.getResponseEntity;
import static integrationProjectGHM.GitHubMiner.util.RestTemplateHelper.getResponseEntityById;

@Service
public class ProjectService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    public ProjectService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    @Value("${github.baseuri}")
    private String baseUri;
    @Value("${gitminer.baseuri}")
    private String baseUriGitMiner;

    @Value("${github.token}")
    private String token;
    // A partir de aquí comenzamos a definir métodos para cada operación que queramos hacer con la API de GitHub para los Projects
    // https://api.github.com/users/{username}/repos
    public ResponseEntity<Project[]> getAllUserProjects (String username){
        String uri = "https://api.github.com/users/" + username + "/repos";
        return getResponseEntity(restTemplate, uri, token, Project[].class);
    }

    // https://api.github.com/repos/{owner}/{repo}
    public ResponseEntity<Project> getProjectByName (String owner, String repo){
        String uri = baseUri + owner + "/" + repo;
        return getResponseEntityById(restTemplate, uri, token, Project.class);
    }

    public ResponseEntity<ProjectGitHubMiner> sendProjectToGitMiner (ProjectGitHubMiner projectFormatted){
        String uri = baseUriGitMiner;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<ProjectGitHubMiner> request = new HttpEntity<>(projectFormatted, headers);
        return restTemplate.exchange(uri, HttpMethod.POST, request, ProjectGitHubMiner.class);
    }

}
