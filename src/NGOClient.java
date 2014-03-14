import java.io.Serializable;

class NGOClient extends ClientAccount implements Serializable{
	
	public NGOClient(String name, String address, String clientID, String creationDate, String accStatus){
		super(name, address, clientID, creationDate, accStatus);
	}
}