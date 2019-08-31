package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactsData;

import java.util.List;

public class ContactsDelete extends BaseTest{

    @Test(enabled = false)
    public void testContactsDelete(){
        if (! app.getContactsHelper().isThereAContact()){
            app.getContactsHelper().contactCreate(new ContactsData("Mikhail", "Alekseevich", "Ivanov", "BSS", "Brin", "c. Moscow", "96-08-56", "89272106632", "89457257986", "test-1", "dragon1239@mail.ru"));
        }
        List<ContactsData> before = app.getContactsHelper().getContactsList();
        app.getContactsHelper().selectContact(before.size() - 1);
        app.getContactsHelper().deleteSelectContact();
        app.getContactsHelper().acceptAlert();
        List<ContactsData> after = app.getContactsHelper().getContactsList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(after, before);
    }
}
