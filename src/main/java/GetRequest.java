import org.json.JSONObject;

import javax.mail.*;
import javax.mail.internet.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;
import java.util.Properties;

public class GetRequest {
    public static int initialChap = 1000;
    public static void main(String[] args) {
        System.out.println("Start");
        try {
            //String data = GetRequest.call_me();
            GetRequest.sendEmail("gotovn07@gmail.com", "A@12345678", "hocdoan11@gmail.com");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("End");
    }


    public static String call_me() throws Exception {
        String accessToken = "EAAAAZAw4FxQIBADPeGda5oDYv3K0Onsr83b8a7ztjebkevKKZAjGWwYOVlUVwu80ZB2aC6AS7rX78XbLXSRUCp1ljwYM54I4rTAcDZCzkm2ffOZCbrUZAELmJIqlpRxZBfXC6nlSeCGJFlbYZA3P5MTWLKDwEvLyHYKaYGgiWDxADVcRkH73HTLl";
        String url = "https://graph.facebook.com/272811963711458/feed?limit=5&access_token=" + accessToken;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("Content-Type", "application/json");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        /*try (PrintWriter out = new PrintWriter("text.txt")) {
            out.print(response.toString());
        }*/
        //print in String
        //System.out.println(response.toString());

        //Read JSON response and print
        JSONObject myResponse = new JSONObject(response.toString());
        System.out.println("result after Reading JSON Response");
        return response.toString();
    }

    public static void sendEmail(String user, String pass, String recipient) throws Exception{

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(user, pass);

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(user));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

            // Set Subject: header field
            message.setSubject("Thông báo chương mới");

            // Now set the actual message
            message.setText("CĐBC đã có chương mới");

            // Send message
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
