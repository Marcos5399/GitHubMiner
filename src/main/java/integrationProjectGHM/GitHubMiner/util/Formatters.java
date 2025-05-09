package integrationProjectGHM.GitHubMiner.util;

import integrationProjectGHM.GitHubMiner.model.comment.Comment;
import integrationProjectGHM.GitHubMiner.model.commit.Commit;
import integrationProjectGHM.GitHubMiner.model.issue.Issue;
import integrationProjectGHM.GitHubMiner.modelsGitHubMiner.CommentGitHubMiner;
import integrationProjectGHM.GitHubMiner.modelsGitHubMiner.CommitGitHubMiner;
import integrationProjectGHM.GitHubMiner.modelsGitHubMiner.IssueGitHubMiner;
import integrationProjectGHM.GitHubMiner.modelsGitHubMiner.UserGitHubMiner;
import integrationProjectGHM.GitHubMiner.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Formatters {

    // Commit formatter: To format the Commit object to CommitGitHubMiner object
    public static CommitGitHubMiner commitFormatter(Commit commit) {
        String fullMessage = commit.getCommit().getMessage();
        String title;
        int newlineIndex = fullMessage.indexOf("\n");

        // Si no hay salto de línea, usar el mensaje completo como título
        if (newlineIndex == -1) {
            title = fullMessage;
        } else {
            title = fullMessage.substring(0, newlineIndex);
        }
        return new CommitGitHubMiner(
                commit.getSha(),
                title,
                fullMessage,
                commit.getCommit().getAuthor().getName(),
                commit.getCommit().getAuthor().getEmail(),
                commit.getCommit().getAuthor().getDate(),
                commit.getHtmlUrl()
        );
    }
    // Issue formatter: To format the Issue object to IssueGitHubMiner object
    public static IssueGitHubMiner issueFormatter(CommentService commentService, Issue issue, String owner, String repoName, Integer maxPage) {
        // Obtenemos las comments de un Issue usando la función que creamos en el service de
        List<Comment> comments = commentService.getIssueCommentsPaginated( owner, repoName, issue.getNumber(), maxPage );
        return new IssueGitHubMiner(
                issue.getId().toString(),
                issue.getTitle(),
                issue.getBody(),
                issue.getState(),
                issue.getCreatedAt(),
                issue.getUpdatedAt(),
                issue.getClosedAt(),
                issue.getLabels().stream().map(label -> label.getName()).toList(),
                issue.getReactions().getTotalCount(),
                UserGitHubMiner.create( //author
                        issue.getUser().getId().toString(),
                        issue.getUser().getLogin(),
                        issue.getUser().getLogin(), // Tanto los assignees como los authors no tienen campo "name" al devolverse en una respuesta de un Issue
                        issue.getUser().getAvatarUrl(),
                        issue.getUser().getHtmlUrl()
                ),
                issue.getAssignee() == null ? null : UserGitHubMiner.create( //assignee
                        issue.getAssignee().getId().toString(),
                        issue.getAssignee().getLogin(),
                        issue.getAssignee().getLogin(),
                        issue.getAssignee().getAvatarUrl(),
                        issue.getAssignee().getHtmlUrl()
                ),
                comments.stream().map(c -> commentFormatter(c)).toList()    //List<CommentGitHubMiner> comments
        );
    }
    // Comment formatter: To format the Comment object to CommentGitHubMiner object
    public static CommentGitHubMiner commentFormatter(Comment comment) {
        return new CommentGitHubMiner(
                comment.getId().toString(),
                comment.getBody(),
                comment.getCreatedAt(),
                comment.getUpdatedAt(),
                UserGitHubMiner.create(
                        comment.getUser().getId().toString(),
                        comment.getUser().getLogin(),
                        comment.getUser().getLogin(), // Los user de los comments no tienen campo "name" solo el "Login"
                        comment.getUser().getAvatarUrl(),
                        comment.getUser().getHtmlUrl()
                )
        );
    }

}
