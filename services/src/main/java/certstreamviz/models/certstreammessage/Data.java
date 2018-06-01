
package certstreamviz.models.certstreammessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;

import certstreamviz.models.CertStreamMessageView;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "cert_index",
    "cert_link",
    "chain",
    "leaf_cert",
    "seen",
    "source",
    "update_type"
})
public class Data {

    @JsonProperty("cert_index")
    private Integer certIndex;
    @JsonProperty("cert_link")
    private String certLink;
    @JsonProperty("chain")
    private List<Chain> chain = null;
    @JsonProperty("leaf_cert")
    private LeafCert leafCert;
    @JsonProperty("seen")
    private Double seen;
    @JsonProperty("source")
    private Source source;
    @JsonProperty("update_type")
    private String updateType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Data() {
    }

    /**
     * 
     * @param certIndex
     * @param source
     * @param chain
     * @param certLink
     * @param seen
     * @param leafCert
     * @param updateType
     */
    public Data(Integer certIndex, String certLink, List<Chain> chain, LeafCert leafCert, Double seen, Source source, String updateType) {
        super();
        this.certIndex = certIndex;
        this.certLink = certLink;
        this.chain = chain;
        this.leafCert = leafCert;
        this.seen = seen;
        this.source = source;
        this.updateType = updateType;
    }

    @JsonProperty("cert_index")
    public Integer getCertIndex() {
        return certIndex;
    }

    @JsonProperty("cert_index")
    public void setCertIndex(Integer certIndex) {
        this.certIndex = certIndex;
    }

    @JsonProperty("cert_link")
    public String getCertLink() {
        return certLink;
    }

    @JsonProperty("cert_link")
    public void setCertLink(String certLink) {
        this.certLink = certLink;
    }

    @JsonProperty("chain")
    public List<Chain> getChain() {
        return chain;
    }

    @JsonProperty("chain")
    public void setChain(List<Chain> chain) {
        this.chain = chain;
    }
    @JsonProperty("leaf_cert")
    public LeafCert getLeafCert() {
        return leafCert;
    }

    @JsonProperty("leaf_cert")
    public void setLeafCert(LeafCert leafCert) {
        this.leafCert = leafCert;
    }

    @JsonProperty("seen")
    public Double getSeen() {
        return seen;
    }

    @JsonProperty("seen")
    public void setSeen(Double seen) {
        this.seen = seen;
    }

    @JsonProperty("source")
    public Source getSource() {
        return source;
    }

    @JsonProperty("source")
    public void setSource(Source source) {
        this.source = source;
    }

    @JsonProperty("update_type")
    public String getUpdateType() {
        return updateType;
    }

    @JsonProperty("update_type")
    public void setUpdateType(String updateType) {
        this.updateType = updateType;
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
