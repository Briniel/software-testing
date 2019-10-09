package ru.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactsData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTest extends BaseTest {

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
    public void testContactEmail(){
        app.goTo().goToHomePage();
        ContactsData contact = app.contacts().all().iterator().next();
        ContactsData contactInfoFormEditForm = app.contacts().infoEmailEditForm(contact);

        assertThat(contact.getAllEmail(), equalTo(mergeEmail(contactInfoFormEditForm)));
    }

    private String mergeEmail(ContactsData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> !s.equals(""))
                .collect(Collectors.joining("\n"));
    }

}
