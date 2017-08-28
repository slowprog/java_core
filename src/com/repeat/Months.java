package com.repeat;

public enum Months { // тип класса - перечисление
    JANUARY(1, "январь"), FEBRUARY(2, "февраль"), MARCH(3, "март"), APRIL(4, "апрель"), MAY(5, "май"), JUNY(6, "июнь"), JULY(7, "июль"), AUGUST(8, "август"), SEPTEMBER(9, "сентябрь"), NOVEMBER(10, "ноябрь"), OCTOBER(11, "октябрь"), DECEMBER(12, "декабрь");

    int number;
    String ruName;


    private Months(int number, String ruName) { // private по умолчанию
        this.number = number;
        this.ruName = ruName;
    }
}
