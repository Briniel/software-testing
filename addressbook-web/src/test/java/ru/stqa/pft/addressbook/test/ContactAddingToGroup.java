package ru.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.ContactsData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactAddingToGroup extends BaseTest {
    @BeforeMethod
    public void ensurePreconditions(){
        app.contacts().returnHomePage();
        if(app.db().contacts().size() == 0){
            app.contacts().contactCreate(new ContactsData().withFirstName("first2").withLastName("last2").withNickName("nick2").withCompany("company2").withAddress("address2")
                    .withMobilePhone("12345").withHomePhone("22").withWorkPhone("333")
                    .withEmail("w@ww.ww").withEmail2("e@e.e").withEmail3("q@q.q"));
        }

        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().creat(new GroupData().withName("test111").withFooter("footer1").withHeader("header1"));
        }
    }

    @Test
    public void testContactAddingToGroup(){
        Contacts contacts = app.db().contacts();
        ContactsData addedContact = contacts.iterator().next();
        Groups groups = app.db().groups();
        GroupData linkedGroup = groups.iterator().next();
        Groups groupsOfAddedContact = addedContact.getGroups();
        Iterator<ContactsData> iterator = contacts.iterator();
        while (iterator.hasNext()) {
            if (groupsOfAddedContact.equals(groups)) {
                addedContact = iterator.next();
                groupsOfAddedContact = addedContact.getGroups();
            } else {
                break;
            }
        }
        if (groupsOfAddedContact.equals(groups)) {
            app.goTo().groupPage();
            linkedGroup = new GroupData().withName("test11").withHeader("test12").withFooter("test13");
            app.group().creat(linkedGroup);
            app.contacts().addContact(addedContact, linkedGroup);
        }
        app.contacts().addContact(addedContact, linkedGroup);
        ContactsData contactsAfter = app.db().selectContactFromDbById(addedContact.getId()).iterator().next();
        Groups groupsOfAddedContactAfter = contactsAfter.getGroups();
        assertThat(groupsOfAddedContact.withAdded(linkedGroup), equalTo(groupsOfAddedContactAfter));
    }
}
