package integrationProjectGHM.GitHubMiner.modelsGitHubMiner;

import java.util.List;

public class ProjectGitHubMiner {
    public String id;
    public String name;
    public String web_url;
    private List<CommitGitHubMiner> commits;
    private List<IssueGitHubMiner> issues;

    public ProjectGitHubMiner(String id, String name, String webUrl, List<CommitGitHubMiner> commits, List<IssueGitHubMiner> issues) {
        this.id = id;
        this.name = name;
        this.web_url = webUrl;
        this.commits = commits;
        this.issues = issues;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public List<CommitGitHubMiner> getCommits() {
        return commits;
    }

    public void setCommits(List<CommitGitHubMiner> commits) {
        this.commits = commits;
    }

    public List<IssueGitHubMiner> getIssues() {
        return issues;
    }

    public void setIssues(List<IssueGitHubMiner> issues) {
        this.issues = issues;
    }
}
