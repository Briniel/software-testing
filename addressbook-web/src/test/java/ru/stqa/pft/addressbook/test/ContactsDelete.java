package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactsData;

public class ContactsDelete extends BaseTest{

    @Test
    public void testContactsDelete() throws Exception {
        app.getContactsHelper().selectContact();
        if (! app.getContactsHelper().isThereAContact()){
            app.getContactsHelper().contactCreate(new ContactsData("Mikhail", "Alekseevich", "Ivanov", "BSS", "Brin", "c. Moscow", "96-08-56", "89272106632", "89457257986", "test-1", "dragon1239@mail.ru"));
        }
        app.getContactsHelper().deleteSelectContact();
        app.getContactsHelper().acceptAlert();
    }
}
