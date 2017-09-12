package com.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Socket socket = null;
        Scanner inConsole;
        DataInputStream inSocket;
        DataOutputStream outSocket;

        try {
            socket = new Socket("localhost", 8189);

            inSocket = new DataInputStream(socket.getInputStream());
            outSocket = new DataOutputStream(socket.getOutputStream());
            inConsole = new Scanner(new InputStreamReader(System.in, "UTF-8"));

            System.out.println("Клиент запущен и подключился к серверу");

            final DataInputStream threadIn = inSocket;

            // Слушаем сокет на наличие сообщений от сервера
            Thread threadSocket = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String msg;

                        while (true) {
                            if (threadIn.available() == 0) {
                                continue;
                            }

                            msg = threadIn.readUTF();

                            if (!msg.isEmpty()) {
                                System.out.println("Сервер: " + msg);
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

            // Слушаем консоль на наличие сообщений для сервера

            String msg;

            while (inConsole.hasNextLine()) {
                msg = inConsole.nextLine();

                outSocket.writeUTF(msg);
                outSocket.flush();

                if (msg.equalsIgnoreCase("end")) {
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
