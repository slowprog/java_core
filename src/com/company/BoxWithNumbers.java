package com.company;

// Дженерик только для чисел! Тип должен быть либо Number либо его наследником
public class BoxWithNumbers<T extends Number> { // public class BoxWithNumbers<T extends Number & Serializable> { // можно сразу от нескольких
    private T[] arr;

    // public BoxWithNumbers(T[] arr) {
    //     this.arr= arr;
    // }

    public BoxWithNumbers(T... arr) {
        this.arr= arr;
    }

    public double avg() {
        double d = 0.0;

        for (int i = 0; i < this.arr.length; i++) {
            d += this.arr[i].doubleValue();
        }

        d /= this.arr.length;

        return d;
    }

    // ? это любой тип (маска)
    // ? extends Number это только тип Number
    // Либо убрать вообще дженерик и это всё будет равной записью
    // public boolean compareAvg(BoxWithNumbers<? extends Number> another) {
    // public boolean compareAvg(BoxWithNumbers<?> another) {
    public boolean compareAvg(BoxWithNumbers another) {
        // double могут быть не равны, даже если внешне равны, а потому их разницу сравнивают с дельтой (с погрешностью)
        return Math.abs(avg() - another.avg()) < 0.001;
    }
}
