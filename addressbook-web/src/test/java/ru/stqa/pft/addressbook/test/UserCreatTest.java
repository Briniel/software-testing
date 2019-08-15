package ru.stqa.pft.addressbook.test;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.UserData;

public class UserCreatTest extends BaseTest {

  @Test
  public void testUserCreat() throws Exception {
    app.getNavigationHelper().goToUserCreatePage();
    app.getCreateNewUserHelper().createNewUser(new UserData("Mikhail", "Alekseevich", "Ivanov", "BSS", "Brin", "c. Moscow", "96-08-56", "89272106632", "89457257986", "test-1", "dragon1239@mail.ru", "18", "January", "1995"));
    app.getNavigationHelper().goToHomePage();
  }
}
