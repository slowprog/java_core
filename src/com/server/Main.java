package com.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static ServerSocket server = null;
    public static Socket socket = null;
    public static Scanner inConsole = null;
    public static DataInputStream inSocket = null;
    public static DataOutputStream outSocket = null;

    public static void main(String[] args) {
        try {
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен, ждём клиентов");
            socket = server.accept(); // Режим ожидания сервера, возвращает сокет, блокирет выполнение кода

            System.out.println("Клиент подключился");

            inConsole = new Scanner(new InputStreamReader(System.in, "UTF-8"));
            inSocket = new DataInputStream(socket.getInputStream());
            outSocket = new DataOutputStream(socket.getOutputStream());

            final DataInputStream threadIn = inSocket;

            // Слушаем сокет на наличие сообщений от клиента
            Thread threadSocket = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String msg;

                        while (true) {
                            // if (inSocket.available() == 0) {
                            //     continue;
                            // }

                            msg = threadIn.readUTF();

                            if (!msg.isEmpty()) {
                                System.out.println("Клиент: " + msg);
                            }

                            if (msg.equalsIgnoreCase("end")) {
                                System.exit(0);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            threadSocket.start();

            String msg;

            while (inConsole.hasNextLine()) {
                msg = inConsole.nextLine();

                try {
                    outSocket.writeUTF(msg);
                    outSocket.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (msg.equalsIgnoreCase("end")) {
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Не удалось запустить сервер");
        } finally {
            try {
                server.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
