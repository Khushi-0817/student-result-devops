package com.devops.student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultUI extends JFrame {

    public ResultUI() {
        setTitle("Student Result Management - DevOps Project");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));

        JLabel nameLabel = new JLabel("Student Name:");
        JTextField nameField = new JTextField();

        JLabel m1Label = new JLabel("Marks 1:");
        JTextField m1Field = new JTextField();

        JLabel m2Label = new JLabel("Marks 2:");
        JTextField m2Field = new JTextField();

        JLabel m3Label = new JLabel("Marks 3:");
        JTextField m3Field = new JTextField();

        JButton button = new JButton("Calculate Result");
        JLabel resultLabel = new JLabel(" ");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    int m1 = Integer.parseInt(m1Field.getText());
                    int m2 = Integer.parseInt(m2Field.getText());
                    int m3 = Integer.parseInt(m3Field.getText());

                    Student s = new Student(name, m1, m2, m3);
                    double percentage = s.percentage();
                    String grade = ResultService.grade(percentage);

                    resultLabel.setText(
                            "<html>Total: " + s.total() +
                            "<br>Percentage: " + String.format("%.2f", percentage) +
                            "<br>Grade: " + grade + "</html>"
                    );

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid inputs");
                }
            }
        });

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(m1Label);
        panel.add(m1Field);
        panel.add(m2Label);
        panel.add(m2Field);
        panel.add(m3Label);
        panel.add(m3Field);
        panel.add(button);
        panel.add(resultLabel);

        add(panel);
    }
}
