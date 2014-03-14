import java.io.*;

@SuppressWarnings("serial")
class Packaging implements Serializable{
	private String pkgCode;                    //initialise variables
	private String progCode;

	public Packaging(String pkgCode, String progCode){ //constructor
		this.pkgCode = pkgCode;
		this.progCode = progCode;
	}

//accessors
	public String getPkgCode(){ //to return package's code
		return pkgCode;
	}

	public String getProgCode(){ //to return programme's code
   
		return progCode;
	}

}


