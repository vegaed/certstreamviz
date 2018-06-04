
package certstreamviz.models.certstreammessage;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import certstreamviz.models.Coordinate;

@JsonPropertyOrder({ "all_domains", "as_der", "extensions", "fingerprint", "not_after", "not_before", "serial_number",
        "subject" })
@JsonIgnoreProperties(ignoreUnknown = true)

public class LeafCert {

    @JsonProperty("all_domains")
    private List<String> allDomains = null;
    @JsonProperty("as_der")
    private String asDer;
    @JsonProperty("extensions")
    private Extensions extensions;
    @JsonProperty("fingerprint")
    private String fingerprint;
    @JsonProperty("not_after")
    private Integer notAfter;
    @JsonProperty("not_before")
    private Integer notBefore;
    @JsonProperty("serial_number")
    private String serialNumber;
    @JsonProperty("subject")
    private Subject subject;
    private Coordinate coordinate;

    /**
     * No args constructor for use in serialization
     * 
     */
    public LeafCert() {
    }

    /**
     * 
     * @param notBefore
     * @param fingerprint
     * @param allDomains
     * @param subject
     * @param serialNumber
     * @param notAfter
     * @param extensions
     * @param asDer
     */
    public LeafCert(List<String> allDomains, String asDer, Extensions extensions, String fingerprint, Integer notAfter,
            Integer notBefore, String serialNumber, Subject subject) {
        super();
        this.allDomains = allDomains;
        this.asDer = asDer;
        this.extensions = extensions;
        this.fingerprint = fingerprint;
        this.notAfter = notAfter;
        this.notBefore = notBefore;
        this.serialNumber = serialNumber;
        this.subject = subject;
    }

    @JsonProperty("all_domains")
    public List<String> getAllDomains() {
        return allDomains;
    }

    @JsonProperty("all_domains")
    public void setAllDomains(List<String> allDomains) {
        this.allDomains = allDomains;
    }

    @JsonProperty("as_der")
    public String getAsDer() {
        return asDer;
    }

    @JsonProperty("as_der")
    public void setAsDer(String asDer) {
        this.asDer = asDer;
    }

    @JsonProperty("extensions")
    public Extensions getExtensions() {
        return extensions;
    }

    @JsonProperty("extensions")
    public void setExtensions(Extensions extensions) {
        this.extensions = extensions;
    }

    @JsonProperty("fingerprint")
    public String getFingerprint() {
        return fingerprint;
    }

    @JsonProperty("fingerprint")
    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    @JsonProperty("not_after")
    public Integer getNotAfter() {
        return notAfter;
    }

    @JsonProperty("not_after")
    public void setNotAfter(Integer notAfter) {
        this.notAfter = notAfter;
    }

    @JsonProperty("not_before")
    public Integer getNotBefore() {
        return notBefore;
    }

    @JsonProperty("not_before")
    public void setNotBefore(Integer notBefore) {
        this.notBefore = notBefore;
    }

    @JsonProperty("serial_number")
    public String getSerialNumber() {
        return serialNumber;
    }

    @JsonProperty("serial_number")
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @JsonProperty("subject")
    public Subject getSubject() {
        return subject;
    }

    @JsonProperty("subject")
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

}
