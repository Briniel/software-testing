package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupDeletTest extends BaseTest {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0){
            app.group().creat(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupDelet(){
        Set<GroupData> before = app.group().all();
        GroupData deleteGroup = before.iterator().next();
        app.group().delete(deleteGroup);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deleteGroup);
        Assert.assertEquals(after, before);
    }



}
