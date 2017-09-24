package com.company;

// Comparable – обобщённый интерфейс, которому можно задать типа дженерик, чтобы они использовались в имплементируемых методах
public class Cat implements Comparable<Cat> {
    @Override
    public int compareTo(Cat o) {
        return 0;
    }
}
