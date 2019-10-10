package ru.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.ContactsData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class DeleteContactFromGroupTest extends BaseTest {

    @BeforeMethod
    public void ensurePreconditions() {
        if(app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().creat(new GroupData().withName("test").withHeader("test1").withFooter("test2"));
        }
        if(app.db().contacts().size() == 0) {
            Groups groups = app.db().groups();
            app.contacts().contactCreate(new ContactsData()
                    .withFirstName("Mikhail").withMiddleName("Alekseevich").withLastName("Ivanov").withCompany("BSS").withNickName("Brin").withAddress("c. Moscow")
                    .withHomePhone("96-08-56").withMobilePhone("89272106632").withWorkPhone("89457257986").withEmail("dragon1239@mail.ru").inGroup(groups.iterator().next()));
        }
    }

    @Test
    public void deleteContactFromGroup(){
        Groups groups = app.db().groups();
        Contacts contactBefore = app.db().contacts();
        ContactsData contacts = contactBefore.iterator().next();

        for (ContactsData contact : contactBefore){
            Groups contactGroups = contact.getGroups();
            if (contactGroups.size() != 0){
                contacts = contact;
                break;
            }
        }

        if(contacts.getGroups().size() == 0){
            GroupData groupToAdd = groups.iterator().next();
            app.contacts().addContact(contacts, groupToAdd);
        }

        app.contacts().deleteContact(contacts, groups.iterator().next());

        Groups groupAfter = app.db().groups();
        Contacts contactAfter = app.db().contacts();

        assertEquals(groups.size(), groupAfter.size());
        assertEquals(contactBefore.size(), contactAfter.size());
        assertThat(groupAfter, equalTo(groups));
        assertThat(contactAfter, equalTo(contactBefore));
    }

}
