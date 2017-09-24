package com.company;

import javafx.beans.binding.NumberBinding;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Box box1 = new Box(20);
        Box box2 = new Box(50);

        // ...

        box2.setObj("Java");

        // ...

        int n1 = 0, n2 = 0;

        if (box1.getObj() instanceof Integer) {
            n1 = (Integer) box1.getObj();
        }

        if (box2.getObj() instanceof Integer) {
            n2 = (Integer) box2.getObj();
        }

        System.out.println(n1 + n2);

        // ..................

        BoxGen<Integer> box3 = new BoxGen<>(20);
        BoxGen<Integer> box4 = new BoxGen<>(50);

        // ...

        // box3.setObj("Java");

        // ...

        n1 = (Integer) box3.getObj();
        n2 = (Integer) box4.getObj();

        System.out.println(n1 + n2);


        BoxWithNumbers<Integer> bn1 = new BoxWithNumbers<>(2, 4, 6, 8);
        BoxWithNumbers<Float> bn2 = new BoxWithNumbers<>(2f, 4f, 6f, 8f);

        System.out.println(bn1.avg());
        System.out.println(bn2.avg());

        // ..................

        // double могут быть не равны!
        double a = 0.7;
        double b = 0.7;

        for (int i = 0; i < 70; i++) {
            b += 0.01;
        }

        System.out.println(a == b);
        System.out.println(b);
        // \double
        // ..................

        System.out.println(bn1.compareAvg(bn2));
        System.out.println(bn1.getClass().getName());
        System.out.println(bn2.getClass().getName());

        List<Integer> ln = new ArrayList<Integer>();
        listAvg(ln);

        // ..................

        List<String> a1 = new ArrayList<>();
        List<Integer> a2 = new ArrayList<>();
        a1.add("Java");
        a2.add(123);
        String str1 = getFirstElement(a1);
        // String str2 = getFirstElement(a2);
        int int1 = getFirstElement(a2);
        // int int2 = getFirstElement(a1);

        // ..................

        Integer a3 = 10;
        Double a4 = 10.0;
        String a5 = "10.0";
        // Можно вызвать с разными наследниками Nubmer, и внутри мы будем работать с Number т.к. это единственное что их объединяет
        doSomething(a3, a4);
        // В этом случае не сработает, но если бы там был extends от Object, то было бы можно т.к. у них был бы один родитель Object
        // doSomething(a4, a5);

        // ..................

        List list = new ArrayList<>(); // сырой тип (raw) без отслеживания типа, компилятор не будет следить за содержимым, добавить в него что угодно можно
        list.add("asdf");
        list.add(34);

        // BoxWithNumbers box5 = new BoxWithNumbers(50, 54, "asdf1");

        // ..................

        List<Number> numbers = new ArrayList<>(Arrays.asList(10.0, 20));
        List<Integer> integers= new ArrayList<>(Arrays.asList(10, 20));

        for (Integer o : integers) {
            numbers.add(o);
        }
        // Так нельзя т.к. понижаение в типах нельзя делать, в ссылку на наследника не запихать родителя.
        // for (Number n : numbers) {
        //     integers.add(n);
        // }

        transfer(integers, numbers);
        System.out.println(integers);
        System.out.println(numbers);
        // Так нельзя т.к. дженерик защитил от подобного т.к. super говорит о том что нужен типа выше по наследованию, а extends ниже по наследованию.
        // transfer(numbers, integers);
        // Это например в коллекциях используется, можно поглядеть в исходник функции copy()
        // Collections.copy();

        // ..................

        System.out.println(compare(22, 33));
        System.out.println(compare("22", "asdf"));
        // Так нельзя т.к. они разных типов, а должны реализовывать один обобщённый тип Comparable<T>, если <T> убрать и оставить только Comparable, то ошибка будет на этапе выполнения.
        // System.out.println(compare(22, "asdf"));
    }

    // Можно записать по разному и это имеет отличный смысл: без extends не будет наследников, а только Number!
    // public static double listAvg(List<Number> data) {
    public static double listAvg(List<? extends Number> data) {
        double d = 0.0;

        for (Number o : data) {
            d += o.doubleValue();
        }

        return d / data.size();
    }

    // Неправильный способ
    // public static Object getFirstElement(List<Object> list) {
    // Первое <T> это объявление того что метод собирается использовать дженерик (так можно ему подавать что хочешь на входи и получать что хочешь, а компилятор всё будет проверять на этапе компилции). Также тут <T> можно ограничить по типу с помощью extends
    public static <T> T getFirstElement(List<T> list) {
        return list.get(0);
    }

    public static <U extends Number> void doSomething(U n1, U n2) {
        // ...
    }

    public static <T> void transfer(List<? extends T> src, List<? super T> dst) {
        // foreach нельзя использовать, только fori
        // for (Object o : src) {
        //     dst.add(o);
        // }

        dst.addAll(src);
        src.clear();
    }

    // На вход можно подать только два одинаковых объекта, которые реализуют один и тотже интерфейс Comparable
    public static <T extends Comparable<T>> int compare(T t1, T t2) {
        return t1.compareTo(t2);
    }
}
