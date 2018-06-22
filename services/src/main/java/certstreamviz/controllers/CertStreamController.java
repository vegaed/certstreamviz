package certstreamviz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import certstreamviz.models.CertStreamMessageView;
import certstreamviz.services.CertStreamService;
import reactor.core.publisher.Flux;

@RestController
public class CertStreamController {

    private CertStreamService certStreamService;

    @Autowired
    CertStreamController(CertStreamService certStreamService) {
        this.certStreamService = certStreamService;
    }

    // TODO: Support LAST-EVENT-ID if we start to store the stream in a repository
    // Good explaination of Server-Sent Events:
    // https://www.html5rocks.com/en/tutorials/eventsource/basics/
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<CertStreamMessageView>> streamCerts() {
        return certStreamService.getStream().map(msg -> CertStreamMessageView.convertMsgToView.apply(msg))
                .map(s -> ServerSentEvent.builder(s).build());
    }
}
