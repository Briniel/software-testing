package ru.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.ContactsData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTest extends BaseTest {

    @BeforeMethod(enabled = false)
    public void ensurePreconditions() {
        app.goTo().goToHomePage();
        if (! app.contacts().isThereAContact()){
            app.contacts().contactCreate(new ContactsData()
                    .withFirstName("Mikhail").withMiddleName("Alekseevich").withLastName("Ivanov").withCompany("BSS").withNickName("Brin").withAddress("c. Moscow")
                    .withHomePhone("96-08-56").withMobilePhone("89272106632").withWorkPhone("89457257986").withGroup("test-1").withEmail("dragon1239@mail.ru"));
        }
    }

    @Test
    public void testContactModificationTest() {
        Contacts before = app.contacts().all();
        ContactsData modifyContact = before.iterator().next();
        ContactsData contact = new ContactsData().withId(modifyContact.getId())
                .withFirstName("Jon").withLastName("Contor").withCompany("BSS_New").withNickName("Briniel").withHomePhone("86-08-58")
                .withMobilePhone("89272107632").withWorkPhone("89457245986").withEmail("dragon1239@ya.ru");
        app.contacts().modify(contact);
        Contacts after = app.contacts().all();
        assertThat(after.size(), equalTo(before.size()));
        assertThat(after, equalTo(before.withOut(modifyContact).withAdded(contact)));

    }

}
