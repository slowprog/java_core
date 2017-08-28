package com.repeat;

public class Main3 {
    public static void main(String[] args) {
        Outer.Inner obj = new Outer(5).new Inner(10); // объект внутреннего класса не существует без объекта внешнего
    }
}
