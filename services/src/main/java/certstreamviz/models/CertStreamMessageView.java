package certstreamviz.models;

import java.util.function.Function;

import certstreamviz.models.certstreammessage.CertStreamMessage;
import certstreamviz.models.certstreammessage.Data;

//Started out using this for JsonView but I wanted to be not only control the data exposed but also how the data was shaped.
public class CertStreamMessageView {
    public String source;
    public String cn;
    public String issuer;
    public Coordinate coordinate;

    public static Function<CertStreamMessage, CertStreamMessageView> convertMsgToView = new Function<CertStreamMessage, CertStreamMessageView>() {

        public CertStreamMessageView apply(CertStreamMessage msg) {
            CertStreamMessageView view = new CertStreamMessageView();

            Data data = msg.getData();
            view.cn = data.getLeafCert().getSubject().getCN();
            view.coordinate = (Coordinate) msg.getAdditionalProperties().get("Coordinate");
            view.issuer = data.getChain().get(0).getSubject().getCN();
            view.source = data.getSource().getName();

            return view;
        }
    };
}
