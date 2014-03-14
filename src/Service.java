import java.io.Serializable;

@SuppressWarnings("serial")
class Service implements Serializable{
	
	private String smartCardNo;
	private String clientID;
	private String decoderNo;
	private String address;
	private String servStatus = "Inactive";
	private String registrationDate;
	private String terminationDate= "N/A";
	private double currOstBal = 0;
	private double prevBillAmount = 0;
		
	public Service (String smartCardNo, String clientID, String decoderNo, String address, String registrationDate){
		this.smartCardNo = smartCardNo;
		this.clientID = clientID;
		this.decoderNo = decoderNo;
		this.address = address;
		this.registrationDate = registrationDate;
	}
		
	public String getRegDate(){
		return registrationDate;
	}
		
	public String getTermDate(){
		return terminationDate;
	}
		
	public double getBal(){
		return currOstBal;
	}
		
	public double getPrevBill(){
			return prevBillAmount;
	}
		
	public String getSmartCardNo(){
		return smartCardNo;
	}
	
	public String getClientID(){
		return clientID;
	}
		
	public String getDecodeNo(){
		return decoderNo;
	}
		
	public String getServStatus(){
		return servStatus;
	}
		
	public String getAddress(){
		return address;
	}
		
	public void setSmartCardNo(String smartCardNo){
		
		this.smartCardNo = smartCardNo;
	}
	
	public void setClientID(String s){
		this.clientID = s;
	}
	
	public void setDecoderNo(String decoderNo){
		this.decoderNo = decoderNo;
	}
		
	public void setAddress(String address){
		this.address = address;
	}
		
	public void setServStatus(String servStatus){
		String s= servStatus.toLowerCase();
		if (s.equals("active"))
			this.servStatus = "Active";
			
		else if (s.equals("no"))
			this.servStatus = "Inactive";
	}
		
	public void setTermDate(String terminationDate){
		this.terminationDate = terminationDate;
	}
	
	public void printService(){
		System.out.println("Client ID: "+ clientID);
		System.out.println("Smart Card Number: "+ smartCardNo);
		System.out.println("Decoder Number: "+ decoderNo);
		System.out.println("Status: "+ servStatus);
		System.out.println("Registration Date: "+ registrationDate);
		System.out.println("Termination Date: "+ terminationDate);
		System.out.println("Current Outstanding Balance: "+ currOstBal);
		System.out.println("Previous Bill Amount: "+ prevBillAmount);
		System.out.println("Address: "+ address);
	}
}