import java.io.Serializable;
class GovClient extends ClientAccount implements Serializable{
	
	public GovClient(String name, String address, String clientID, String creationDate, String accStatus){
		super(name, address, clientID, creationDate, accStatus);
	}
}