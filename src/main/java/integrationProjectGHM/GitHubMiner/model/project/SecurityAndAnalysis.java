
package integrationProjectGHM.GitHubMiner.model.project;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class SecurityAndAnalysis {

    @JsonProperty("advanced_security")
    private AdvancedSecurity advancedSecurity;
    @JsonProperty("secret_scanning")
    private SecretScanning secretScanning;
    @JsonProperty("secret_scanning_push_protection")
    private SecretScanningPushProtection secretScanningPushProtection;
    @JsonProperty("secret_scanning_non_provider_patterns")
    private SecretScanningNonProviderPatterns secretScanningNonProviderPatterns;

    @JsonProperty("advanced_security")
    public AdvancedSecurity getAdvancedSecurity() {
        return advancedSecurity;
    }

    @JsonProperty("advanced_security")
    public void setAdvancedSecurity(AdvancedSecurity advancedSecurity) {
        this.advancedSecurity = advancedSecurity;
    }

    @JsonProperty("secret_scanning")
    public SecretScanning getSecretScanning() {
        return secretScanning;
    }

    @JsonProperty("secret_scanning")
    public void setSecretScanning(SecretScanning secretScanning) {
        this.secretScanning = secretScanning;
    }

    @JsonProperty("secret_scanning_push_protection")
    public SecretScanningPushProtection getSecretScanningPushProtection() {
        return secretScanningPushProtection;
    }

    @JsonProperty("secret_scanning_push_protection")
    public void setSecretScanningPushProtection(SecretScanningPushProtection secretScanningPushProtection) {
        this.secretScanningPushProtection = secretScanningPushProtection;
    }

    @JsonProperty("secret_scanning_non_provider_patterns")
    public SecretScanningNonProviderPatterns getSecretScanningNonProviderPatterns() {
        return secretScanningNonProviderPatterns;
    }

    @JsonProperty("secret_scanning_non_provider_patterns")
    public void setSecretScanningNonProviderPatterns(SecretScanningNonProviderPatterns secretScanningNonProviderPatterns) {
        this.secretScanningNonProviderPatterns = secretScanningNonProviderPatterns;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SecurityAndAnalysis.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("advancedSecurity");
        sb.append('=');
        sb.append(((this.advancedSecurity == null)?"<null>":this.advancedSecurity));
        sb.append(',');
        sb.append("secretScanning");
        sb.append('=');
        sb.append(((this.secretScanning == null)?"<null>":this.secretScanning));
        sb.append(',');
        sb.append("secretScanningPushProtection");
        sb.append('=');
        sb.append(((this.secretScanningPushProtection == null)?"<null>":this.secretScanningPushProtection));
        sb.append(',');
        sb.append("secretScanningNonProviderPatterns");
        sb.append('=');
        sb.append(((this.secretScanningNonProviderPatterns == null)?"<null>":this.secretScanningNonProviderPatterns));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
