package com.company;

public class Main {
    public static void main(String[] args) {
        int result = 0;

        try {
            result = sum(new String[][] {
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
            });
        } catch (MyArraySizeException e) {
            System.out.println(e.getMessage());
            result = 0;
        } catch (MyArrayDataException e) {
            System.out.println(e.getMessage());
            result = 0;
        }

        System.out.println("Результат = " + result + "\n");

        try {
            result = sum(new String[][] {
                    {"1", "2", "3", "4"},
                    {"1", "2", "3", "4"},
                    {"1", "2", "3"},
                    {"1", "2", "3", "4"},
            });
        } catch (MyArraySizeException e) {
            System.out.println(e.getMessage());
            result = 0;
        } catch (MyArrayDataException e) {
            System.out.println(e.getMessage());
            result = 0;
        }

        System.out.println("Результат = " + result + "\n");

        try {
            result = sum(new String[][] {
                    {"1", "2", "3", "4"},
                    {"1", "2", "3", "4"},
                    {"1", "2", "3", "4"},
            });
        } catch (MyArraySizeException e) {
            System.out.println(e.getMessage());
            result = 0;
        } catch (MyArrayDataException e) {
            System.out.println(e.getMessage());
            result = 0;
        }

        System.out.println("Результат = " + result + "\n");

        try {
            result = sum(new String[][] {
                    {"1", "2", "3", "4"},
                    {"1", "2", "3", "4"},
                    {"1", "2", "3", "4"},
                    {"l", "2", "3", "4"},
            });
        } catch (MyArraySizeException e) {
            System.out.println(e.getMessage());
            result = 0;
        } catch (MyArrayDataException e) {
            System.out.println(e.getMessage());
            result = 0;
        }

        System.out.println("Результат = " + result + "\n");
    }

    public static int sum(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if (array.length != 4) {
            throw new MyArraySizeException("Неверный размер кол-ва строк в двумерном массиве");
        }

        int result = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i].length != 4) {
                throw new MyArraySizeException("Неверный размер массива в строке " + i);
            }

            for (int j = 0; j < array[i].length; j++) {
                try {
                    result += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Неверный формат данных в строке " + i + " и столбце " + j + " (" + array[i][j] + ")");
                }
            }
        }

        return result;
    }
}
