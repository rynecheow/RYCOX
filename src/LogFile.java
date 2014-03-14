class LogFile {
    private String date;
    private String user;
    private String action;

    public LogFile(String date, String user, String action) {
        this.date = date;
        this.user = user;
        this.action = action;
    }

    public String getUser() {
        return user;
    }

    public String getAction() {
        return action;
    }

    public void setDate(String d) {
        this.date = d;
    }

    public String getDate() {
        return date;
    }

    public void showLog() {
        System.out.println(getDate() + " " + getUser() + " " + getAction());
    }
}