package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.ContactsData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

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
        if (creation) {
            if (contactsData.getGroups().size() > 0){
                Assert.assertTrue(contactsData.getGroups().size() == 1);
                dropdownListSelect(By.name("new_group"), contactsData.getGroups().iterator().next().getName());
            }

        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")), "На форме модификации контакта появилось поля группы");
        }
        type(By.name("work"), contactsData.getWorkPhone());
        attach(By.name("photo"), contactsData.getPhoto());
        type(By.name("email"), contactsData.getEmail());
    }

    public void modify(ContactsData contact) {
        initContactModificationById(contact.getId());
        createContact(contact, false);
        submitContactModification();
        contactCach = null;
        returnHomePage();
    }

    public void delete(ContactsData contact) {
        selectContactById(contact.getId());
        deleteSelectContact();
        contactCach = null;
        returnHomePage();
    }

    public void addtogroup(ContactsData contact) {
        selectContactById(contact.getId());
        addSelectedContactToGroup(contact);
        returnHomePage();
    }

    public void contactCreate(ContactsData contact) {
        goToUserCreatePage();
        createContact(contact, true);
        submitContactCreation();
        contactCach = null;
        returnHomePage();
    }

    public void addSelectedContactToGroup(ContactsData contactData) {
        new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
        click(By.xpath("//input[@value='Add to']"));
//    wd.switchTo().alert().accept();
        wd.findElement(By.cssSelector("div.msgbox"));
    }

    public void submitContactCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void selectContactById(int id) {
        wd.findElement(By.id(String.valueOf(id))).click();
    }

    public void deleteSelectContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void acceptAlert() {
        wd.switchTo().alert().accept();
    }

    public void initContactModificationById(int id) {
        wd.findElement(By.xpath("//input[@id='"+id+"']/../..//img[@alt='Edit']")).click();
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

    public int count() {
        return wd.findElements(By.xpath("//tr[@name='entry']")).size();
    }

    private Contacts contactCach = null;

    public Contacts all() {
        if (contactCach != null){
            return new Contacts(contactCach);
        }
        contactCach = new Contacts();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : elements) {
            String firstName = element.findElement(By.xpath("./td[3]")).getText();
            String lastName = element.findElement(By.xpath("./td[2]")).getText();
            String allPhones = element.findElement(By.xpath("./td[6]")).getText();
            String allEmail = element.findElement(By.xpath("./td[5]")).getText();
            String allHome = element.findElement(By.xpath("./td[4]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            contactCach.add(new ContactsData().withId(id).withFirstName(firstName).withLastName(lastName)
                    .withAllPhone(allPhones).withAllEmail(allEmail).withAddress(allHome));
        }
        return new Contacts(contactCach);
    }

    public ContactsData infoPhoneEditForm(ContactsData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return new  ContactsData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
    }

    public ContactsData infoEmailEditForm(ContactsData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new  ContactsData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname)
                .withEmail(email).withEmail2(email2).withEmail3(email3);
    }

    public ContactsData infoAddressEditForm(ContactsData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        wd.navigate().back();
        return new  ContactsData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname).withAddress(address);
    }

    public void addContact(ContactsData contact, GroupData group){
        selectContactById(contact.getId());
        new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(group.getName());
        click(By.name("add"));
        click(By.linkText("group page \"" + group.getName() + "\""));
    }

    public void deleteContact(ContactsData contact, GroupData group) {
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(group.getName());
        selectContactById(contact.getId());
        click(By.name("remove"));
        click(By.linkText("group page \"" + group.getName() + "\""));
    }
}
