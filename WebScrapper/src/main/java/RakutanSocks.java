import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class RakutenSocks extends WebHelper implements Runnable{

    //private String url = "https://global.rakuten.com/en/search/?k=socks&l-id=search_regular";
    //////////////////////////////////////////////////////////////////////////////////////////
    private final String source = "Rakuten";
    //private String url = "https://www.qoo10.sg/s/SOCKS?keyword=socks&keyword_auto_change=";
    private String databaseName;
    private String keyword1;
    private String keyword2;
    private String keyword3;
    private String url;

    public RakutenSocks(String databaseName, String keyword1, String keyword2, String keyword3){
        this.databaseName = databaseName;
        this.keyword1 = keyword1;
        this.keyword2 = keyword2;
        this.keyword3 = keyword3;

    }

    @Override
    public void crawl(){
        try
        {
            DatabaseHandler rakutanDatabase = new DatabaseHandler();
            for(int i=1;i<=6;i++) {
                if(i == 1) {
                    url = "https://global.rakuten.com/en/search/?k=socks&l-id=search_regular"; // other than first time... url will be url = page.getNextUrl(url)
                    int counter = 0;
                    Document doc = Jsoup.connect(url).get();
                    Elements row = doc.getElementsByClass("b-content b-fix-2lines").select("a");//// get class element
                    Elements row2 = doc.getElementsByClass("b-content m-shop-top-text").select("span");
                    for (Element rows : row)
                    {
                        if(row.text().contains(keyword1) || row.text().contains(keyword2) || row.text().contains(keyword3)){// filter keywords
                            System.out.println(rows.text());
                            System.out.println("----------------------");
                            System.out.println(rows.attr("href"));
                            System.out.println("----------------------");
                            String header = rows.text();
                            String price = row2.get(counter).ownText();
                            String source = "Rakuten";
                            String link = "https://global.rakuten.com"+rows.attr("href");
                            counter += 2;
                            rakutanDatabase.pushDatabase(header,price,source,link, databaseName);// add to database
                        }


                    }
                }
                if(i > 1){
                    url = "https://global.rakuten.com/en/search/?k=socks&p="+i+"&l-id=search_regular"; // other than first time... url will be url = page.getNextUrl(url)
                    int counter = 0;
                    Document doc = Jsoup.connect(url).get();
                    Elements row = doc.getElementsByClass("b-content b-fix-2lines").select("a");//// get class element
                    Elements row2 = doc.getElementsByClass("b-content m-shop-top-text").select("span");
                    for (Element rows : row)
                    {

                        if(row.text().contains(keyword1) || row.text().contains(keyword2) || row.text().contains(keyword3)){// filter keywords
                            System.out.println(rows.text());
                            System.out.println("----------------------");
                            System.out.println(rows.attr("href"));
                            System.out.println("----------------------");
                            String header = rows.text();
                            String price = row2.get(counter).ownText();
                            String source = "Rakuten";
                            String link = "https://global.rakuten.com"+rows.attr("href");
                            counter += 2;
                            rakutanDatabase.pushDatabase(header,price,source,link,databaseName);// add to database
                        }


                    }

                }
            }
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public void run() { // make new thread
        crawl();
    }
}
