import sun.jvm.hotspot.oops.ObjectHeap;

import java.io.File;
import java.lang.reflect.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class Main {
    public static void main(String[] args) {
        // Получение ссылки на класса
        Class c = Cat.class;
        // try {
        //     Class c2 = Class.forName("ru.company.Cat");
        // } catch (ClassNotFoundException e) {
        //     e.printStackTrace();
        // }

        Cat cat = new Cat("1", "2", 3);
        Class c3 = cat.getClass();

        // даже у приметивов можно запросить
        Class c4 = int.class;
        Class c5 = void.class;
        Class c6 = char[][].class;

        // Method[] methods = c3.getMethods();
        Method[] methods = c3.getDeclaredMethods();
        for (Method o : methods) {
            System.out.println(o);
        }

        try {
            Method m = c.getDeclaredMethod("printNumber", int.class);

            m.invoke(cat, 10);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            Field fname = Cat.class.getDeclaredField("name");
            String sname = (String)fname.get(cat);
            System.out.println(sname);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        // Можно менять доступ к полям
        try {
            Field f = Cat.class.getDeclaredField("color");
            f.setAccessible(true);
            f.set(cat, "Red");
            cat.info();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        // можно получить что за модификаторы у полей, методов и др.
        try {
            Field ff = Cat.class.getDeclaredField("data");
            int mods = ff.getModifiers();
            System.out.println(mods);
            System.out.println(Modifier.isFinal(mods));
            System.out.println(Modifier.isProtected(mods));
            System.out.println(Modifier.isPrivate(mods));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        // Можно создать объект опосредованно чперез рефлексию
        try {
            Constructor constr = c.getConstructor(String.class, String.class, int.class);
            Cat cat1 = (Cat)constr.newInstance("Bars", "Ik", 5);
            cat1.info();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // Можнополучить доступ к приватному классу внутри другого, но что-то к конструктору доступ не удалось получить
        try {
            Class[] cq = c.getDeclaredClasses();
            for (Class o : cq) {
                System.out.println(o);
            }
            Class cfood = cq[0];
            Constructor cx = cfood.getDeclaredConstructor();
            cx.setAccessible(true);
            Object food = cx.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
                e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // МОжно класс подгрзить в любой директории на компе
        try {
            ClassLoader cl = new URLClassLoader(new URL[] {new File("./").toURL()});
            Class chuman = cl.loadClass("Human");
            Constructor humanconst = chuman.getConstructor(String.class);
            Object human = humanconst.newInstance("Bob");

            Method m3 = chuman.getDeclaredMethod("info");
            m3.invoke(human);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // Можно запускать только те что помечены аннотацией
        for (Method o: methods) {
            if (o.isAnnotationPresent(MyAnno.class)) {
                try {
                    o.invoke(cat);
                    System.out.println(o.getAnnotation(MyAnno.class).priority());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}