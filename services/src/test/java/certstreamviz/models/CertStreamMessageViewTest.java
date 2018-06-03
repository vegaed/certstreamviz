package certstreamviz.models;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import certstreamviz.models.certstreammessage.CertStreamMessage;

public class CertStreamMessageViewTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testSerialization() throws IOException, JSONException {

        CertStreamMessageView csmv = new CertStreamMessageView();
        csmv.coordinate = new Coordinate(0, 0);
        csmv.issuer = "issuer";
        csmv.source = "source";
        csmv.cn = "cn";

        String json = objectMapper.writeValueAsString(csmv);

        String expectedJson = "{cn:\"cn\", source:\"source\", issuer:\"issuer\", coordinate:{latitude:0,longitude:0} }";
        JSONAssert.assertEquals(expectedJson, json, true);
    }

    @Test
    public void testSerializationWithMissingCoordinate() throws IOException, JSONException {

        CertStreamMessageView csmv = new CertStreamMessageView();
        csmv.issuer = "issuer";
        csmv.source = "source";
        csmv.cn = "cn";

        String json = objectMapper.writeValueAsString(csmv);

        String expectedJson = "{cn:\"cn\", source:\"source\", issuer:\"issuer\", coordinate:null }";
        JSONAssert.assertEquals(expectedJson, json, true);
    }

    @Test
    public void testConverterFunction() throws IOException {
        InputStream is = this.getClass().getResourceAsStream("/certstreammessage");
        // TODO: get spring object mapper
        ObjectMapper om = new ObjectMapper();
        CertStreamMessage msg = om.readValue(is, CertStreamMessage.class);

        CertStreamMessageView view = CertStreamMessageView.convertMsgToView.apply(msg);
        CertStreamMessageView expected = new CertStreamMessageView();
        expected.cn = "wexsav.com";
        expected.issuer = "Let's Encrypt Authority X3";
        expected.source = "Google 'Argon2018' log";
        assertThat(view, is(expected));
    }
}
