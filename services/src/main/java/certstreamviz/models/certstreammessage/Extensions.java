
package certstreamviz.models.certstreammessage;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
    "authorityInfoAccess",
    "authorityKeyIdentifier",
    "basicConstraints",
    "certificatePolicies",
    "crlDistributionPoints",
    "keyUsage",
    "subjectKeyIdentifier"
})
public class Extensions {

    @JsonProperty("authorityInfoAccess")
    private String authorityInfoAccess;
    @JsonProperty("authorityKeyIdentifier")
    private String authorityKeyIdentifier;
    @JsonProperty("basicConstraints")
    private String basicConstraints;
    @JsonProperty("certificatePolicies")
    private String certificatePolicies;
    @JsonProperty("crlDistributionPoints")
    private String crlDistributionPoints;
    @JsonProperty("keyUsage")
    private String keyUsage;
    @JsonProperty("subjectKeyIdentifier")
    private String subjectKeyIdentifier;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Extensions() {
    }

    /**
     * 
     * @param crlDistributionPoints
     * @param authorityKeyIdentifier
     * @param subjectKeyIdentifier
     * @param authorityInfoAccess
     * @param basicConstraints
     * @param certificatePolicies
     * @param keyUsage
     */
    public Extensions(String authorityInfoAccess, String authorityKeyIdentifier, String basicConstraints, String certificatePolicies, String crlDistributionPoints, String keyUsage, String subjectKeyIdentifier) {
        super();
        this.authorityInfoAccess = authorityInfoAccess;
        this.authorityKeyIdentifier = authorityKeyIdentifier;
        this.basicConstraints = basicConstraints;
        this.certificatePolicies = certificatePolicies;
        this.crlDistributionPoints = crlDistributionPoints;
        this.keyUsage = keyUsage;
        this.subjectKeyIdentifier = subjectKeyIdentifier;
    }

    @JsonProperty("authorityInfoAccess")
    public String getAuthorityInfoAccess() {
        return authorityInfoAccess;
    }

    @JsonProperty("authorityInfoAccess")
    public void setAuthorityInfoAccess(String authorityInfoAccess) {
        this.authorityInfoAccess = authorityInfoAccess;
    }

    @JsonProperty("authorityKeyIdentifier")
    public String getAuthorityKeyIdentifier() {
        return authorityKeyIdentifier;
    }

    @JsonProperty("authorityKeyIdentifier")
    public void setAuthorityKeyIdentifier(String authorityKeyIdentifier) {
        this.authorityKeyIdentifier = authorityKeyIdentifier;
    }

    @JsonProperty("basicConstraints")
    public String getBasicConstraints() {
        return basicConstraints;
    }

    @JsonProperty("basicConstraints")
    public void setBasicConstraints(String basicConstraints) {
        this.basicConstraints = basicConstraints;
    }

    @JsonProperty("certificatePolicies")
    public String getCertificatePolicies() {
        return certificatePolicies;
    }

    @JsonProperty("certificatePolicies")
    public void setCertificatePolicies(String certificatePolicies) {
        this.certificatePolicies = certificatePolicies;
    }

    @JsonProperty("crlDistributionPoints")
    public String getCrlDistributionPoints() {
        return crlDistributionPoints;
    }

    @JsonProperty("crlDistributionPoints")
    public void setCrlDistributionPoints(String crlDistributionPoints) {
        this.crlDistributionPoints = crlDistributionPoints;
    }

    @JsonProperty("keyUsage")
    public String getKeyUsage() {
        return keyUsage;
    }

    @JsonProperty("keyUsage")
    public void setKeyUsage(String keyUsage) {
        this.keyUsage = keyUsage;
    }

    @JsonProperty("subjectKeyIdentifier")
    public String getSubjectKeyIdentifier() {
        return subjectKeyIdentifier;
    }

    @JsonProperty("subjectKeyIdentifier")
    public void setSubjectKeyIdentifier(String subjectKeyIdentifier) {
        this.subjectKeyIdentifier = subjectKeyIdentifier;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
