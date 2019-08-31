package ru.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactsData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTest extends BaseTest {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().goToHomePage();
        if (! app.contacts().isThereAContact()){
            app.contacts().contactCreate(new ContactsData()
                    .withFirstName("Mikhail").withMiddleName("Alekseevich").withLastName("Ivanov").withCompany("BSS").withNickName("Brin").withAddress("c. Moscow")
                    .withHomePhone("96-08-56").withMobilePhone("89272106632").withWorkPhone("89457257986").withGroup("test-1").withEmail("dragon1239@mail.ru"));
        }
    }

    @Test
    public void testContactPhone(){
        app.goTo().goToHomePage();
        ContactsData contact = app.contacts().all().iterator().next();
        ContactsData contactInfoFormEditForm = app.contacts().infoFormEditForm(contact);
        assertThat(contact.getHomePhone(), equalTo(cleaned(contactInfoFormEditForm.getHomePhone())));
        assertThat(contact.getMobilePhone(), equalTo(cleaned(contactInfoFormEditForm.getMobilePhone())));
        assertThat(contact.getWorkPhone(), equalTo(cleaned(contactInfoFormEditForm.getWorkPhone())));
    }

    public String cleaned(String phone){
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
