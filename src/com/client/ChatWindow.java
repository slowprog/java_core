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

    private boolean isAuthorized;

    public void setAuthorized(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;

        upperPanel.setVisible(!this.isAuthorized);
        bottomPanel.setVisible(this.isAuthorized);
    }

    public ChatWindow() {
        setTitle("Мессенджер");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(200, 400, 400, 400);
        setResizable(true);
        setLayout(new BorderLayout());

        JTextField textField = new JTextField();
        JButton buttonSend = new JButton("Послать");

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        bottomPanel.add(textField, BorderLayout.CENTER);
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
                    out.writeUTF("/auth " + jtfLogin.getText() + " " + jtfPass.getText());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }


            }
        });

        JTextArea textArea = new JTextArea();

        textArea.setBackground(Color.lightGray);
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);

        add(upperPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChatWindow.this.sendMessage(textField, textArea);
            }
        });

        buttonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChatWindow.this.sendMessage(textField, textArea);
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
                    while (true) {
                        String msg = in.readUTF();
                        if (msg.equals("/authok")) {
                            setAuthorized(true);
                            break;
                        }

                        textArea.append(msg + "\n");
                        textArea.setCaretPosition(textArea.getDocument().getLength());
                    }

                    while (true) {
                        String msg = in.readUTF();
                        if (!msg.isEmpty()) {
                            textArea.append(msg + "\n");
                        }
                        textArea.setCaretPosition(textArea.getDocument().getLength());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    setAuthorized(false);
                }
            }
        });

        t1.start();

        setVisible(true);
        setAuthorized(false);

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

    private void sendMessage(JTextField textField, JTextArea textArea) {
        String msg = textField.getText();

        if (msg.isEmpty()) {
            return;
        }

        textField.setText("");

        try {
            out.writeUTF(msg);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
