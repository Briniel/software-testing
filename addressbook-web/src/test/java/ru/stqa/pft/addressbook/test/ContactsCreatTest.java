package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.ContactsData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactsCreatTest extends BaseTest {

    @Test
    public void testUserCreat(){
        app.goTo().goToHomePage();
        Contacts before = app.contacts().all();
        File photo = new File("src/test/resources/stru.png");
        ContactsData contact = new ContactsData()
                .withFirstName("Mikhail").withMiddleName("Alekseevich").withLastName("Ivanov").withCompany("BSS").withNickName("Brin").withAddress("c. Moscow")
                .withHomePhone("96-08-56").withMobilePhone("89272106632").withWorkPhone("89457257986").withPhoto(photo).withEmail("dragon1239@mail.ru").withGroup("weewg");
        app.contacts().contactCreate(contact);
        assertThat(app.contacts().count(), equalTo(before.size() + 1));
        Contacts after = app.contacts().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }
}
