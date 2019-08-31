package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupCreatTest extends BaseTest {

    @Test
    public void testGroupCreat(){
        app.goTo().groupPage();
        Set<GroupData> before = app.group().all();
        GroupData group = new GroupData().withName("test2");
        app.group().creat(group);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(group);
        Assert.assertEquals(after, before);
    }
}
