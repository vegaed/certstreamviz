
package certstreamviz.models.certstreammessage;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

//Orignially Created using http://www.jsonschema2pojo.org/
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "data", "message_type" })
public class CertStreamMessage {

    @JsonProperty("data")
    private Data data;
    @JsonProperty("message_type")
    private String messageType;

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

}
