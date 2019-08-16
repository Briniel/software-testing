package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactsData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactsHelper extends HelperBase {


    public ContactsHelper(WebDriver wd) {
        super(wd);
    }

    public void createContact(ContactsData contactsData, boolean creation) {
        type(By.name("firstname"), contactsData.getFirstName());
        type(By.name("middlename"), contactsData.getMiddleName());
        type(By.name("lastname"), contactsData.getLastName());
        type(By.name("company"), contactsData.getCompany());
        type(By.name("nickname"), contactsData.getNickName());
        type(By.name("address"), contactsData.getAddress());
        type(By.name("home"), contactsData.getHomePhone());
        type(By.name("mobile"), contactsData.getMobilePhone());
        if (creation){
            dropdownListSelect(By.name("new_group"), contactsData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")), "На форме модификации контакта появилось поля группы");
        }
        type(By.name("work"), contactsData.getWorkPhone());
        type(By.name("email"), contactsData.getEmail());
    }

    public void submitContactCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void selectContact() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td/input"));
    }

    public void deleteSelectContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void acceptAlert() {
        wd.switchTo().alert().accept();
    }

    public void initContactModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitContactModification() {
        click(By.xpath("(xpath=(//input[@name='update'])[2]"));
    }

    public void returnHomePage() {
        click(By.linkText("home page"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("(xpath=(//input[@name='update'])[2]"));
    }

    public void goToUserCreatePage() {
        click(By.linkText("add new"));
    }

    public void contactCreate(ContactsData contact) {
        goToUserCreatePage();
        createContact(contact, true);
        submitContactCreation();
        returnHomePage();
    }
}
