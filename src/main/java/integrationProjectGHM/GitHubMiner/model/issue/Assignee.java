package integrationProjectGHM.GitHubMiner.model.issue;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Assignee {
    @JsonProperty("login")
    private String login;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("node_id")
    private String node_id;
    @JsonProperty("avatar_url")
    private String avatar_url;
    @JsonProperty("gravatar_id")
    private String gravatar_id;
    @JsonProperty("url")
    private String url;
    @JsonProperty("html_url")
    private String html_url;
    @JsonProperty("followers_url")
    private String followers_url;
    @JsonProperty("following_url")
    private String following_url;
    @JsonProperty("gists_url")
    private String gists_url;
    @JsonProperty("starred_url")
    private String starred_url;
    @JsonProperty("subscriptions_url")
    private String subscriptions_url;
    @JsonProperty("organizations_url")
    private String organizations_url;
    @JsonProperty("repos_url")
    private String repos_url;
    @JsonProperty("events_url")
    private String events_url;
    @JsonProperty("received_events_url")
    private String received_events_url;
    @JsonProperty("type")
    private String type;
    @JsonProperty("site_admin")
    private boolean site_admin;

    @JsonProperty("login")
    public String getLogin() { return login; }

    @JsonProperty("login")
    public void setLogin(String login) { this.login = login; }

    @JsonProperty("id")
    public Integer getId() { return id; }

    @JsonProperty("id")
    public void setId(Integer id) { this.id = id; }

    @JsonProperty("node_id")
    public String getNodeId() { return node_id; }

    @JsonProperty("node_id")
    public void setNodeId(String node_id) { this.node_id = node_id; }

    @JsonProperty("avatar_url")
    public String getAvatarUrl() { return avatar_url; }

    @JsonProperty("avatar_url")
    public void setAvatarUrl(String avatar_url) { this.avatar_url = avatar_url; }

    @JsonProperty("gravatar_id")
    public String getGravatarId() { return gravatar_id; }

    @JsonProperty("gravatar_id")
    public void setGravatarId(String gravatar_id) { this.gravatar_id = gravatar_id; }

    @JsonProperty("url")
    public String getUrl() { return url; }

    @JsonProperty("url")
    public void setUrl(String url) { this.url = url; }

    @JsonProperty("html_url")
    public String getHtmlUrl() { return html_url; }

    @JsonProperty("html_url")
    public void setHtmlUrl(String html_url) { this.html_url = html_url; }

    @JsonProperty("followers_url")
    public String getFollowersUrl() { return followers_url; }

    @JsonProperty("followers_url")
    public void setFollowersUrl(String followers_url) { this.followers_url = followers_url; }

    @JsonProperty("following_url")
    public String getFollowingUrl() { return following_url; }

    @JsonProperty("following_url")
    public void setFollowingUrl(String following_url) { this.following_url = following_url; }

    @JsonProperty("gists_url")
    public String getGistsUrl() { return gists_url; }

    @JsonProperty("gists_url")
    public void setGistsUrl(String gists_url) { this.gists_url = gists_url; }

    @JsonProperty("starred_url")
    public String getStarredUrl() { return starred_url; }

    @JsonProperty("starred_url")
    public void setStarredUrl(String starred_url) { this.starred_url = starred_url; }

    @JsonProperty("subscriptions_url")
    public String getSubscriptionsUrl() { return subscriptions_url; }

    @JsonProperty("subscriptions_url")
    public void setSubscriptionsUrl(String subscriptions_url) { this.subscriptions_url = subscriptions_url; }

    @JsonProperty("organizations_url")
    public String getOrganizationsUrl() { return organizations_url; }

    @JsonProperty("organizations_url")
    public void setOrganizationsUrl(String organizations_url) { this.organizations_url = organizations_url; }

    @JsonProperty("repos_url")
    public String getReposUrl() { return repos_url; }

    @JsonProperty("repos_url")
    public void setReposUrl(String repos_url) { this.repos_url = repos_url; }

    @JsonProperty("events_url")
    public String getEventsUrl() { return events_url; }

    @JsonProperty("events_url")
    public void setEventsUrl(String events_url) { this.events_url = events_url; }

    @JsonProperty("received_events_url")
    public String getReceivedEventsUrl() { return received_events_url; }

    @JsonProperty("received_events_url")
    public void setReceivedEventsUrl(String received_events_url) { this.received_events_url = received_events_url; }

    @JsonProperty("type")
    public String getType() { return type; }

    @JsonProperty("type")
    public void setType(String type) { this.type = type; }

    @JsonProperty("site_admin")
    public boolean isSiteAdmin() { return site_admin; }

    @JsonProperty("site_admin")
    public void setSiteAdmin(boolean site_admin) { this.site_admin = site_admin; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Assignee.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("login=").append(login).append(',');
        sb.append("id=").append(id).append(',');
        sb.append("node_id=").append(node_id).append(',');
        sb.append("avatar_url=").append(avatar_url).append(',');
        sb.append("gravatar_id=").append(gravatar_id).append(',');
        sb.append("url=").append(url).append(',');
        sb.append("html_url=").append(html_url).append(',');
        sb.append("followers_url=").append(followers_url).append(',');
        sb.append("following_url=").append(following_url).append(',');
        sb.append("gists_url=").append(gists_url).append(',');
        sb.append("starred_url=").append(starred_url).append(',');
        sb.append("subscriptions_url=").append(subscriptions_url).append(',');
        sb.append("organizations_url=").append(organizations_url).append(',');
        sb.append("repos_url=").append(repos_url).append(',');
        sb.append("events_url=").append(events_url).append(',');
        sb.append("received_events_url=").append(received_events_url).append(',');
        sb.append("type=").append(type).append(',');
        sb.append("site_admin=").append(site_admin).append(']');
        return sb.toString();
    }
}

