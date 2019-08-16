package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreatTest extends BaseTest {

    @Test
    public void testGroupCreat() throws Exception {
        app.getNavigationHelper().goToGroupsPage();
        app.getGroupHelper().creatGroup(new GroupData("test-4", null, "New group"));

    }
}
