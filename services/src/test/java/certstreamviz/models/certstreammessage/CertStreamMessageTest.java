package certstreamviz.models.certstreammessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class CertStreamMessageTest {
    //TODO: use same object mapper as spring boot
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testSerializationDeserialization() throws IOException, JSONException {
        InputStream is = this.getClass().getResourceAsStream("/certstreammessage");

        String actualJson = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)).lines()
                .collect(Collectors.joining("\n"));
        CertStreamMessage msg = objectMapper.readValue(actualJson, CertStreamMessage.class);
        String newJson = objectMapper.writeValueAsString(msg);

        JSONAssert.assertEquals(actualJson, newJson, false);
    }

}