package integrationProjectGHM.GitHubMiner.model.issue;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Label {
    @JsonProperty("id")
    private long id;
    @JsonProperty("node_id")
    private String node_id;
    @JsonProperty("url")
    private String url;
    @JsonProperty("name")
    private String name;
    @JsonProperty("color")
    private String color;
    @JsonProperty("default")
    private boolean isDefault;
    @JsonProperty("description")
    private String description;

    @JsonProperty("id")
    public long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty("node_id")
    public String getNodeId() {
        return node_id;
    }

    @JsonProperty("node_id")
    public void setNodeId(String node_id) {
        this.node_id = node_id;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("color")
    public String getColor() {
        return color;
    }

    @JsonProperty("color")
    public void setColor(String color) {
        this.color = color;
    }

    @JsonProperty("default")
    public boolean isDefault() {
        return isDefault;
    }

    @JsonProperty("default")
    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Label.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id=").append(id).append(',');
        sb.append("node_id=").append(node_id).append(',');
        sb.append("url=").append(url).append(',');
        sb.append("name=").append(name).append(',');
        sb.append("color=").append(color).append(',');
        sb.append("default=").append(isDefault).append(',');
        sb.append("description=").append(description).append(']');
        return sb.toString();
    }
}

