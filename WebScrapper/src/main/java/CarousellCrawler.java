import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class CarousellCrawler extends WebHelper implements Runnable {

    private String databaseName;
    private String src = "Carousell";
    private String words;
    private String cost;
    private String link;
    private String keyword1;
    private String keyword2;
    private String keyword3;
    private String url;

    public CarousellCrawler(String databaseName,String url, String keyword1, String keyword2, String keyword3) {
        super();
        this.databaseName = databaseName;
        this.keyword1 = keyword1;
        this.keyword2 = keyword2;
        this.keyword3 = keyword3;
        this.url = url;
    }


    @Override
    public void crawl() throws Exception {
        DatabaseHandler adder = new DatabaseHandler();// call DatabaseHandler to get methods to push to database
        WebDriver driver = new ChromeDriver(); //initialise webdriver
        driver.get(url); // go to url
        Thread.sleep(500); // wait to load

        for (int i = 0; i<2; i++) // loop crawl how many pages to crawl
        {
            driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div[2]/main/div/button")).click();// go next page
            Thread.sleep(1600); // wait to load
        }
        Thread.sleep(1000);
        List<WebElement> all = driver.findElements(By.className("TpQXuJG_eo")); // get all the things inside this container

        int i = 1;
        for(WebElement each : all){
            try{

                words = each.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div[2]/main/div/div/div["+i+"]/div[1]/a[2]/p[1]")).getText(); // get the name in all html class. increment to the next container

                cost = each.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div[2]/main/div/div/div["+i+"]/div[1]/a[2]/p[2]")).getText();// get the price in all html class. increment to the next container

                link = each.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div[2]/main/div/div/div["+i+"]/div[1]/a[2]")).getAttribute("href"); //get the links. increment to get next

                if (words.contains(keyword1) || words.contains(keyword2) || words.contains(keyword3)){ // filter by keywords

                    System.out.println(words);
                    cost = cost.replace("S$", ""); // remove dollar sign
                    System.out.println(cost);
                    System.out.println(link);
                    adder.pushDatabase(words, cost, src, link, databaseName); // add to database
                }
                i++;
            }
            catch (Exception e)
            {
                i++;
            }
        }
        Thread.sleep(1000); // let user see the end before closing
        driver.quit(); // close browser
    }

    @Override
    public void run() {  // to make new thread
        try {
            crawl();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
