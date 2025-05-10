package integrationProjectGHM.GitHubMiner.service;
import integrationProjectGHM.GitHubMiner.model.project.Project;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProjectServiceTest {
    @Autowired
    ProjectService projectService;

    @Test
    @DisplayName("Get all projects of a user")
    void getAllUserProjects() {
        ResponseEntity<Project[]> response = projectService.getAllUserProjects("octocat");
        List<Project> projects = List.of(response.getBody());
        assertFalse(projects.isEmpty(), "Project list should not be empty");
        //System.out.println(projects);
        System.out.println("Total projects: " + projects.size());
        for (Project project : projects) {
            System.out.println(project);
        }
    }

    @Test
    @DisplayName("Get project by name")
    void getProjectByName() {
        ResponseEntity<Project> response = projectService.getProjectByName("octocat", "Hello-World");
        Project project = response.getBody();
        assertNotNull(project, "Project should not be null");
        System.out.println(project);
    }
}