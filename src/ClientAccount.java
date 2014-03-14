import java.io.Serializable;

@SuppressWarnings("serial")
abstract class ClientAccount implements Serializable{
	protected String name;
	protected String billingAddress;
	protected String clientID;
	protected String creationDate;
	protected String accStatus = "INACTIVE";

	public ClientAccount(String name, String billingAddress, String clientID, String creationDate, String accStatus){
		this.name=name;
		this.billingAddress=billingAddress;
		this.clientID=clientID;
		this.creationDate=creationDate;
		this.accStatus=accStatus;
	}

	public String getName(){
		return name;
	}

	public String getBillingAddress(){
		return billingAddress;
	}

	public String getClientID(){
		return clientID;
	}

	public String getCreationDate(){
		return creationDate;
	}

	public String getAccStatus(){
		return accStatus;
	}
	
	public void setName(String s){
		this.name=s;
	}

	public void setBillingAddress(String b){
		this.billingAddress=b;
	}

	public void changeAccStatus(String as){
		this.accStatus=as;
	}
	
	public boolean terminationStatus(){
			if(this.accStatus.equalsIgnoreCase("TERMINATED"))
				return true;
			else
				return false;
	}

	public void printClient(){
		System.out.println("Client Name:\t"+getName());
		System.out.println("Client Address:\t"+getBillingAddress());
		System.out.println("Client ID:\t"+getClientID());
		System.out.println("Creation Date:\t"+getCreationDate());
		System.out.println("Account Status:\t"+getAccStatus());
		System.out.println("");
	}
}