package ru.stqa.pft.addressbook.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreatTest extends BaseTest {

    @DataProvider
    public Iterator<Object[]> valiGroups() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new GroupData().withName("tset1").withHeader("header 1").withFooter("footer 1")});
        list.add(new Object[]{new GroupData().withName("tset2").withHeader("header 2").withFooter("footer 2")});
        list.add(new Object[]{new GroupData().withName("tset3").withHeader("header 3").withFooter("footer 3")});
        return list.iterator();
    }

    @Test(dataProvider = "valiGroups")
    public void testGroupCreat(GroupData group) {
        app.goTo().groupPage();
        Groups before = app.group().all();
        app.group().creat(group);
        assertThat(app.group().count(), equalTo(before.size() + 1));
        Groups after = app.group().all();
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test
    public void testBadGroupCreat() {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test2'");
        app.group().creat(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.group().all();
        assertThat(after, equalTo(before));
    }
}
