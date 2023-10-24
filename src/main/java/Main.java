
import com.azure.core.credential.TokenCredential;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

public class Main {

    static Configuration parseConfiguration(String path) throws FileNotFoundException {
        Yaml yaml = new Yaml(new Constructor(Configuration.class, new LoaderOptions()));
        FileInputStream fis = new FileInputStream(path);
        return yaml.load(fis);
    }

    static void fetchSecrets(SecretClient client, Configuration configuration) {
        for (Secret secret : configuration.getSecrets()) {
            KeyVaultSecret kvSecret = client.getSecret(secret.getKey());
            System.out.printf("%s=%s\n", secret.getTarget(), kvSecret.getValue());
        }
    }

    static SecretClient getSecretClient(Configuration config) {
        Vault vault = config.getVault();

        switch (vault.getType()) {
            case "keyvault":
                return getKeyvaultClient(vault);
            default:
                System.err.printf("unknown type %s", vault.getType());
                System.exit(-1);
        }
        return null;
    }

    static SecretClient getKeyvaultClient(Vault vault) {
        Credentials credentialConfig = vault.getCredentials();
        if (credentialConfig != null) {
            System.setProperty("AZURE_CLIENT_ID", credentialConfig.getPrincipal());
            System.setProperty("AZURE_CLIENT_SECRET", credentialConfig.getSecret());
            System.setProperty("AZURE_TENANT_ID", credentialConfig.getTenant());
        }
        return new SecretClientBuilder()
                .vaultUrl(vault.getUrl())
                .credential(new DefaultAzureCredentialBuilder().build())
                .buildClient();

    }

    public static void main(String[] args) {
        try {
            Configuration config = parseConfiguration("./configuration.yaml");
            SecretClient client = getSecretClient(config);
            fetchSecrets(client, config);
        } catch (FileNotFoundException fne) {
            System.err.printf("couldn't find the file: %s", fne);
            System.exit(-1);
        }
    }

}
