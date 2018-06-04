package certstreamviz.services;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.stream.Collectors;


import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import certstreamviz.models.Coordinate;
import certstreamviz.models.certstreammessage.CertStreamMessage;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class CertStreamServiceTest {

    @Mock
    private IGeolocateIpAddressService service;

    private CertStreamService certStreamService;

    private String actualJson;
    private CertStreamMessage msg;

    @Before
    public void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);
        BDDMockito.given(service.geolocateIpAddress("badhost")).willReturn(Optional.empty());
        BDDMockito.given(service.geolocateIpAddress("www.google.com")).willReturn(Optional.of(new Coordinate(0, 0)));
        BDDMockito.given(service.geolocateIpAddress("wexsav.com")).willReturn(Optional.of(new Coordinate(0, 0)));

        certStreamService = Mockito.spy(new CertStreamService(service));

        InputStream is = this.getClass().getResourceAsStream("/certstreammessage");

        actualJson = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)).lines()
                .collect(Collectors.joining("\n"));

        msg = new ObjectMapper().readValue(actualJson, CertStreamMessage.class);
    }

    @Test
    public void testGetStreamBadValueInStrema() {
        // should get 1 less
        BDDMockito.given(this.certStreamService.certStreamBridge())
                .willReturn(Flux.just(actualJson, "bad json", actualJson));
        StepVerifier.create(certStreamService.getStream()).expectNextCount(2).expectComplete().verify();
    }

    @Test
    public void testGetStream() {
        BDDMockito.given(this.certStreamService.certStreamBridge())
                .willReturn(Flux.just(actualJson, actualJson, actualJson));
        StepVerifier.create(certStreamService.getStream()).expectNextCount(1).consumeNextWith(m -> {
            assertThat(m.getData().getLeafCert().getCoordinate(), is(new Coordinate(0, 0)));
        }).expectNextCount(1).thenCancel().verify();

    }

    @Test
    public void testGetStreamWithUnkownHost() {
        BDDMockito.given(this.certStreamService.certStreamBridge())
                .willReturn(Flux.just(actualJson, actualJson.replaceAll("wexsav.com", "bad url"), actualJson));
    
        StepVerifier.create(certStreamService.getStream()).expectNextCount(1).consumeNextWith(m -> {
            assertThat(m.getData().getLeafCert().getCoordinate(), nullValue());
        }).expectNextCount(1).thenCancel().verify();

    }

}