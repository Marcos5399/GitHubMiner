package integrationProjectGHM.GitHubMiner.service;

import integrationProjectGHM.GitHubMiner.model.issue.Issue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class IssueServiceTest {
    @Autowired
    IssueService issueService;

    @Test
    @DisplayName("Get all issues")
    void getAllIssues() {
        ResponseEntity<Issue[]> response = issueService.getAllIssues("octocat", "Hello-World");
        List<Issue> issues = List.of(response.getBody());
        assertFalse(issues.isEmpty(), "Issue list should not be empty");
        //System.out.println(issues);
        System.out.println("Total issues: " + issues.size());
        for (Issue issue : issues) {
            System.out.println(issue);
        }
    }

    @Test
    @DisplayName("Get issue by ID")
    // Vamos a probar una issue que tenga campo labels y assignee ya que la mayoría no lo tiene
    // https://api.github.com/repos/microsoft/vscode/issues/190000
    void getIssueById() {
        ResponseEntity<Issue> response = issueService.getIssueById("microsoft", "vscode", "190000");
        Issue issue = response.getBody();
        assertNotNull(issue, "Issue should not be null");
        System.out.println(issue);
    }

    @Test
    @DisplayName("Get all issues since a given date")
    void getAllIssuesSince() {
        List<Issue> issues = issueService.getAllIssuesSince(
                "octocat",                  // owner
                "Hello-World",              // repo
                30,                        // updatedSinceDays (últimos 30 días)
                2                         // maxPages (páginas 1 y 2)
        );

        assertFalse(issues.isEmpty());
        System.out.println("Issues obtenidas: " + issues.size());
        for (Issue issue : issues) {
            System.out.println(issue);
        }
    }
}