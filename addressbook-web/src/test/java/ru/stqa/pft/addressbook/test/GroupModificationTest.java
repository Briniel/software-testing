package ru.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModificationTest extends BaseTest {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().creat(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupModificationTest() {
        Groups before = app.db().groups();
        GroupData modifyGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modifyGroup.getId()).withName("test-1").withHeader("test-2").withFooter("test-2");
        app.goTo().groupPage();
        app.group().modify(group);
        assertEquals(app.group().count(), before.size());
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.withOut(modifyGroup).withAdded(group)));
        verifyGroupListInUI();
    }



}
