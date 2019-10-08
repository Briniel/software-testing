package ru.stqa.pft.mantis.test;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.models.MailMessage;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTestsWithJames extends BaseTest {

        @Test
        public void testRegistration() throws IOException, javax.mail.MessagingException {
            long now = System.currentTimeMillis();
            String email = String.format("user%s@localhost.localdomain", now);
            String user = String.format("user%s", now);;
            String password = "password";
            app.james().createUser(user, password);
            app.registration().start(user, email);
            List<MailMessage> mailMessages = app.james().waitForMail(user, password,60000);
            String confirmationLink = findConfirmationLink(mailMessages, email);
            app.registration().finish(confirmationLink, password);
            assertTrue(app.newSession().login(user,password));

        }

        private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
            MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
            VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
            regex.getText(mailMessage.text);
            return regex.getText(mailMessage.text);
        }

    }

