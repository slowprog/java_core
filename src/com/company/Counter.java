package com.company;

public class Counter {
    private volatile int x; // volatile пользование переменных, которое не кешируется, переменная содержит данные которые не могут быть повреждены, они не могут кешироваться

    public Counter() {
        this.x = 0;
    }

    public synchronized int getX() {
        return this.x;
    }

    public synchronized void inc() { // synchronized, никто не может прервать работу этого метода пока он не закончит, т.е. два раза этот метод (или другой synchronized) параллелно не вызвать у одного объекта
        this.x++;
    }

    public synchronized void dec() {
        this.x--;
    }
}
