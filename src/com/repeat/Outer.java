package com.repeat;

// Вложенность класссов
public class Outer {
    class Inner {
        private int innerX = 10;

        public Inner (int x) {
            this.innerX = x;
        }

        public void test() {
            System.out.println("Inner – " + innerX);
            System.out.println("Outer – " + outerX); // внутренний класс имеет доступ к private-полям внешнего
        }
    }

    private int outerX = 10;

    public Outer(int x) {
        this.outerX = x;
    }

    public void outerTest() {
        // System.out.println("Inner – " + innerX);
        System.out.println("Outer – " + outerX);
    }
}
