import java.util.List;

public class Configuration {
    private Vault vault;
    private List<Secret> secrets;

    public Vault getVault() {
        return this.vault;
    }

    public void setVault(Vault vault) {
        this.vault = vault;
    }
    

    public List<Secret> getSecrets() {
        return this.secrets;
    }

    public void setSecrets(List<Secret> secrets) {
        this.secrets = secrets;
    }
}