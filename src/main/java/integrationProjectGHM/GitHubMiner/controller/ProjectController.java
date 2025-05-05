package integrationProjectGHM.GitHubMiner.controller;


import integrationProjectGHM.GitHubMiner.model.issue.Issue;
import integrationProjectGHM.GitHubMiner.model.project.Project;
import integrationProjectGHM.GitHubMiner.modelsGitHubMiner.CommitGitHubMiner;
import integrationProjectGHM.GitHubMiner.modelsGitHubMiner.IssueGitHubMiner;
import integrationProjectGHM.GitHubMiner.modelsGitHubMiner.ProjectGitHubMiner;
import integrationProjectGHM.GitHubMiner.service.CommentService;
import integrationProjectGHM.GitHubMiner.service.CommitService;
import integrationProjectGHM.GitHubMiner.service.IssueService;
import integrationProjectGHM.GitHubMiner.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import static integrationProjectGHM.GitHubMiner.util.Formatters.commitFormatter;
import static integrationProjectGHM.GitHubMiner.util.Formatters.issueFormatter;

@RestController
@RequestMapping("/github") // Sirve para definir la ruta base de la API (siempre que llamemos a la API tendrá esta ruta base
public class ProjectController {

    private final RestTemplate restTemplate;
    private final ProjectService projectService;
    private final CommitService commitService;
    private final IssueService issueService;
    private final CommentService commentService;

    // Un único constructor con @Autowired
    @Autowired
    public ProjectController(
            RestTemplate restTemplate,
            ProjectService projectService,
            CommitService commitService,
            IssueService issueService,
            CommentService commentService
    ) {
        this.restTemplate = restTemplate;
        this.projectService = projectService;
        this.commitService = commitService;
        this.issueService = issueService;
        this.commentService = commentService;
    }

    // GET http://localhost:8082/github/{owner}/{repoName}[?sinceCommits=5&sinceIssues=30&maxPages=2]
    @GetMapping("/{owner}/{repoName}")
    public ProjectGitHubMiner getProject(@PathVariable String owner, @PathVariable String repoName,
                                         @RequestParam(required = false, name = "sinceCommits", defaultValue = "2") Integer sinceCommits,
                                         @RequestParam(required = false, name = "sinceIssues", defaultValue = "20") Integer sinceIssues,
                                         @RequestParam(required = false, name = "maxPages", defaultValue = "2") Integer maxPages) {

        Project project = projectService.getProjectByName(owner, repoName).getBody(); // Ponemos getBody() porque es un ResponseEntity
        // Empezamos a obtener los parámetros de nuestra clase ProjectGitHubMiner, que es la clase que irá a la base de datos
        String projectId = project.getId().toString();
        String projectName = project.getName();
        String project_webUrl = project.getHtmlUrl();
        // Hasta aquí los 3 atributos de la entidad Project, ahora hay que manejar su relación con Commits e Issues
        List<CommitGitHubMiner> commits = commitService.getAllCommitsSince(owner, repoName, sinceCommits, maxPages).stream()
                .map(c -> commitFormatter(c)).toList();
        List<IssueGitHubMiner> issues = issueService.getAllIssuesSince(owner, repoName, sinceIssues, maxPages).stream()
                .map(i -> issueFormatter(commentService, i, owner, repoName, maxPages)).toList();

        // Ya tenemos todos los parámetros necesarios para poder construir un objeto ProjectGitHubMiner
        return new ProjectGitHubMiner(projectId, projectName, project_webUrl, commits, issues);
    }

    // POST http://localhost:8082/github/{owner}/{repoName}[?sinceCommits=5&sinceIssues=30&maxPages=2]
    @PostMapping("/{owner}/{repoName}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectGitHubMiner createProject(@PathVariable String owner, @PathVariable String repoName,
                                            @RequestParam(required = false, name = "sinceCommits", defaultValue = "2") Integer sinceCommits,
                                            @RequestParam(required = false, name = "sinceIssues", defaultValue = "20") Integer sinceIssues,
                                            @RequestParam(required = false, name = "maxPages", defaultValue = "2") Integer maxPages) {

        Project project = projectService.getProjectByName(owner, repoName).getBody();
        String projectId = project.getId().toString();
        String projectName = project.getName();
        String project_webUrl = project.getHtmlUrl();
        List<CommitGitHubMiner> commits = commitService.getAllCommitsSince(owner, repoName, sinceCommits, maxPages).stream()
                .map(c -> commitFormatter(c)).toList();
        List<IssueGitHubMiner> issues = issueService.getAllIssuesSince(owner, repoName, sinceIssues, maxPages).stream()
                .map(i -> issueFormatter(commentService, i, owner, repoName, maxPages)).toList();
        ProjectGitHubMiner projectGitHubMiner = new ProjectGitHubMiner(projectId, projectName, project_webUrl, commits, issues);
        return projectService.sendProjectToGitMiner(projectGitHubMiner).getBody();
    }
}
