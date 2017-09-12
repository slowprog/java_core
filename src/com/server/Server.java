package com.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

public class Server {
    private final int PORT = 8189;
    private Vector<ClientHandler> clients;

    public Server() {
        ServerSocket server = null;
        Socket socket = null;
        clients = new Vector<>();

        try {
            server = new ServerSocket(PORT);
            System.out.println("Сервер запущен, ждём клиентов");

            while (true) {
                socket = server.accept(); // Режим ожидания сервера, возвращает сокет, блокирет выполнение кода
                subscribeMe(new ClientHandler(socket, this));

                System.out.println("Клиент подключился");
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

    public void broadcast(String msg) {
        for (ClientHandler c: clients) {
            c.sendMessage(msg);
        }
    }

    public void subscribeMe(ClientHandler client) {
        clients.add(client);
    }

    public void unsubscribeMe(ClientHandler client) {
        clients.remove(client);
    }
}
