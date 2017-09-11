package com.client;

import javafx.scene.Parent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatWindow extends JFrame {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public ChatWindow() {
        setTitle("Мессенджер");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(200, 400, 400, 400);
        setResizable(true);
        setLayout(new BorderLayout());

        JTextField textField = new JTextField();
        JButton buttonSend = new JButton("Послать");

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());

        jPanel.add(textField, BorderLayout.CENTER);
        jPanel.add(buttonSend, BorderLayout.EAST);

        JTextArea textArea = new JTextArea();

        textArea.setBackground(Color.lightGray);
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);

        add(scrollPane, BorderLayout.CENTER);
        add(jPanel, BorderLayout.SOUTH);

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
                        if (!msg.isEmpty()) {
                            textArea.append(msg + "\n");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();

        setVisible(true);
    }

    private void sendMessage(JTextField textField, JTextArea textArea) {
        // System.out.println(textField.getText());

        // textArea.append(textField.getText() + "\n");
        String msg = textField.getText();
        textField.setText("");

        try {
            out.writeUTF(msg);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
