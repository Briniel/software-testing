import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {
    @Test
    public void testArea(){
        Point p1 = new Point(3, 4);
        Point p2 = new Point(2, 1);
        Assert.assertEquals(p1.distance(p2), 3.1622776601683795);
        Assert.assertEquals(p1.distance(p2), p2.distance(p1));
    }
}
