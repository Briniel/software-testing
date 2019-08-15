package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactsData;

public class ContactsHelper extends HelperBase {


    public ContactsHelper(WebDriver wd) {
        super(wd);
    }

    public void createContact(ContactsData contactsData) {
        type(By.name("firstname"), contactsData.getFirstName());
        type(By.name("middlename"), contactsData.getMiddleName());
        type(By.name("lastname"), contactsData.getLastName());
        type(By.name("company"), contactsData.getCompany());
        type(By.name("nickname"), contactsData.getNickName());
        type(By.name("address"), contactsData.getAddress());
        type(By.name("home"), contactsData.getHomePhone());
        type(By.name("mobile"), contactsData.getMobilePhone());
        dropdownListSelect(By.name("new_group"), contactsData.getGroup());
        type(By.name("work"), contactsData.getWorkPhone());
        type(By.name("email"), contactsData.getEmail());
        dropdownListSelect(By.name("bday"), contactsData.getBday());
        dropdownListSelect(By.name("bmonth"), contactsData.getBmount());
        type(By.name("byear"), contactsData.getByear());
    }

    public void updateContact(ContactsData contactsData) {
        type(By.name("company"), contactsData.getCompany());
        type(By.name("nickname"), contactsData.getNickName());
        type(By.name("home"), contactsData.getHomePhone());
        type(By.name("mobile"), contactsData.getMobilePhone());
        type(By.name("work"), contactsData.getWorkPhone());
        type(By.name("email"), contactsData.getEmail());
    }

    public void submitContactCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void selectContact() {
        click(By.xpath("//*[@title='Select (Mikhail Ivanov)']"));
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
}
