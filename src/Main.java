import java.util.ArrayList;

public class Main {


    public static void main(String [] args){
        ArrayList<Product> list = new ArrayList<>();
        list.add(new Product("https://www.skroutz.gr/s/22234070/Ghost-of-Tsushima-PS4.html",50.0) );
        list.add(new Product("https://www.skroutz.gr/s/20268935/The-Last-of-Us-Part-II-PS4.html?from=recommendations",20.0) );
        list.add(new Product("https://www.skroutz.gr/s/20452802/Grand-Theft-Auto-V-Premium-Edition-PS4.html?from=recommendations",30.0) );

        for(Product i : list){
            String title = i.getProductTitle();
            Double price = i.getProductPrice();
            Double desiredPrice = i.getDesiredPrice();

            if(price>desiredPrice){
                System.out.println("Άσε το "+title+" είναι ακόμα ακριβό");
        }else{
                System.out.println("Τρέξε το "+title+" είναι σε καλή τιμή: "+price);
        }

        }





    }


}
