
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
    "C",
    "CN",
    "L",
    "O",
    "OU",
    "ST",
    "aggregated"
})
public class Subject_ {

    @JsonProperty("C")
    private Object c;
    @JsonProperty("CN")
    private String cN;
    @JsonProperty("L")
    private Object l;
    @JsonProperty("O")
    private Object o;
    @JsonProperty("OU")
    private String oU;
    @JsonProperty("ST")
    private Object sT;
    @JsonProperty("aggregated")
    private String aggregated;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Subject_() {
    }

    /**
     * 
     * @param oU
     * @param c
     * @param cN
     * @param aggregated
     * @param sT
     * @param o
     * @param l
     */
    public Subject_(Object c, String cN, Object l, Object o, String oU, Object sT, String aggregated) {
        super();
        this.c = c;
        this.cN = cN;
        this.l = l;
        this.o = o;
        this.oU = oU;
        this.sT = sT;
        this.aggregated = aggregated;
    }

    @JsonProperty("C")
    public Object getC() {
        return c;
    }

    @JsonProperty("C")
    public void setC(Object c) {
        this.c = c;
    }

    @JsonProperty("CN")
    public String getCN() {
        return cN;
    }

    @JsonProperty("CN")
    public void setCN(String cN) {
        this.cN = cN;
    }

    @JsonProperty("L")
    public Object getL() {
        return l;
    }

    @JsonProperty("L")
    public void setL(Object l) {
        this.l = l;
    }

    @JsonProperty("O")
    public Object getO() {
        return o;
    }

    @JsonProperty("O")
    public void setO(Object o) {
        this.o = o;
    }

    @JsonProperty("OU")
    public String getOU() {
        return oU;
    }

    @JsonProperty("OU")
    public void setOU(String oU) {
        this.oU = oU;
    }

    @JsonProperty("ST")
    public Object getST() {
        return sT;
    }

    @JsonProperty("ST")
    public void setST(Object sT) {
        this.sT = sT;
    }

    @JsonProperty("aggregated")
    public String getAggregated() {
        return aggregated;
    }

    @JsonProperty("aggregated")
    public void setAggregated(String aggregated) {
        this.aggregated = aggregated;
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
