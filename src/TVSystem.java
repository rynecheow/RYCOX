import java.io.BufferedReader;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class TVSystem {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String username = "", password = "", menu1_Opt = "", menu2_Opt = "";
        int i = 0, f = 0, u = 0, option1_1 = 0, option2_1 = 0;
        boolean login = false;

        //Data List
        //User
        List<Users> userList = new LinkedList<Users>();
        userList.add(new Administrators("admin", "nimda"));
        userList.add(new FrontdeskStaffs("staff", "123abc"));

        //Client
        List<ClientAccount> clientList = new LinkedList<ClientAccount>();
        clientList.add(new IndividualClient("Izhar", 39, "631220-05-1243", "9, Trafalgar Road", "I00001", "17th Apr 2011", "ACTIVE"));
        clientList.add(new GovClient("Dept. of Education", "12, Long Fave Strt.", "G000001", "17th Apr 2011", "INACTIVE"));
        clientList.add(new NGOClient("NGO", "56, Taylor's Street", "N000001", "17th Apr 2011", "ACTIVE"));
        clientList.add(new PrvClient("Private Organisation", "Address", "P000001", "17th Apr 2011", "ACTIVE"));

        // Services
        List<Service> servList = new LinkedList<Service>();
        servList.add(new Service("S000001", "I00001", "D999999", "5, Jalan Sungai Beranang", "17th Apr 2011"));
        servList.add(new Service("S000002", "G00001", "D999998", "Lot 1-3 Starhill", "17th Apr 2011"));
        servList.add(new Service("S000003", "N00001", "D999997", "Lot 3-10 Jalan Taylor", "17th Apr 2011"));
        servList.add(new Service("S000004", "P00001", "D999996", "32 Jalan Kota Kemuning ", "17th Apr 2011"));

        //Subscription
        List<Subscription> subsList = new LinkedList<Subscription>();
        subsList.add(new Subscription("S000001", 1));
        subsList.add(new Subscription("S000001", 2));
        subsList.add(new Subscription("S000001", 3));
        subsList.add(new Subscription("S000002", 1));
        subsList.add(new Subscription("S000003", 1));
        subsList.add(new Subscription("S000004", 1));

        //TV Packages
        List<TVPackage> pkgList = new LinkedList<TVPackage>();


        //TV Programmes
        List<TVProgramme> prgList = new LinkedList<TVProgramme>();

        //Logs
        List<LogFile> logList = new LinkedList<LogFile>();
        LogFile log = new LogFile("", "");

        //Special(for function use)
        List<Integer> t = new LinkedList<Integer>();

        //file handling for data
        File client_file = new File("clients.dat");
        boolean existFile = client_file.exists();
        StringBuffer contents = new StringBuffer();
        BufferedReader reader = null;


    }
}
