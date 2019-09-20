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
        if (app.db().contacts().size() == 0){
            app.goTo().goToHomePage();
            app.contacts().contactCreate(new ContactsData()
                    .withFirstName("Mikhail").withMiddleName("Alekseevich").withLastName("Ivanov").withCompany("BSS").withNickName("Brin").withAddress("c. Moscow")
                    .withHomePhone("96-08-56").withMobilePhone("89272106632").withWorkPhone("89457257986").withGroup("test-1").withEmail("dragon1239@mail.ru"));
        }
    }

    @Test(enabled = false)
    public void testContactsDelete(){
        Contacts before = app.db().contacts();
        ContactsData deleteContact = before.iterator().next();
        app.goTo().goToHomePage();
        app.contacts().delete(deleteContact);
        assertEquals(app.contacts().count(), before.size() - 1);
        Contacts after = app.db().contacts();
        MatcherAssert.assertThat(after, equalTo(before.withOut(deleteContact)));
    }

}
