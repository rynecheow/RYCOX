import java.io.Serializable;

@SuppressWarnings("serial")
class GovClient extends ClientAccount implements Serializable {

    public GovClient(String name, String address, String clientID, String accStatus) {
        super(name, address, clientID, accStatus);
    }
}