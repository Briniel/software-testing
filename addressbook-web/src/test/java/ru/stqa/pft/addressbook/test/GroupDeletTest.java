package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;

public class GroupDeletTest extends BaseTest {

    @Test
    public void testGroupDelet() throws Exception {
        app.getNavigationHelper().goToGroupsPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deletSelectGroup();
        app.getGroupHelper().returnToGroupsPage();
    }

}
