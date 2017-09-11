package com.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ServerSocket server = null;
        Socket socket = null;

        try {
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен, ждём клиентов");
            socket = server.accept(); // Режим ожидания сервера, возвращает сокет, блокирет выполнение кода

            System.out.println("Клиент подключился");

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            while (true) {
                String msg = in.readUTF();
                System.out.println("Клиента: " + msg);
                out.writeUTF("echo: " + msg); // Записываю в буфер
                out.flush(); // Сбрасываем содержимое буфера в канал

                if (msg.equalsIgnoreCase("end")) {
                    break;
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
