package com.company;

public class BoxGenKV<K, V> {
    private K obj1;
    private V obj2;

    // E Element
    // K Key
    // V Value
    // T Type
    // N Number

    public BoxGenKV(K obj1, V obj2) {
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    public K getObj1() {
        return obj1;
    }

    public V getObj2() {
        return obj2;
    }

    public void setObj1(K obj1) {
        this.obj1 = obj1;
    }

    public void setObj2(V obj2) {
        this.obj2 = obj2;
    }

    public void info() {
        System.out.println("Box: " + obj1.toString());
    }
}
