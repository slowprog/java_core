
@XTable(name = "students")
public class Student {
    @XField
    int id;

    @XField
    String name;

    @XField
    int score;

    public Student(int id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }
}
