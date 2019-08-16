package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactsData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactModificationTest extends BaseTest {

    @Test
    public void testContactModificationTest() {
        app.getNavigationHelper().goToHomePage();
        if (! app.getContactsHelper().isThereAContact()){
            app.getContactsHelper().contactCreate(new ContactsData("Mikhail", "Alekseevich", "Ivanov", "BSS", "Brin", "c. Moscow", "96-08-56", "89272106632", "89457257986", "test-1", "dragon1239@mail.ru"));
        }
        app.getContactsHelper().initContactModification();
        app.getContactsHelper().createContact(new ContactsData(null, null, null, "BSS_New", "Briniel", null, "86-08-58", "89272107632", "89457245986", null, "dragon1239@ya.ru"), false);
        app.getContactsHelper().submitContactModification();
        app.getContactsHelper().returnHomePage();
    }
}
