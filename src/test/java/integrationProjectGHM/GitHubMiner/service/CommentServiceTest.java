package integrationProjectGHM.GitHubMiner.service;

import integrationProjectGHM.GitHubMiner.model.comment.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CommentServiceTest {
    @Autowired
    CommentService commentService;

    @Test
    @DisplayName("Get all comments")
    void getAllComments() {
        ResponseEntity<Comment[]> response = commentService.getAllComments("octocat", "Hello-World", "1");
        List<Comment> comments = List.of(response.getBody());
        assertFalse(comments.isEmpty(), "Comment list should not be empty");
        //System.out.println(comments);
        System.out.println(comments.size());
        for (Comment comment : comments) {
            System.out.println(comment);
        }
    }

    @Test
    @DisplayName("Get comment by ID")
    void getCommentById() {
        ResponseEntity<Comment> response = commentService.getCommentById("octocat", "Hello-World", "1340258");
        Comment comment = response.getBody();
        assertNotNull(comment, "Comment should not be null");
        System.out.println(comment);
    }

    @Test
    @DisplayName("Get comments for issue")
    void getCommentsForIssue() {
        ResponseEntity<Comment[]> response = commentService.getCommentsForIssue("octocat", "Hello-World", 1);
        List<Comment> comments = List.of(response.getBody());
        assertFalse(comments.isEmpty(), "Comment list should not be empty");
        //System.out.println(comments);
        System.out.println(comments.size());
        for (Comment comment : comments) {
            System.out.println(comment);
        }
    }

    @Test
    @DisplayName("Get issue comments paginated")
    void getIssueCommentsPaginated() {
        List<Comment> comments = commentService.getIssueCommentsPaginated("octocat", "Hello-World", 1, 3);
        assertFalse(comments.isEmpty(), "Comment list should not be empty");
        //System.out.println(comments);
        System.out.println(comments.size());
        for (Comment comment : comments) {
            System.out.println(comment);
        }

    }
}