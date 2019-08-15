package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class GroupDeletTest extends BaseTest {

    @Test
    public void testGroupDelet() throws Exception {
        goToGroupsPage();
        selectGroup();
        deletSelectGroup();
        returnToGroupsPage();
    }

}
