
package certstreamviz.models.certstreammessage;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "authorityInfoAccess",
    "authorityKeyIdentifier",
    "basicConstraints",
    "certificatePolicies",
    "crlDistributionPoints",
    "ctlSignedCertificateTimestamp",
    "extendedKeyUsage",
    "keyUsage",
    "subjectAltName",
    "subjectKeyIdentifier"
})
public class Extensions_ {

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
    @JsonProperty("ctlSignedCertificateTimestamp")
    private String ctlSignedCertificateTimestamp;
    @JsonProperty("extendedKeyUsage")
    private String extendedKeyUsage;
    @JsonProperty("keyUsage")
    private String keyUsage;
    @JsonProperty("subjectAltName")
    private String subjectAltName;
    @JsonProperty("subjectKeyIdentifier")
    private String subjectKeyIdentifier;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Extensions_() {
    }

    /**
     * 
     * @param crlDistributionPoints
     * @param ctlSignedCertificateTimestamp
     * @param authorityKeyIdentifier
     * @param subjectKeyIdentifier
     * @param extendedKeyUsage
     * @param authorityInfoAccess
     * @param basicConstraints
     * @param certificatePolicies
     * @param keyUsage
     * @param subjectAltName
     */
    public Extensions_(String authorityInfoAccess, String authorityKeyIdentifier, String basicConstraints, String certificatePolicies, String crlDistributionPoints, String ctlSignedCertificateTimestamp, String extendedKeyUsage, String keyUsage, String subjectAltName, String subjectKeyIdentifier) {
        super();
        this.authorityInfoAccess = authorityInfoAccess;
        this.authorityKeyIdentifier = authorityKeyIdentifier;
        this.basicConstraints = basicConstraints;
        this.certificatePolicies = certificatePolicies;
        this.crlDistributionPoints = crlDistributionPoints;
        this.ctlSignedCertificateTimestamp = ctlSignedCertificateTimestamp;
        this.extendedKeyUsage = extendedKeyUsage;
        this.keyUsage = keyUsage;
        this.subjectAltName = subjectAltName;
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

    @JsonProperty("ctlSignedCertificateTimestamp")
    public String getCtlSignedCertificateTimestamp() {
        return ctlSignedCertificateTimestamp;
    }

    @JsonProperty("ctlSignedCertificateTimestamp")
    public void setCtlSignedCertificateTimestamp(String ctlSignedCertificateTimestamp) {
        this.ctlSignedCertificateTimestamp = ctlSignedCertificateTimestamp;
    }

    @JsonProperty("extendedKeyUsage")
    public String getExtendedKeyUsage() {
        return extendedKeyUsage;
    }

    @JsonProperty("extendedKeyUsage")
    public void setExtendedKeyUsage(String extendedKeyUsage) {
        this.extendedKeyUsage = extendedKeyUsage;
    }

    @JsonProperty("keyUsage")
    public String getKeyUsage() {
        return keyUsage;
    }

    @JsonProperty("keyUsage")
    public void setKeyUsage(String keyUsage) {
        this.keyUsage = keyUsage;
    }

    @JsonProperty("subjectAltName")
    public String getSubjectAltName() {
        return subjectAltName;
    }

    @JsonProperty("subjectAltName")
    public void setSubjectAltName(String subjectAltName) {
        this.subjectAltName = subjectAltName;
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
