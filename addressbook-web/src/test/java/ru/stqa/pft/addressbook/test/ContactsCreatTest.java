package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactsData;

import java.util.Comparator;
import java.util.List;

public class ContactsCreatTest extends BaseTest {

    @Test
    public void testUserCreat(){
        app.getNavigationHelper().goToHomePage();
        List<ContactsData> before = app.getContactsHelper().getContactsList();
        ContactsData contact = new ContactsData("Mikhail", "Alekseevich", "Ivanov", "BSS", "Brin", "c. Moscow", "96-08-56", "89272106632", "89457257986", "test-1", "dragon1239@mail.ru");
        app.getContactsHelper().contactCreate(contact);
        List<ContactsData> after = app.getContactsHelper().getContactsList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactsData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);
    }
}
