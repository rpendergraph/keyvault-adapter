
public class Vault {
    private String type;
    private String url;
    private Credentials credentials;

    public String getType() {
        return this.type;
    }

    public String getUrl() {
        return this.url;
    }

    public Credentials getCredentials() {
        return this.credentials;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }
}