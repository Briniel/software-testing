package ru.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactsData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTest extends BaseTest {

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
    public void testContactPhone() {
        app.goTo().goToHomePage();
        ContactsData contact = app.contacts().all().iterator().next();
        ContactsData contactInfoFormEditForm = app.contacts().infoPhoneEditForm(contact);

        assertThat(contact.getAllPhone(), equalTo(mergePhones(contactInfoFormEditForm)));
    }

    private String mergePhones(ContactsData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactPhoneTest::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
