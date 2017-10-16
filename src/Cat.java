public class Cat {
    private class Food {}
    String name;
    int age;

    private String color;

    private static final int data = 10;

    public Cat(String name, String color, int age) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    private void privateMethod() {
        System.out.println("cat private method");
    }

    public void printNumber(int n) {
        System.out.println(n);
    }

    public void info() {
        System.out.println(name + " " + color + " " + age);
    }

    @MyAnno(priority = 5)
    public void method1() {
        System.out.println("m1");
    }

    public void method2() {
        System.out.println("m2");
    }

    @MyAnno
    public void method3() {
        System.out.println("m3");
    }
}
