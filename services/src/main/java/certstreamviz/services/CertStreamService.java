package certstreamviz.services;

import java.io.IOException;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import certstreamviz.models.Coordinate;
import certstreamviz.models.certstreammessage.CertStreamMessage;
import certstreamviz.models.certstreammessage.LeafCert;
import io.calidog.certstream.CertStream;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

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
        Flux<String> bridge = certStreamBridge();
        // flatMap let you return an empty
        return bridge.flatMap(msg -> {
            try {
                return Flux.just(objectMapper.readValue(msg, CertStreamMessage.class));
            } catch (IOException e) {
                logger.debug("Unable to convert message to object", e);
                return Flux.empty();
            }
        }).map(msg -> {

            LeafCert leafCert = msg.getData().getLeafCert();
			Optional<Coordinate> coordinate = geolocateIpAddressService
                    .geolocateIpAddress(leafCert.getSubject().getCN());
            coordinate.ifPresent(
                    coord -> leafCert.setCoordinate(new Coordinate(coord.getLatitude(), coord.getLongitude())));

            return msg;
        });

    }

    protected Flux<String> certStreamBridge() {
        return Flux.create(sink -> {
            CertStream.onMessageString(msg -> sink.next(msg));
        });
    }
}