package com.client;

import javafx.scene.Parent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatWindow extends JFrame {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private JPanel bottomPanel, upperPanel;
    private JTextField textField;
    private JTextArea textArea;
    private JTextArea jtaUsers;
    private JScrollPane jspUsers;

    private boolean isAuthorized;
    private String name;

    public void setAuthorized(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;

        upperPanel.setVisible(!this.isAuthorized);
        bottomPanel.setVisible(this.isAuthorized);
        jspUsers.setVisible(this.isAuthorized);

        if (this.isAuthorized) {
            setTitle("Чат: " + this.name);
            this.textField.requestFocus();
        } else {
            setTitle("Чат не авторизован");
        }
    }

    public ChatWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(200, 400, 400, 200);
        setResizable(true);
        setLayout(new BorderLayout());

        this.textField = new JTextField();
        JButton buttonSend = new JButton("Послать");

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        bottomPanel.add(this.textField, BorderLayout.CENTER);
        bottomPanel.add(buttonSend, BorderLayout.EAST);

        upperPanel = new JPanel(new GridLayout(1,3));
        JTextField jtfLogin = new JTextField();
        JPasswordField jtfPass= new JPasswordField();
        JButton jbAuth = new JButton("Login");
        upperPanel.add(jtfLogin);
        upperPanel.add(jtfPass);
        upperPanel.add(jbAuth);

        jbAuth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String passText = new String(jtfPass.getPassword());

                    out.writeUTF("/auth " + jtfLogin.getText() + " " + passText);
                    jtfLogin.setText("");
                    jtfPass.setText("");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }


            }
        });

        textArea = new JTextArea();

        textArea.setBackground(Color.lightGray);
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);

        jtaUsers = new JTextArea();
        jtaUsers.setPreferredSize(new Dimension(150, 150));
        jtaUsers.setEditable(false);

        jspUsers = new JScrollPane(jtaUsers);

        add(upperPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        add(jspUsers, BorderLayout.EAST);

        this.textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChatWindow.this.sendMessage();
            }
        });

        buttonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChatWindow.this.sendMessage();
            }
        });

        try {
            socket = new Socket("localhost", 8189);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Цикл для повторного подключения, после отключения
                    while (true) {
                        // Цикл для принятия ответа об авторизации
                        while (true) {
                            String msg = in.readUTF();
                            if (msg.startsWith("/authok")) {
                                String[] elements = msg.split(" ");
                                ChatWindow.this.name = elements[1];

                                setAuthorized(true);

                                break;
                            } else if (msg.startsWith("/userlist")) {
                                // ...
                            } else {
                                ChatWindow.this.textArea.append(msg + "\n");
                                ChatWindow.this.textArea.setCaretPosition(ChatWindow.this.textArea.getDocument().getLength());
                            }
                        }

                        // Цикл для принятия сообщений
                        while (true) {
                            String msg = in.readUTF();

                            if (msg.startsWith("/")) {
                                if (msg.equalsIgnoreCase("/end")) {
                                    break;
                                } else if (msg.startsWith("/userlist")) {
                                    String[] users = msg.split(" ");
                                    jtaUsers.setText("");

                                    for (int i = 1; i < users.length; i++) {
                                        jtaUsers.append(users[i] + "\n");
                                    }
                                }

                            } else {
                                if (!msg.isEmpty()) {
                                    ChatWindow.this.textArea.append(msg + "\n");
                                }
                                ChatWindow.this.textArea.setCaretPosition(ChatWindow.this.textArea.getDocument().getLength());
                            }
                        }

                        setAuthorized(false);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    setAuthorized(false);
                }
            }
        });

        setAuthorized(false);

        t1.setDaemon(true);
        t1.start();

        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);

                try {
                    out.writeUTF("/end");
                    socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                    setAuthorized(false);
                }
            }
        });
    }

    private void sendMessage() {
        String msg = this.textField.getText();

        if (msg.isEmpty()) {
            return;
        }

        this.textField.setText("");

        try {
            out.writeUTF(msg);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
