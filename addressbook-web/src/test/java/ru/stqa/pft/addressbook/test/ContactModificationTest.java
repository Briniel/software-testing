package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactsData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactModificationTest extends BaseTest {

    @Test
    public void testGroupModificationTest() {
        app.getContactsHelper().initContactModification();
        app.getContactsHelper().updateContact(new ContactsData("Mikhail", "Alekseevich", "Ivanov", "BSS_New", "Briniel", "c. Moscow", "86-08-58", "89272107632", "89457245986", "test-1", "dragon1239@ya.ru", "18", "January", "1995"));
        app.getContactsHelper().submitContactModification();
        app.getContactsHelper().returnHomePage();
    }
}
