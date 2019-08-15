package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class GroupCreatTest extends BaseTest{

    @Test
    public void testGroupCreat() throws Exception {
        goToGroupsPage();
        initGroupCreation();
        fillGroupForm(new GroupData("test-1", "Hi all", "New group"));
        submitGroupCreation();
        returnToGroupsPage();
    }

}
