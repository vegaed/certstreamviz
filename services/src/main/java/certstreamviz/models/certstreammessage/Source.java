
package certstreamviz.models.certstreammessage;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "name", "url" })
@JsonIgnoreProperties(ignoreUnknown = true)

public class Source {

    @JsonProperty("name")
    private String name;
    @JsonProperty("url")
    private String url;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Source() {
    }

    /**
     * 
     * @param name
     * @param url
     */
    public Source(String name, String url) {
        super();
        this.name = name;
        this.url = url;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

}
