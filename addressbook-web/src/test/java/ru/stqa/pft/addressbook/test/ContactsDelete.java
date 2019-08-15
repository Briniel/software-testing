package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;

public class ContactsDelete extends BaseTest{

    @Test
    public void testContactsDelete() throws Exception {
        app.getContactsHelper().selectContact();
        app.getContactsHelper().deleteSelectContact();
        app.getContactsHelper().acceptAlert();
    }
}
