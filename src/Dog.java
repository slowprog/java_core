import java.lang.annotation.Retention;

@XTable(name = "dogs")
public class Dog {
    @XField
    String name;

    @XField
    String color;

    @XField
    int age;

    public Dog(String name, String color, int age) {
        this.name = name;
        this.color = color;
        this.age = age;
    }
}
