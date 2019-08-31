package ru.stqa.pft.addressbook.test;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.ContactsData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.testng.Assert.assertEquals;

public class ContactsDelete extends BaseTest{

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().goToHomePage();
        if (! app.contacts().isThereAContact()){
            app.contacts().contactCreate(new ContactsData()
                    .withFirstName("Mikhail").withMiddleName("Alekseevich").withLastName("Ivanov").withCompany("BSS").withNickName("Brin").withAddress("c. Moscow")
                    .withHomePhone("96-08-56").withMobilePhone("89272106632").withWorkPhone("89457257986").withGroup("test-1").withEmail("dragon1239@mail.ru"));
        }
    }

    @Test(enabled = false)
    public void testContactsDelete(){
        Contacts before = app.contacts().all();
        ContactsData deleteContact = before.iterator().next();
        app.contacts().delete(deleteContact);
        Contacts after = app.contacts().all();
        assertEquals(after.size(), before.size() - 1);
        MatcherAssert.assertThat(after, equalTo(before.withOut(deleteContact)));
    }

}
