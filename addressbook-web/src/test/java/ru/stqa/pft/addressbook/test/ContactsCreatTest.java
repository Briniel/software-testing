package ru.stqa.pft.addressbook.test;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactsData;

public class ContactsCreatTest extends BaseTest {

  @Test
  public void testUserCreat() throws Exception {
    app.getNavigationHelper().goToUserCreatePage();
    app.getContactsHelper().createContact(new ContactsData("Mikhail", "Alekseevich", "Ivanov", "BSS", "Brin", "c. Moscow", "96-08-56", "89272106632", "89457257986", "test-1", "dragon1239@mail.ru", "18", "January", "1995"));
    app.getContactsHelper().submitContactCreation();
    app.getContactsHelper().returnHomePage();
  }
}
