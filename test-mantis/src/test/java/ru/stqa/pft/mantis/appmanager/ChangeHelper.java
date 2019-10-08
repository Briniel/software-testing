package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.models.UserData;

import java.io.IOException;

public class ChangeHelper extends HelperBase {

    public ChangeHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String password) {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), username);
        click(By.cssSelector("input[value='Войти']"));
        type(By.name("password"), password);
        click(By.cssSelector("input[value='Войти']"));
    }

    public void password(UserData user) throws IOException {
        click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Управление'])[1]/preceding::i[1]"));
        click(By.linkText("Управление пользователями"));
        click(By.xpath(String.format("//a[@href='manage_user_edit_page.php?user_id=%s']", user.getId())));
        click(By.cssSelector("input[value='Сбросить пароль']"));
    }

    public void finish(String confirmationLink, UserData user, String newPassword) {
        wd.get(confirmationLink);
        type(By.cssSelector("input[id='realname']"), user.getUsername());
        type(By.cssSelector("input[id='password']"), newPassword);
        type(By.cssSelector("input[id='password-confirm']"), newPassword);
        click(By.cssSelector("button[type='submit']"));

    }
}
