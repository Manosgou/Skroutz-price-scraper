

public class Main {


    public static void main(String [] args){
        Product p = new Product("https://www.skroutz.gr/s/22234070/Ghost-of-Tsushima-PS4.html",50.0);


        String title = p.getProductTitle();
        Double price = p.getProductPrice();
        System.out.println("Title: "+title);
        System.out.println("Price: "+price);
        if(price>p.getDesiredPrice()){
            System.out.println("Den ginete file asto");
        }else{
            System.out.println("Pane parto to rimadi");
        }
    }


}
