import java.io.Serializable;

class PrvClient extends ClientAccount implements Serializable{
	
	public PrvClient(String name, String address, String clientID, String creationDate, String accStatus){
		super(name, address, clientID, creationDate, accStatus);
	}
}