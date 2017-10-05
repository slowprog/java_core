package ru.geekbrains.java3.lesson4.mt.p0_homework;

import java.io.*;
import java.util.ArrayList;

public class Test {
    public static ArrayList<byte[]> dataToFile;

    public static void main(String[] args) {
        try
        {
            File myFolder = new File(".");
            File[] files = myFolder.listFiles();
            dataToFile = new ArrayList<>();
            for (File file : files) {
                if (!file.isDirectory()) {
                    dataToFile.add(readContentIntoByteArray(new File(file.getCanonicalPath())));
                    System.out.println("Теперь в массиве: " + dataToFile.toString());
                }
            }

            ByteArrayOutputStream outArray = new ByteArrayOutputStream();
            for (int i = 0; i < dataToFile.size(); ++i) {
                outArray.write(dataToFile.get(i));
                System.out.println("Добавляем в out: " + dataToFile.get(i));
            }

            writeByteArrayToFile(outArray);
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
    }

    private static void writeByteArrayToFile(ByteArrayOutputStream outArray) {
        FileOutputStream out = null;
        try {
            String fName = "output.txt";

            out = new FileOutputStream(fName, false);
            System.out.println("Записываем результат 'сшивания' файлов из текущей " + "директории в файл " + fName);
            out.write(outArray.toByteArray());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static byte[] readContentIntoByteArray(File file)
    {
        FileInputStream fileInputStream = null;
        byte[] bFile = new byte[(int) file.length()];
        try
        {
            /*
             * Конвертируем файл в массив типа byte
             */
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return bFile;
    }
}
