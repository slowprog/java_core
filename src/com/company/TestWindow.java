package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestWindow extends JFrame {
    public TestWindow() {
        setTitle("Test Windows");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(200, 400, 400, 400);
        setResizable(true);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JTextField jtf = new JTextField();
        // jtf.setPreferredSize(new Dimension(100, 30));

        JPanel jPanel = new JPanel();

        JButton jb1 = new JButton("JB1");
        JButton jb2 = new JButton("JB2");
        JButton jb3 = new JButton("JB3");
        JButton jb4 = new JButton("JB4");

        jb2.setPreferredSize(new Dimension(100, 50));

        jPanel.add(jb1);
        jPanel.add(jb2);
        jPanel.add(jb3);
        jPanel.add(jb4);

        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new FlowLayout());

        JTextArea jTextArea1 = new JTextArea();
        JTextArea jTextArea2 = new JTextArea();
        JTextArea jTextArea3 = new JTextArea();

        jTextArea1.setPreferredSize(new Dimension(100, 50));
        jTextArea2.setPreferredSize(new Dimension(100, 50));
        jTextArea3.setPreferredSize(new Dimension(100, 50));

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(100,50));
        // scrollPane.setLayout();
        scrollPane.add(jTextArea2);
        jPanel1.add(jTextArea1);
        jPanel1.add(scrollPane);
        jPanel1.add(jTextArea3);

        jtf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(jtf.getText());
                jTextArea1.append(jtf.getText() + "\n");
                jTextArea2.append(jtf.getText() + "\n");
                jtf.setText("");
            }
        });
        
        add(jtf);
        add(jPanel);
        add(jPanel1);

        setVisible(true);
    }
}
