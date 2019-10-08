package ru.stqa.pft.mantis.test;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.models.MailMessage;
import ru.stqa.pft.mantis.models.UserData;
import ru.stqa.pft.mantis.test.BaseTest;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends BaseTest {

    @BeforeMethod
    public void ensurePreconditions() throws IOException, MessagingException {
        if (app.db().usersExceptAdmin().size() == 0){
            long now = System.currentTimeMillis();
            String email = String.format("user%s@localhost.localdomain", now);
            String user = String.format("user%s", now);;
            String password = "password";
            app.registration().start(user, email);
            List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
            String confirmationLink = app.registration().findConfirmationLink(mailMessages, email);
            app.registration().finish(confirmationLink, password);
        }
    }
    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void changePassword() throws IOException, MessagingException {
        app.change().start(app.getProperty("web.adminLogin"),app.getProperty("web.adminPassword"));
        UserData user = app.db().usersExceptAdmin().iterator().next();
        app.change().password(user);
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = app.registration().findConfirmationLink(mailMessages, user.getEmail());
        String newPassword = "newpassword";
        app.change().finish(confirmationLink, user, newPassword);
        assertTrue(app.newSession().login(user.getUsername(), newPassword));
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
