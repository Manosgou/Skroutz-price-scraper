package skroutzPriceScraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import org.jsoup.select.Elements;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;


/**
 *
 * @author Manos Gouvrikos
 *
 *
 */
public class SkroutzPriceScraper {

    public static void main(String[] args) {

        String to = "example@gmail.com";
        String from = "example@gmail.com";
        
        final String username = "example@gmail.com";
        final String password = "password";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        
        

        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("skroutzPriceTrakcer");

            
            String URL = "skroutz poduct link e.g: https://www.skroutz.gr/s/10864394/Nintendo-Switch-Red-Blue-Joy-Con-32GB.html?from=featured";
            Document doc = Jsoup.connect(URL).get();
            String pirceSelector = "div.price>.js-blp>.js-product-link";
            Elements link = doc.select(pirceSelector);
            String price = link.first().text().substring(0, 6).replace(",", ".").trim();

            double convertedPrice = Double.valueOf(price);

            int desiredPrice =400;
            
            if (convertedPrice > desiredPrice) {
                message.setText("Ωρα για αγορές!!!\n" + URL);
                Transport.send(message);
                System.out.println("email sent");
            } 

           
        } catch (IOException e) {

            e.printStackTrace();

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

}
