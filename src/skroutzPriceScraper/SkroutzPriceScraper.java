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

            ArrayList<String> urls = new ArrayList<>();
            ArrayList<Double> desiredPrices = new ArrayList<>();
            ArrayList<Double> convertedPrices = new ArrayList<>();
            ArrayList<String> titles = new ArrayList<>();

            urls.add("skroutz poduct link e.g:https://www.skroutz.gr/s/10864394/Nintendo-Switch-Red-Blue-Joy-Con-32GB.html?from=featured");
            urls.add("skroutz poduct link e.g:https://www.skroutz.gr/s/15809760/Apple-iPhone-XR-64GB.html");
            urls.add("skroutz poduct link e.g:https://www.skroutz.gr/s/8843619/Bosch-%CE%A8%CF%85%CE%B3%CE%B5%CE%B9%CE%BF%CE%BA%CE%B1%CF%84%CE%B1%CF%88%CF%8D%CE%BA%CF%84%CE%B7%CF%82-NoFrost-A-KGN36NW30.html");

            desiredPrices.add(0.0);
            desiredPrices.add(0.0);
            desiredPrices.add(0.0);

            if (urls.size() == desiredPrices.size()) {
                for (String i : urls) {
                    Document doc = Jsoup.connect(i).get();

                    String pirceSelector = "div.price>.js-blp>.js-product-link";
                    String titleSelector = "#sku-info > div:nth-child(1) > h1";

                    Elements priceElement = doc.select(pirceSelector);
                    Elements titleElement = doc.select(titleSelector);

                    String title = titleElement.text();
                    titles.add(title);

                    String price = priceElement.first().text().replace("€","").replace(",", ".").trim();

                    double convertedPrice = Double.valueOf(price);
                    convertedPrices.add(convertedPrice);

                }
                for (int i = 0; i < convertedPrices.size(); i++) {
                    double cP = convertedPrices.get(i);
                    double dP = desiredPrices.get(i);

                    if (cP < dP) {

                        message.setSubject("Skoutz price tracker - Η τιμή του " + titles.get(i) + "έπεσε!!!");
                        message.setText("Ωρα για αγορές!!!\n Το " + titles.get(i) + " κοστίζει " + cP + " €\n" + urls.get(i));
                        Transport.send(message);
                        System.out.println("Email sent for " + titles.get(i));

                    }
                }

            } else if (desiredPrices.size() > urls.size()) {
                System.out.println("Υπάρχει μια παραπάνω τιμή για προιόν που δεν υπάχει στη λίστα.");
            } else {
                System.out.println("Κάποιο προιόν δεν έχει τιμή.");
            }

        } catch (IOException e) {

            e.printStackTrace();

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

}
