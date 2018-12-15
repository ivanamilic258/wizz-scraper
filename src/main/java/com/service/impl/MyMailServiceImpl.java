package com.service.impl;

import com.service.MyMailService;
import com.service.dto.BestDealDto;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Service
public class MyMailServiceImpl implements MyMailService{


    @Override
    public void sendEmail(List<BestDealDto> bestDeals){
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        // Get a Properties object
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.store.protocol", "pop3");
        props.put("mail.transport.protocol", "smtp");
        final String username = "ivanaxyz123@gmail.com";//
        final String password = "ivanaxyz*";
        try{
            Session session = Session.getDefaultInstance(props,
                    new Authenticator(){
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }});

            // -- Create a new message --
            Message msg = new MimeMessage(session);

            // -- Set the FROM and TO fields --
            msg.setFrom(new InternetAddress("xxxx@gmail.com"));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("ivanamilic258@gmail.com",false));
            msg.setSubject("Did you know...");
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");

            StringBuilder builder = new StringBuilder("<p>&nbsp;</p>\n" +
                    "<p>Hi there,</p>\n" +
                    "<p>&nbsp;</p>\n" +
                    "<p>As you probably already know, we are here to make your flight search easier. That's why we are providing you a list of our top 5 picks of trips from Belgrade during 4 to 8 days, based on the price:</p>\n" +
                    "<p>&nbsp;</p>\n" +
                    "<ul>\n" );


            for (BestDealDto bestDeal : bestDeals) {
                builder.append(MessageFormat.format(   "<li>{0}</li>\n", dateFormat.format(bestDeal.getDateFrom()) + " - " +dateFormat.format(bestDeal.getDateTo())  + ": " + bestDeal.getDeparture() + " to "  + bestDeal.getDestination() + " for "  + bestDeal.getPriceSum() + " " + bestDeal.getCurrency()));
            }



            builder.append("</ul>\n" +
                    "<p>&nbsp;</p>\n" +
                    "<p>Be sure to check them out before they are sold and you are left with lousy AirSerbia.</p>\n" +
                    "<p><br />Cheers,</p>\n" +
                    "<p>Your PA team</p>");
            msg.setContent(builder.toString(), "text/html");
            msg.setSentDate(new Date());
            Transport.send(msg);
            System.out.println("Message sent.");
        }catch (MessagingException e){ System.out.println("Erreur d'envoi, cause: " + e);}

    }
}
