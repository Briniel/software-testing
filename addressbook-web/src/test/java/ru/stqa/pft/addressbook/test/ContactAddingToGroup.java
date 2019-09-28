package ru.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.ContactsData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.testng.Assert.assertEquals;

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
        Groups groups = app.db().groups();
        Contacts before = app.db().contacts();
        ContactsData addedContact = before.iterator().next();
        ContactsData contact = new ContactsData().withId(addedContact.getId()).inGroup(groups.iterator().next());
        if(contact.getGroups().size()!=app.db().groups().size()) {
            app.contacts().addtogroup(contact);
            Contacts after = app.db().contacts();
            assertEquals(after.size(), before.size());
        }
    }
}
