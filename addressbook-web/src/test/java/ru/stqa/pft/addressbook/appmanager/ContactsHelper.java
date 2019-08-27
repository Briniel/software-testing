package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactsData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

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

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteSelectContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void acceptAlert() {
        wd.switchTo().alert().accept();
    }

    public void initContactModification(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }

    public void submitContactModification() {
        click(By.xpath("//input[@name='update']"));
    }

    public void returnHomePage() {
        click(By.linkText("home page"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
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

    public List<ContactsData> getContactsList() {
        List<ContactsData> contacts = new ArrayList<ContactsData>();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element: elements){
            String firstName = element.findElement(By.xpath("//td[3]")).getText();
            String lastName = element.findElement(By.xpath("//td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.xpath("//td//input")).getAttribute("id"));
            ContactsData contact = new ContactsData(id, firstName, null, lastName, null, null, null, null, null, null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }
}
