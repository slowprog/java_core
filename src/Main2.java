import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.*;
import java.util.HashMap;

public class Main {
    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement ps;

    public static void main(String[] args) throws Exception {
        prepareTable(Dog.class);
        Dog dog = new Dog("Bobik", "Black", 5);
        putObjectToDB(dog);
    }

    public static void prepareTable(Class c) {
        HashMap<Class, String> hm = new HashMap<>();
        hm.put(int.class, "INTEGER");
        hm.put(String.class, "TEXT");
        try {
            connect();
            String query = "CREATE TABLE IF NOT EXISTS ";
            query += ((XTable) c.getAnnotation(XTable.class)).name();
            // CREATE TABLE IF NOT EXISTS students
            query += " (";
            Field[] fields = c.getDeclaredFields();
            for (Field o : fields) {
                if (o.isAnnotationPresent(XField.class)) {
                    query += o.getName() + " " + hm.get(o.getType()) + ", ";
                }
            }
            // CREATE TABLE IF NOT EXISTS students (id INTEGER, name TEXT,
            query = query.substring(0, query.length() - 2);
            query += ");";
            // CREATE TABLE IF NOT EXISTS students (id INTEGER, name TEXT);
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public static void putObjectToDB(Object obj) {
        try {
            connect();
            String query = "INSERT INTO ";
            Class c = obj.getClass();
            query += ((XTable) c.getAnnotation(XTable.class)).name();
            query += " (";
            // INSERT INTO students (
            Field[] fields = c.getDeclaredFields();
            for (Field o : fields) {
                if (o.isAnnotationPresent(XField.class)) {
                    query += o.getName() + ", ";
                }
            }
            query = query.substring(0, query.length() - 2);
            // INSERT INTO students (id, name, score
            query += ") VALUES (";
            // INSERT INTO students (id, name, score) VALUES (
            for (Field o : fields) {
                if (o.isAnnotationPresent(XField.class)) {
                    query += "?, ";
                }
            }
            query = query.substring(0, query.length() - 2);
            query += ");";
            // INSERT INTO students (id, name, score) VALUES (?, ?, ?);
            ps = connection.prepareStatement(query);
            int counter = 1;
            for (Field o : fields) {
                if (o.isAnnotationPresent(XField.class)) {
                    ps.setObject(counter, o.get(obj));
                    counter++;
                }
            }
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void connect() throws Exception {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:1.db");
        stmt = connection.createStatement();
    }
}
