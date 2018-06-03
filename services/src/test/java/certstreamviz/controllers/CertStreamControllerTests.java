package certstreamviz.controllers;

import static org.mockito.BDDMockito.given;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import certstreamviz.models.CertStreamMessageView;
import certstreamviz.models.Coordinate;
import certstreamviz.models.certstreammessage.CertStreamMessage;
import certstreamviz.models.certstreammessage.Data;
import certstreamviz.models.certstreammessage.LeafCert;
import certstreamviz.services.CertStreamService;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@WebFluxTest(CertStreamController.class)
public class CertStreamControllerTests {

    private static final Coordinate COORDINATE = new Coordinate(0, 0);

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private CertStreamService certStreamService;

    private ObjectMapper om;

    private CertStreamMessage firstMsg;
    private CertStreamMessage fifthMsg;
    private CertStreamMessageView firstMsgView;
    private CertStreamMessageView fifthMsgView;

    @Before
    public void setUp() throws JsonParseException, JsonMappingException, IOException {
        InputStream is = this.getClass().getResourceAsStream("/certstreammessage");
        // TODO: get spring object mapper
        om = new ObjectMapper();
        CertStreamMessage msg = om.readValue(is, CertStreamMessage.class);
        firstMsg = createTestCertStreamMessage(msg, "1", null, null, null);
        fifthMsg = createTestCertStreamMessage(msg, "5", "source", "issuer", COORDINATE);
        firstMsgView = CertStreamMessageView.convertMsgToView.apply(firstMsg);
        fifthMsgView = CertStreamMessageView.convertMsgToView.apply(fifthMsg);

        given(this.certStreamService.getStream()).willReturn(Flux.just(firstMsg, msg, msg, msg, fifthMsg));
    }

    @Test
    public void streamCertsTest() {

        // https://github.com/spring-projects/spring-framework/blob/master/spring-webflux/src/test/java/org/springframework/web/reactive/result/method/annotation/SseIntegrationTests.java
        FluxExchangeResult<CertStreamMessageView> result = webTestClient.get().uri("/sse")
                .accept(MediaType.TEXT_EVENT_STREAM).exchange().expectStatus().isOk().expectHeader()
                .contentType("text/event-stream;charset=UTF-8").returnResult(CertStreamMessageView.class);

        StepVerifier.create(result.getResponseBody()).expectNext(firstMsgView).expectNextCount(3)
                .expectNext(fifthMsgView).thenCancel().verify();
    }

    // Using this to override the key fields for testing
    private CertStreamMessage createTestCertStreamMessage(CertStreamMessage csm, String cn, String source,
            String issuer, Coordinate coordinate) throws IOException {

        CertStreamMessage copy = om.readValue(om.writeValueAsString(csm), CertStreamMessage.class);
        Data data = copy.getData();
        LeafCert leafCert = data.getLeafCert();

        if (cn != null) {
            leafCert.getSubject().setCN(cn);
        }

        if (coordinate != null) {
            leafCert.setCoordinate(coordinate);
        }

        if (source != null) {
            data.getSource().setName(source);
        }

        if (issuer != null) {
            data.getChain().get(0).getSubject().setCN(issuer);
        }

        return copy;
    }

}