package certstreamviz.models;

import java.util.Objects;
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
            view.coordinate = data.getLeafCert().getCoordinate();
            view.issuer = data.getChain().get(0).getSubject().getCN();
            view.source = data.getSource().getName();

            return view;
        }
    };

    @Override
    public boolean equals(Object o) {

        if (o == this)
            return true;
        if (!(o instanceof CertStreamMessageView)) {
            return false;
        }
        CertStreamMessageView csmv = (CertStreamMessageView) o;
        return Objects.equals(source, csmv.source) && Objects.equals(cn, csmv.cn) && Objects.equals(issuer, csmv.issuer)
                && Objects.equals(coordinate, csmv.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, cn, issuer, coordinate);
    }
}
