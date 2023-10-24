
public class Credentials {
    private String principal;
    private String secret;
    private String tenant;

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getPrincipal() {
        return this.principal;
    }

    public String getSecret() {
        return this.secret;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}