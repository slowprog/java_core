package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyWindow extends JFrame {
    public MyWindow() {
        setTitle("My Windows");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(200, 400, 400, 400);

        JButton jb1 = new JButton("CENTER");
        JButton jb2 = new JButton("SOUTH");
        JButton jb3 = new JButton("NORTH");
        JButton jb4 = new JButton("WEST");
        JButton jb5 = new JButton("EAST1");
        JButton jb6 = new JButton("EAST2");

        JPanel jpeast = new JPanel();
        jpeast.add(jb5);
        jpeast.add(jb6);
        jpeast.setLayout(new GridLayout());

        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Что-то");
            }
        });

        add(jb1, BorderLayout.CENTER);
        add(jb2, BorderLayout.SOUTH);
        add(jb3, BorderLayout.NORTH);
        add(jb4, BorderLayout.WEST);
        add(jpeast, BorderLayout.EAST);

        setVisible(true);
    }
}
