import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.List;

public class QtenCrawler extends WebHelper implements Runnable{

    private final String src = "Qoo10";
    private String words;
    private String cost;
    private String link;
    private String url;
    private String databaseName;
    private String keyword1;
    private String keyword2;
    private String keyword3;

    public QtenCrawler(String databaseName, String url, String keyword1, String keyword2, String keyword3){
        super();
        this.databaseName = databaseName;
        this.keyword1 = keyword1;
        this.keyword2 = keyword2;
        this.keyword3 = keyword3;
        this.url = url;
    }

    @Override
    public void crawl()throws Exception {

        DatabaseHandler adder = new DatabaseHandler(); // call DatabaseHandler to get methods to push to database
        WebDriver driver = new ChromeDriver(); //initialise webdriver
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        driver.get(url); // go to url
        Thread.sleep(800);



        for (int i = 0; i < 7; i++) {
            try {
                List<WebElement> all = driver.findElements(By.className("sbj")); // get list of all elements under class sbj
                List<WebElement> allprice = driver.findElements(By.className("td_prc")); // get list of all elements under class td_prc
                List<WebElement> alllink = driver.findElements(By.className("sbj")); // get list of all elements under class sbj

                int count = 0;
                for (WebElement each : allprice) { // loop for each WebElement 'each' in the list 'allprice'
                    cost = each.findElement(By.className("prc")).findElement(By.tagName("strong")).getText(); // get the element containing the price
                    words = all.get(count).getText(); // get the element containing the header text. increment to get next item of list
                    link = alllink.get(count).findElement(By.tagName("a")).getAttribute("href"); // get element containing the item link
                    count++;
                    if (words.contains(keyword1) || words.contains(keyword2) || words.contains(keyword3)) { //filter for keywords
                        cost = cost.replace("S$", "");
                        System.out.println(words);
                        System.out.println(cost);
                        System.out.println(link);
                        adder.pushDatabase(words, cost, src, link, databaseName); // put to database
                    }
                }

            } catch (Exception e) {
                i++;
            }
            driver.findElement(By.xpath("//*[@id=\"NextPage\"]")).click(); // NEXT PAGE
            Thread.sleep(1600);
        }
        Thread.sleep((1000)); // wait
        driver.close(); // close browser
    }

    @Override
    public void run() { // MAKE NEW THREAD
        try {
            crawl();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
