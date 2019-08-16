package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupDeletTest extends BaseTest {

    @Test
    public void testGroupDelet() throws Exception {
        app.getNavigationHelper().goToGroupsPage();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().creatGroup(new GroupData("test-4", null, "New group"));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deletSelectGroup();
        app.getGroupHelper().returnToGroupsPage();
    }

}
