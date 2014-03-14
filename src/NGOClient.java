import java.io.Serializable;

@SuppressWarnings("serial")
class NGOClient extends ClientAccount implements Serializable {

    public NGOClient(String name, String address, String clientID, String accStatus) {
        super(name, address, clientID, accStatus);
    }
}