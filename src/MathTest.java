@TestClass
public class MathTest {
    private Math math;

    @BeforeSuite
    public void before() {
        System.out.println("Тест начинается (before)");
        this.math = new Math();
    }

    @AfterSuite
    public void after() {
        System.out.println("Тест окончен (after)");
    }

    @Test
    public void addTest() {
        if (this.math.add(1, 2) != 3) {
            throw new RuntimeException("Тест не пройден");
        }
    }

    @Test
    public void divTest() {
        if (this.math.sub(4, 2) != 2) {
            throw new RuntimeException("Тест не пройден");
        }
    }
}
