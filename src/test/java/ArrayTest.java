import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArrayTest {
    Array array;

    @Before
    public void init() {
        array = new Array();
    }

    @Test
    public void searchTest() {
        Assert.assertArrayEquals(new int[] {}, array.search(new int[] {1, 2, 3, 4}));
        Assert.assertArrayEquals(new int[] {6, 8}, array.search(new int[] {1, 2, 3, 4, 6, 8}));
        Assert.assertArrayEquals(new int[] {2, 3, 1, 6, 8}, array.search(new int[] {4, 2, 3, 1, 6, 8}));
    }

    @Test(expected = RuntimeException.class)
    public void searchExeptionTest() {
        array.search(new int[] {1, 2, 3});
        array.search(new int[] {});
    }

    @Test
    public void checkTrueTest() {
        Assert.assertTrue(array.check(new int[] {1, 1, 4}));
        Assert.assertTrue(array.check(new int[] {1, 1, 4, 4, 4}));
    }

    @Test
    public void checkFalseTest() {
        Assert.assertFalse(array.check(new int[] {}));
        Assert.assertFalse(array.check(new int[] {1, 2, 3, 4}));
        Assert.assertFalse(array.check(new int[] {2, 3}));
        Assert.assertFalse(array.check(new int[] {1, 1, 1}));
        Assert.assertFalse(array.check(new int[] {4, 4, 4}));
    }
}
