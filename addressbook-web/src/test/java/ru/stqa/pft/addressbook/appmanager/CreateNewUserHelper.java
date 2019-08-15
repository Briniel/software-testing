package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.UserData;

public class CreateNewUserHelper extends HelperBase {

    public CreateNewUserHelper(WebDriver wd) {
        super(wd);
    }

    public void createNewUser(UserData userData) {
        type(By.name("firstname"), userData.getFirstName());
        type(By.name("middlename"), userData.getMiddleName());
        type(By.name("lastname"), userData.getLastName());
        type(By.name("company"), userData.getCompany());
        type(By.name("nickname"), userData.getNickName());
        type(By.name("address"), userData.getAddress());
        type(By.name("home"), userData.getHomePhone());
        type(By.name("mobile"), userData.getMobilePhone());
        dropdownListSelect(By.name("new_group"), userData.getGroup());
        type(By.name("work"), userData.getWorkPhone());
        type(By.name("email"), userData.getEmail());
        dropdownListSelect(By.name("bday"), userData.getBday());
        dropdownListSelect(By.name("bmonth"), userData.getBmount());
        type(By.name("byear"), userData.getByear());
        click(By.xpath("(//input[@name='submit'])[2]"));
    }
}
