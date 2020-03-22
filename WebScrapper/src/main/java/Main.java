import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.FileInputStream;

public class Main{

    public static void main(String[] args) throws Exception {
        FileInputStream serviceAccount = new FileInputStream("ooop-6969-firebase-adminsdk-arx6j-fbc9ed80d0.json"); // get the firebase token

        FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://ooop-6969.firebaseio.com").build(); // connect to firebase by url link

        FirebaseApp.initializeApp(options);


        //////////////// URLs AND DATABASE REFERENCE NAME //////////////////////
        String carousellSocksRef = "SocksCarousell";
        String carousellSocksUrl = "https://sg.carousell.com/search/socks?";


        String carousellMilkRef = "MilkCarousell";
        String carousellMilkUrl = "https://sg.carousell.com/search/milk?";


        String corousellDiaperRef = "DiaperCarousell";
        String carousellDiaperUrl = "https://sg.carousell.com/search/diapers?";

        String q10SocksRef = "SocksQoo10";
        String q10SocksUrl = "https://www.qoo10.sg/s/SOCKS?keyword=socks&keyword_auto_change=";

        String q10MilkRef = "MilkQoo10";
        String q10MilkUrl = "https://www.qoo10.sg/s/MILK?keyword=Milk&keyword_auto_change=";

        String q10DiaperRef = "DiaperQoo10";
        String q10DiaperUrl = "https://www.qoo10.sg/s/DIAPER?keyword=Diaper&keyword_auto_change=";

        String rakutenSocksRef = "SocksRakuten";
        String rakutenSocksUrl = "https://global.rakuten.com/en/search/?k=socks&l-id=search_regular";

        String rakutenMilkRef = "MilkRakuten";
        String rakutenMilkUrl = "https://global.rakuten.com/en/search/?k=milk&l-id=search_regular";

        String rakutenDiapersRef = "DiapersRakuten";
        String rakutenDiapersUrl = "https://global.rakuten.com/en/search/?tl=0&k=diapers&l-id=search_regular";
        /////////////////////////////////////////////////////////////////////////////////////////////////////////


       /////////////////////////////////TO CRAWL///////////////////////////////////////////////////////////////////

        /////////////CAROUSELL////////////////////////////////
        CarousellCrawler carousellSocks = new CarousellCrawler("carousellSocksRef", carousellSocksUrl, "sock","SOCK","Sock");
        Thread carousellSocksThread = new Thread(carousellSocks);
        carousellSocksThread.start();

        CarousellCrawler carousellMilk = new CarousellCrawler("carousellMilkRef", carousellMilkUrl, "milk","MILK","Milk");
        Thread carousellMilkThread = new Thread(carousellMilk);
        carousellMilkThread.start();

        CarousellCrawler carousellDiaper = new CarousellCrawler("corousellDiaperRef", carousellDiaperUrl, "diaper","DIAPER","Diaper");
        Thread carousellDiaperThread = new Thread(carousellDiaper);
        carousellDiaperThread.start();

        /////////////////Qoo10////////////////
        QtenCrawler qtenSocks = new QtenCrawler("q10SocksRef", q10SocksUrl,"sock","SOCK","Sock");
        Thread qtenSokcsThread = new Thread(qtenSocks);
        qtenSokcsThread.start();

        QtenCrawler qtenMilk = new QtenCrawler("q10MilkRef", q10MilkUrl,"milk","MILK","Milk");
        Thread qtenMilkThread = new Thread(qtenMilk);
        qtenMilkThread.start();

        QtenCrawler qtenDiaper = new QtenCrawler("q10DiaperRef", q10DiaperUrl,"diaper","DIAPER","Diaper");
        Thread qtenDiaperThread = new Thread(qtenDiaper);
        qtenDiaperThread.start();

        /////////////RAKUTEN////////////////////////
        RakutenSocks rakutenSocks = new RakutenSocks("rakutenSocksRef", "sock","SOCK","Sock");
        Thread rakutenSockThread = new Thread(rakutenSocks);
        rakutenSockThread.start();

        RakutenMilk rakutenMilk = new RakutenMilk("rakutenMilkRef", "milk","MILK","Milk");
        Thread rakutenMilkThread = new Thread(rakutenMilk);
        rakutenMilkThread.start();

        RakutenDiapers rakutenDriapers = new RakutenDiapers("rakutenDiapersRef", "diaper","DIAPER","Diaper");
        Thread rakutenDiapersThread = new Thread(rakutenDriapers);
        rakutenDiapersThread.start();


        /////////////////////////////////////////////////////////////////////////////////////////////////////////////

        /// OPEN MAIN PAGE GUI ///////////////
        MainPage page = new MainPage();
        page.main(null);

    }

}


