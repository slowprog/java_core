package com.company;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {


        File file = new File("dir");

        System.out.println(Arrays.toString(file.list()));

        System.out.println(Arrays.toString(file.list((dir, name) -> name.startsWith("1"))));

        // C ламбдами всё становится лаконичнее и красивее
        List<String> data = Arrays.asList("asd", "asdffff", "f");
        List<Integer> list = data.stream()
                .map(s -> s.length())
                .sorted()
                .filter(integer -> integer < 3)
                .map(integer -> integer * 2)
                .collect(Collectors.toList());

        System.out.println(list);

        System.out.println(file.isDirectory());
        System.out.println(file.isFile());
        System.out.println(file.getAbsoluteFile());
        System.out.println(file.getPath());
        System.out.println(file.lastModified());
        System.out.println(file.toURI());

        // Stream – работа с потоком байтов
        // Input – входящий
        // Output – исходящий
        // Reader и Writer – это всё тоже самое но посимвольно, а не побайтово в отличии от Stream.

        // // Побайтово читьать медленно!
        // long t = System.currentTimeMillis();
        // try (FileInputStream in = new FileInputStream("dir/11.txt")) {
        //     int x= -1;
        //     // String s = ""; // Так нельзя т.к. каждый раз при присвоении она будет пересоздаваться и мусорить
        //     // StringBuilder s = new StringBuilder(in.available());
        //     while ((x = in.read()) != -1) {
        //         // s.append((char)x);
        //         // System.out.println((char)x);
        //     }
        //     // System.out.println(s);
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
        // System.out.println(System.currentTimeMillis() - t);

        // Buffered – эти классы работают через буфер, а значит быстрее, как на чтение так и на запись
        // BufferedOutputStream bos = new BufferedOutputStream();

        // PushbackReader - позволят обратно запихивать данные в поток обратно после чтения.

        // ObjectOutputStream - позволяет записывать объекты в файл
        try {
            Student s = new Student("asdf", 40);

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("dir/stud"));
            oos.writeObject(s);
            System.out.println(s);
            oos.close();

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("dir/stud"));
            Student s2 = (Student)ois.readObject();
            System.out.println(s2);
            ois.close();

            Student s3 = new Student("asdf", 40);
            Student s4 = new Student("asdf", 40);
            Book book = new Book();
            book.name = "asdf 1";
            s3.book = book;
            s4.book = book;

            // При сериализации все объекты должны быть Serialazible иначе это не получится
            ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream("dir/stud1"));
            oos1.writeObject(s3);
            book.name = "asdf 2"; // т.к. уже записали, то это изменение не будет запомненно
            oos1.reset(); // но это позволят забыть джаве объекты которые были записаны так что имя новое теперь запишиться во второй объект
            oos1.writeObject(s4);
            oos1.close();
            System.out.println(s3);
            System.out.println(s4);

            ObjectInputStream ois1 = new ObjectInputStream(new FileInputStream("dir/stud1"));
            Student s5 = (Student)ois1.readObject();
            Student s6 = (Student)ois1.readObject();
            System.out.println(s5.book);
            System.out.println(s6.book);
            System.out.println(s5.book.name);
            System.out.println(s6.book.name);
            ois1.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    private static void randomAccessFileEx() {
        // RandomAccessFile - позволяет получить случайный доступ к файлу, как на чтение так и на запись (в этом случайность и есть). Смысл в том, что можно быстро прыгнуть в конце файла и прочитать концовку, в отличиит от InputStream, которые не могут возвращаться и скипать часть байт, он читает поочреди и обратно не возвращается
        try (RandomAccessFile raf = new RandomAccessFile("dir/11.txt", "rw")) {
            raf.seek(1000);// перемещать курсор по файлу
            raf.read();
            raf.write(44);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sequenceEx() {
        // Чтение нескольких потоков как из одного по очереди, переходит к следующему, когда в предыдущем кончаются данные, он сам закрывает их
        try {
            ArrayList<FileInputStream> ali = new ArrayList<>(Arrays.asList(
                    new FileInputStream("1.txt"),
                    new FileInputStream("2.txt"),
                    new FileInputStream("3.txt")
            ));

            SequenceInputStream in = new SequenceInputStream(Collections.enumeration(ali));
            int x = -1;
            while ((x = in.read()) != -1) {
                System.out.println((char)x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void dataOutputEx() {
        // Это вроде удобнее для чтения (записи), но я не помню в чём удобнее, типа для больших массиво данных или неь
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("123/dataOutpu.txt"))) {
            dos.writeLong(55L);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void byteArrayInputEx() {
        // ByteArrayInputStream - поток в байтовый массив
        byte[] arr = {1,2,3,4};
        ByteArrayInputStream in = new ByteArrayInputStream(arr);
        int x = -1;
        while ((x = in.read()) != -1) {
            System.out.println(x);
        }
    }

    private static void readWithCharsetEx() {
        long t = System.currentTimeMillis();
        try (InputStreamReader in = new InputStreamReader(new FileInputStream("dir/rus.txt"), StandardCharsets.UTF_8)) {
            int x= -1;
            while ((x = in.read()) != -1) {
                System.out.print((char)x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\nTime: " + (System.currentTimeMillis() - t));
    }

    private static void readToArrayEx1() {
        // // Кусками читать гораздо быстрее
        long t = System.currentTimeMillis();
        try (FileInputStream in = new FileInputStream("dir/11.txt")) {
            int x= -1;
            byte[] arr = new byte[128];
            while ((x = in.read(arr)) != -1) {
                // s.append((char)x);
                // System.out.println((char)x);
            }
            // System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - t);
    }

    private static void readToArrayEx() {
        // При чтении в массив старые данные из массива не удаляются, а потому в конце можно отхватить старых данных с предыдущей итерации
        try (FileInputStream in = new FileInputStream("dir/12.txt")) {
            int x= -1;
            byte[] arr = new byte[3];
            while ((x = in.read(arr)) > 0) {
                for (int i = 0; i < arr.length; i++) {
                    System.out.print((char)arr[i]);
                }
                System.out.println();
                for (int i = 0; i < x; i++) {
                    System.out.print((char)arr[i]);
                }
                System.out.println();
            }
            // System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readEx() {
        long t = System.currentTimeMillis();
        try (FileOutputStream out = new FileOutputStream("dir/out.txt")) {
            String data1 = "adsfasdfasdfasdf";
            out.write(data1.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\nTime: " + (System.currentTimeMillis() - t));
    }
}
