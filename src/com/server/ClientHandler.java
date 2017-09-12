package com.server;

import javax.xml.crypto.Data;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private Socket socket;
    private Server server;
    private DataOutputStream out;
    private DataInputStream in;
    private String name;

    public ClientHandler(Socket socket, Server server) {
        try {
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.server = server;
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            try {
                // Авторизация клиента по логину / паролю
                while (true) {
                    String str = in.readUTF();

                    if (str.startsWith("/auth")) {
                        String[] elements = str.split(" ");
                        if (elements.length == 3 && elements[1].equals("login") && elements[2].equals("pass")) {
                            sendMessage("/authok");

                            this.name = "client";

                            break;
                        } else {
                            sendMessage("Неверный логин / пароль");
                        }
                    } else {
                        sendMessage("Для начала нужна авторизация");
                    }
                }

                while (true) {
                    String str = in.readUTF();
                    if (str.equalsIgnoreCase("/end")) {
                        break;
                    }

                    System.out.println("Client: " + str);
                    // sendMessage("str: " + str);
                    server.broadcast(str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                server.unsubscribeMe(this);

                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void sendMessage(String msg) {
        try {
            out.writeUTF(msg);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }
}
