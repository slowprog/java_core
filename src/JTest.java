import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.PriorityQueue;

public class JTest {
    public static void start(Class c) {
        if (!c.isAnnotationPresent(TestClass.class)) {
            System.out.println("Класс " + c.getName() + " не является тестом");

            return;
        }

        System.out.println("Запустился тест " + c.getName());

        Method[] methods = c.getDeclaredMethods();

        PriorityQueue<Method> testsQueue = new PriorityQueue<>(new Comparator<Method>() {
            @Override
            public int compare(Method o1, Method o2) {
                int methodPriority1 = ((Test) o1.getAnnotation(Test.class)).priority();
                int methodPriority2 = ((Test) o2.getAnnotation(Test.class)).priority();

                return methodPriority1 - methodPriority2;
            }
        });

        Method methodBeforeSuite = null;
        Method methodAfterSuite = null;

        for (Method m : methods) {
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                if (methodBeforeSuite != null) {
                    throw new RuntimeException("Аннотация @BeforeSuite должна быть одна");
                }

                methodBeforeSuite = m;
            } else if (m.isAnnotationPresent(AfterSuite.class)) {
                if (methodAfterSuite != null) {
                    throw new RuntimeException("Аннотация @AfterSuite должна быть одна");
                }

                methodAfterSuite = m;
            } else if (m.isAnnotationPresent(Test.class)) {
                testsQueue.add(m);
            }
        }

        try {
            Object object = c.getConstructor().newInstance();

            if (methodBeforeSuite != null) {
                methodBeforeSuite.invoke(object);
            }

            Method testMethod;

            while ((testMethod = testsQueue.poll()) != null)  {
                try {
                    System.out.print("Запускаем тест " + testMethod.getName() + "... ");
                    testMethod.invoke(object);
                    System.out.println("Ок");
                } catch (Exception e) {
                    System.out.println("Fail");
                }
            }

            if (methodAfterSuite != null) {
                methodAfterSuite.invoke(object);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
