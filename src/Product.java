import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;

public class Product {
    private final String productUrl;
    private String productName;
    private Double productPrice;
    public Double desiredPrice;

    public Product(String productUrl , Double desiredPrice){
        this.productUrl = productUrl;
        this.desiredPrice =desiredPrice;
    }

    public String getProductTitle(){
        try{
            Document doc = Jsoup.connect(this.productUrl).get();
            String titleSelector = "nav#nav.scroll-area > h1";
            Elements titleElement = doc.select(titleSelector);
            this.productName = titleElement.text();
        }catch (IOException e){
            e.printStackTrace();

        }

        return this.productName;
    }

    public Double getDesiredPrice(){
        return this.desiredPrice;
    }

    public Double getProductPrice(){

        try{
            Document doc = Jsoup.connect(this.productUrl).get();
            String pirceSelector = "div.price";
            Elements priceElement = doc.select(pirceSelector);
            String price = priceElement.first().text().replace(',','.').replace('€',' ').trim();
            this.productPrice = Double.valueOf(price);
        }catch (IOException e){
            e.printStackTrace();

        }
        return this.productPrice;
    }




}
