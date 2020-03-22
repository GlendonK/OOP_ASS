abstract class WebHelper {

    public WebHelper() {
        System.setProperty("webdriver.chrome.driver", "chromedriver_win32\\chromedriver.exe"); // SET PATH TO CHROME DRIVER
    }

    abstract void crawl() throws Exception;
}