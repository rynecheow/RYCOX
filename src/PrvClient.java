import java.io.Serializable;

@SuppressWarnings("serial")
class PrvClient extends ClientAccount implements Serializable {

    public PrvClient(String name, String address, String clientID, String accStatus) {
        super(name, address, clientID, accStatus);
    }
}