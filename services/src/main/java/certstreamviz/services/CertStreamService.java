package certstreamviz.services;

import java.io.IOException;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;

import certstreamviz.models.Coordinate;
import certstreamviz.models.certstreammessage.CertStreamMessage;
import io.calidog.certstream.CertStream;
import reactor.core.publisher.Flux;

@Service
public class CertStreamService {
    private static final Logger logger = LoggerFactory.getLogger(CertStreamService.class);

    // @Autowired
    // private MappingJackson2HttpMessageConverter springJacksonConverter;

    private ObjectMapper objectMapper;
    private IGeolocateIpAddressService geolocateIpAddressService;

    CertStreamService(IGeolocateIpAddressService geolocateIpAddressService) {
        // TODO: get the singleton object mapper used in spring
        objectMapper = new ObjectMapper();// springJacksonConverter.getObjectMapper();
        this.geolocateIpAddressService = geolocateIpAddressService;
    }

    public Flux<CertStreamMessage> getStream() {

        // TODO: consider hybrid push/pull if we start to store the stream in a
        // repository
        // http://projectreactor.io/docs/core/snapshot/reference/#_hybrid_push_pull_model
        //
        // Flux.create is used to bridge exsiting apis with reactor
        Flux<String> bridge = Flux.create(sink -> {
            CertStream.onMessageString(msg -> sink.next(msg));
        });

        // flatMap let you return an empty
        return bridge.flatMap(msg -> {
            try {
                return Flux.just(objectMapper.readValue(msg, CertStreamMessage.class));
            } catch (IOException e) {
                logger.debug("Unable to convert message to object", e);
                return Flux.empty();
            }
        }).map(msg -> {

            Optional<Coordinate> coordinate = geolocateIpAddressService
                    .geolocateIpAddress(msg.getData().getLeafCert().getSubject().getCN());
            coordinate.ifPresent(
                    c -> msg.setAdditionalProperty("Coordinate", new Coordinate(c.getLatitude(), c.getLongitude())));

            return msg;
        });

    }
}