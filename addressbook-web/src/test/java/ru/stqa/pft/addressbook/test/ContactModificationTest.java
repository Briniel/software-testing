package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactsData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends BaseTest {

    @Test(enabled = false)
    public void testContactModificationTest() {
        app.goTo().goToHomePage();
        if (! app.getContactsHelper().isThereAContact()){
            app.getContactsHelper().contactCreate(new ContactsData("Mikhail", "Alekseevich", "Ivanov", "BSS", "Brin", "c. Moscow", "96-08-56", "89272106632", "89457257986", "test-1", "dragon1239@mail.ru"));
        }
        List<ContactsData> before = app.getContactsHelper().getContactsList();
        app.getContactsHelper().initContactModification(before.size() - 1);
        ContactsData contact = new ContactsData("Jon", null, "Contor", "BSS_New", "Briniel", null, "86-08-58", "89272107632", "89457245986", null, "dragon1239@ya.ru");
        app.getContactsHelper().createContact(contact, false);
        app.getContactsHelper().submitContactModification();
        app.getContactsHelper().returnHomePage();
        List<ContactsData> after = app.getContactsHelper().getContactsList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactsData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);

    }
}
