package ru.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactsData;

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
        ContactsData contacts = app.contacts().all().iterator().next();
//        ContactsData contactInfoFormEditForm = app.contacts().infoFormEditForm();
    }
}
