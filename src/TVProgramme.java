import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

@SuppressWarnings("serial")
class TVProgramme implements Serializable {

    private String progCode;                    //initialise variables
    private String progTitle;
    private String desc;
    private String contentOrigin;
    private String creationDate;
    private String terminationDate = "N/A";
    private String prgStatus = "ACTIVE";
    private String viewerStatus;
    private String type;
    private String notes;

    public TVProgramme(String progCode, String progTitle, String desc, String contentOrigin, String prgStatus, String viewerStatus, String type, String notes) { //constructor
        this.progCode = progCode;
        this.progTitle = progTitle;
        this.desc = desc;
        this.contentOrigin = contentOrigin;
        this.creationDate = DateFormat.getInstance().format(new Date());
        this.terminationDate = "N/A";
        this.prgStatus = prgStatus;
        this.viewerStatus = viewerStatus;
        this.type = type;
        this.notes = notes;
    }

    public TVProgramme(String progCode, String progTitle, String desc, String contentOrigin, String creationDate, String terminationDate, String prgStatus, String viewerStatus, String type, String notes) { //constructor
        this.progCode = progCode;
        this.progTitle = progTitle;
        this.desc = desc;
        this.contentOrigin = contentOrigin;
        this.creationDate = DateFormat.getInstance().format(new Date());
        this.terminationDate = "N/A";
        this.prgStatus = prgStatus;
        this.viewerStatus = viewerStatus;
        this.type = type;
        this.notes = notes;
    }

    //accessors
    public String getProgCode() {//to return programme's code
        return progCode;
    }

    public String getProgTitle() { //to return programme's title
        return progTitle;
    }

    public String getDesc() { //to return the description of the programme
        return desc;
    }

    public String getContentOrigin() { //to return programme's content come from which country
        return contentOrigin;
    }

    public String getCreationDate() { //to return the creation date of a programme
        return creationDate;
    }

    public String getTerminationDate() {  //to return the termination date of a programme

        return terminationDate;

    }

    public String getPrgStatus() { //to return the programme's status
        return prgStatus;
    }

    public String getViewerStatus() { //to return the programme's viewer status
        return viewerStatus;
    }

    public String getType() { //to return the programme's type

        return type;
    }

    public String getNotes() { //to return the programme's notes
        return notes;
    }

    public void printList() {  //to print out all the information of a TV programme
        System.out.println(" Programme Code: " + progCode + "\n Programme Title: " + progTitle + "\n Description: " + desc + "\n Content Origin: " + contentOrigin + "\n Creation Date: " + creationDate + "\n Status: " + prgStatus + "\n Viewer Status: " + viewerStatus + "\n Type: " + type + "\n Notes: " + notes + "\n");
    }

    //mutators
    public void setProgCode(String progCode) { //to change programme's code
        this.progCode = progCode;
    }

    public void setProgTitle(String progTitle) { //to change programme's title
        this.progTitle = progTitle;
    }

    public void setDesc(String desc) { //to change programme's description
        this.desc = desc;
    }

    public void setPrgStatus(String prgStatus) {  //to change programme's status
        this.prgStatus = prgStatus;
    }

    public void setViewerStatus(String viewerStatus) { //to change programme's viewer status
        this.viewerStatus = viewerStatus;
    }

    public void setType(String type) { //to change programme's type
        this.type = type;
    }

    public void setNotes(String notes) {//to change programme's notes
        this.notes = notes;
    }

    public void setTerminationDate(String terminationDate) {

        this.terminationDate = terminationDate;

    }

    public void setContentOrigin(String contentOrigin) {
        this.contentOrigin = contentOrigin;
    }
}

/**************************************************************************
 * (C) Copyright 2012 by Ryne Cheow Yeong Chi , Ng Jia Jiun               *
 * Lai Li Hao. All rights reserved.                                       *
 *                                                                        *
 * DISCLAIMER: The writer of this particular program have used their      *
 * best efforts in preparing this program. These efforts include the      *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The writers make                     *
 * no warranty of any kind, expressed or implied, with regard to this     *
 * program. The writers shall not be liable in                            *
 * any event for incidental or                                            *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of this program.                       *
 *************************************************************************/