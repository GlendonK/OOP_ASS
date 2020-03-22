import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class RakutenMilk extends WebHelper implements Runnable{
    private final String source = "Rakuten";
    private String databaseName;
    private String keyword1;
    private String keyword2;
    private String keyword3;
    private String url;

    public RakutenMilk(String databaseName, String keyword1, String keyword2, String keyword3){
        this.databaseName = databaseName;
        this.keyword1 = keyword1;
        this.keyword2 = keyword2;
        this.keyword3 = keyword3;

    }

    public void crawl(){
        try
        {
            DatabaseHandler setdata = new DatabaseHandler();
            for(int i=1;i<=6;i++) {
                if(i == 1) {
                    String url = "https://global.rakuten.com/en/search/?k=milk&l-id=search_regular"; // other than first time... url will be url = page.getNextUrl(url)
                    int counter = 0;
                    Document doc = Jsoup.connect(url).get();
                    Elements row = doc.getElementsByClass("b-content b-fix-2lines").select("a");//// get class element
                    Elements row2 = doc.getElementsByClass("b-content m-shop-top-text").select("span");
                    for (Element rows : row)
                    {
                        if(row.text().contains(keyword1) || row.text().contains(keyword2) || row.text().contains(keyword3)){//filer keywords
                            System.out.println(rows.text());
                            System.out.println("----------------------");
                            System.out.println(rows.attr("href"));
                            System.out.println("----------------------");
                            String header = rows.text();
                            String price = row2.get(counter).ownText();
                            //String source = "Rakuten";
                            String link = "https://global.rakuten.com"+rows.attr("href");
                            counter += 2;
                            setdata.pushDatabase(header,price,source,link, databaseName); // add to database
                        }

                    }
                }
                if(i > 1){
                    url = "https://global.rakuten.com/en/search/?k=diapers&p="+2+"&l-id=search_regular"; // other than first time... url will be url = page.getNextUrl(url
                    int counter = 0;
                    Document doc = Jsoup.connect(url).get();
                    Elements row = doc.getElementsByClass("b-content b-fix-2lines").select("a");
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
                            //String source = "Rakuten";
                            String link = "https://global.rakuten.com"+rows.attr("href");
                            counter += 2;
                            setdata.pushDatabase(header,price,source,link, databaseName);
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
    public void run() {
        crawl();
    }
}
