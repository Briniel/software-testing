package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTest extends BaseTest {

    @Test
    public void testGroupModificationTest() {
        app.getNavigationHelper().goToGroupsPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test-2", "test-2", "test-2"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupsPage();
    }
}
