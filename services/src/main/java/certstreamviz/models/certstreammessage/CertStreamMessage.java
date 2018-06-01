
package certstreamviz.models.certstreammessage;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

//Created using http://www.jsonschema2pojo.org/
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "data",
    "message_type"
})
public class CertStreamMessage {

    @JsonProperty("data")
    private Data data;
    @JsonProperty("message_type")
    private String messageType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public CertStreamMessage() {
    }

    /**
     * 
     * @param data
     * @param messageType
     */
    public CertStreamMessage(Data data, String messageType) {
        super();
        this.data = data;
        this.messageType = messageType;
    }

    @JsonProperty("data")
    public Data getData() {
        return data;
    }


    @JsonProperty("data")
    public void setData(Data data) {
        this.data = data;
    }

    @JsonProperty("message_type")
    public String getMessageType() {
        return messageType;
    }

    @JsonProperty("message_type")
    public void setMessageType(String messageType) {
        this.messageType = messageType;
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
