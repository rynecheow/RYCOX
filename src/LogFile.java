class LogFile
{
	protected String user;
	protected String action;

	public LogFile(String user, String action){
		this.user=user;
		this.action=action;
	}

	public String getUser(){
		return user;
	}

	public String getAction(){
		return action;
	}

	public void showLog(){
		System.out.println(getUser()+" "+getAction());
	}
}