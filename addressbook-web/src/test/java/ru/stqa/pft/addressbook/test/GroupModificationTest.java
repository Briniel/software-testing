package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupModificationTest extends BaseTest {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0){
            app.group().creat(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupModificationTest() {
        Set<GroupData> before = app.group().all();
        GroupData modifyGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modifyGroup.getId()).withName("test-1").withHeader("test-2").withFooter("test-2");
        app.group().modify(group);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifyGroup);
        before.add(group);
        Assert.assertEquals(after, before);
    }

}
