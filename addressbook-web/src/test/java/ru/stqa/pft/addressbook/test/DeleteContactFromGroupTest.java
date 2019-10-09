package ru.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.ContactsData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

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
        Contacts contacts = app.db().contacts();
        ContactsData deletedContact = contacts.iterator().next();
        Groups groupsOfDeletedContact = deletedContact.getGroups();
        if (deletedContact.getGroups().size() == 0) {
            app.contacts().addContact(deletedContact, groups.iterator().next());
        } // проверяем, что у контакта есть группы, если нет, то привязываем контакт к группе
        GroupData linkedGroup = groupsOfDeletedContact.iterator().next();
        app.contacts().deleteContact(deletedContact, linkedGroup);
        ContactsData contactsAfter = app.db().selectContactFromDbById(deletedContact.getId()).iterator().next();
        Groups groupsOfDeletedContactAfter = contactsAfter.getGroups();
        assertThat(groupsOfDeletedContact.withOut(linkedGroup), equalTo(groupsOfDeletedContactAfter));
    }

}
