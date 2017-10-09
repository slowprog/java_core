import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.*;

public class CalcTest {

    Calc calc;

    @Before
    public void init() {
        calc = new Calc();
        System.out.println("@Before");
    }

    @Test
    public void addTest1() {
        Assert.assertEquals(4, calc.add(2, 2));
        Assert.assertEquals(5, calc.add(1, 4));

        // String q = "Java";
        //
        // Assert.assertThat(q, new Matcher<String>() {
        //     public boolean matches(Object o) {
        //         return (String)o.;
        //     }
        //
        //     public void describeMismatch(Object o, Description description) {
        //
        //     }
        //
        //     public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {
        //
        //     }
        //
        //     public void describeTo(Description description) {
        //
        //     }
        // });
    }

    @Test
    public void addTest2() {
        Assert.assertEquals(11, calc.add(5, 6));
        Assert.assertEquals(8, calc.add(5, 3));
    }

    @Test
    @Ignore
    public void addTest3() {
        Assert.assertEquals(12, calc.add(6, 6));
        Assert.assertEquals(7, calc.add(4, 3));
    }

    //Проверка на выброс исключения
    @Test(expected = ArithmeticException.class)
    public void divBy0() {
        calc.div(10, 0);
    }

    // Поймать таймаут длинный, если слишком долго работает
    @Test(timeout = 1000)
    public void catchTimeout() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After
    public void shutdown() {
        System.out.println("@After");
    }
}
