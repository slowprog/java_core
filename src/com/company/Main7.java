package com.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main7 {
    public static void main(String[] args) {
        // System.out.println((new Person("Иван","2",5)).age);
        //
        // try {
        //     System.out.println((new Person("Иван1","3",-5)).age);
        // } catch(PersonException e) {
        //     System.out.println("Забей");
        // }

        // try catch with resources (https://habrahabr.ru/post/178405/ и http://javaway.info/chto-takoe-try-with-resources/)
        // Такой способ позволяет не заботиться о закрытии ресурса реализующего интерфейс java.lang.Autocloseable
        // Он закроется в неявном finally
        try(FileInputStream in = new FileInputStream(("1.txt"))) {
            System.out.println("Работаем с потоком");
        } catch(FileNotFoundException e) {

        } catch(IOException e) {

        }

        System.out.println("Конец");
    }
}
