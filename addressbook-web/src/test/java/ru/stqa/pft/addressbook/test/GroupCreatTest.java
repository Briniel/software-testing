package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupCreatTest extends BaseTest {

    @Test
    public void testGroupCreat() throws Exception {
        app.getNavigationHelper().goToGroupsPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().creatGroup(new GroupData("test-5", null, "New group"));
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1);
    }
}
