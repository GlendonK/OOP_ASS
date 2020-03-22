public class ExploreModelClass {

    private String price;
    private String header;
    private String source;
    private String link;

 //////////////// CLASS TO STORE THE INFORMATION FROM DATABASE //////////////////////////////
    public ExploreModelClass() {
    }

    public ExploreModelClass(String price, String header,String source,String link ) {
        this.price = price;
        this.header = header;
        this.source = source;
        this.link = link;

    }

    ////////////////////// GETTERS /////////////////////////
    public String getprice() {
        return price;
    }

    public String getheader() {
        return header;
    }

    public String getlink() {
        return link;
    }

    public String getsource() {
        return source;
    }


}