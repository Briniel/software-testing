package ru.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.ContactsData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTest extends BaseTest {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0){
            Groups groups = app.db().groups();
            app.goTo().goToHomePage();
            app.contacts().contactCreate(new ContactsData()
                    .withFirstName("Mikhail").withMiddleName("Alekseevich").withLastName("Ivanov").withCompany("BSS").withNickName("Brin").withAddress("c. Moscow")
                    .withHomePhone("96-08-56").withMobilePhone("89272106632").withWorkPhone("89457257986").withEmail("dragon1239@mail.ru").inGroup(groups.iterator().next()));
        }
    }

    @Test
    public void testContactModificationTest() {
        Contacts before = app.db().contacts();
        app.goTo().goToHomePage();
        ContactsData modifyContact = before.iterator().next();
        ContactsData contact = new ContactsData().withId(modifyContact.getId())
                .withFirstName("Jon").withLastName("Contor").withCompany("BSS_New").withNickName("Briniel").withHomePhone("86-08-58")
                .withMobilePhone("89272107632").withWorkPhone("89457245986").withEmail("dragon1239@ya.ru");
        app.contacts().modify(contact);
        assertThat(app.contacts().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.withOut(modifyContact).withAdded(contact)));

    }

}
