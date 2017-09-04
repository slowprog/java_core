package com.company;

import javafx.scene.Parent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessangerWindow extends JFrame {

    public MessangerWindow() {
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
                MessangerWindow.this.sendMessage(textField, textArea);
            }
        });

        buttonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MessangerWindow.this.sendMessage(textField, textArea);
            }
        });

        setVisible(true);
    }

    private void sendMessage(JTextField textField, JTextArea textArea) {
        System.out.println(textField.getText());

        textArea.append(textField.getText() + "\n");
        textField.setText("");
    }
}
