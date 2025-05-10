package integrationProjectGHM.GitHubMiner.service;

import integrationProjectGHM.GitHubMiner.model.commit.Commit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommitServiceTest {
    @Autowired
    CommitService commitService;
    @Test
    @DisplayName("Get all commits")
    void getAllCommits() {
        ResponseEntity<Commit[]> response = commitService.getAllCommits("octocat", "Hello-World");
        List<Commit> commits = List.of(response.getBody());
        assertFalse(commits.isEmpty(), "Commit list should not be empty");
        System.out.println(commits);
        System.out.println("Número de commits:" + commits.size());
    }

    @Test
    @DisplayName("Get commit by ID")
    void getCommitById() {
        String commitId = "4f79eaa2ceac86a0e0f304b0bab556cca5bf4f30";
        ResponseEntity<Commit> response = commitService.getCommitById("torvalds", "linux", commitId);
        Commit commit = response.getBody();
        assertNotNull(commit, "Commit should not be null");
        assertEquals(commitId, commit.getSha(), "Commit ID should match");
        System.out.println(commit);
    }

    @Test
    @DisplayName("Get all commits since a given date")
    void getAllCommitsSince() {
        Integer updatedSinceDays = 30;
        Integer maxPages = 4;
        List<Commit> commits = commitService.getAllCommitsSince("spring-projects", "spring-framework", updatedSinceDays, maxPages);
        assertFalse(commits.isEmpty(), "Commit list should not be empty");
        System.out.println("Número de commits:" + commits.size());
        for (Commit commit : commits) {
            System.out.println(commit);
        }
    }
}
