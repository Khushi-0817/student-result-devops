package com.devops.student;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class App {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::createUI);
    }

    private static void createUI() {

        JFrame frame = new JFrame("Student Result Management");
        frame.setSize(950, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        /* ================= GRADIENT HEADER ================= */
        JPanel header = new JPanel() {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(44, 62, 80),
                        getWidth(), getHeight(), new Color(52, 152, 219)
                );
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        header.setPreferredSize(new Dimension(950, 90));
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBorder(new EmptyBorder(20, 30, 20, 30));

        JLabel title = new JLabel("Student Result Management");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(Color.WHITE);

        JLabel subtitle = new JLabel("DevOps Project • Maven • Git • Executable JAR");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(new Color(230, 230, 230));

        header.add(title);
        header.add(Box.createVerticalStrut(5));
        header.add(subtitle);

        frame.add(header, BorderLayout.NORTH);

        /* ================= BODY ================= */
        JPanel body = new JPanel(new GridLayout(1, 2));
        body.setBackground(new Color(245, 247, 250));
        frame.add(body, BorderLayout.CENTER);

        /* ===== LEFT INFO ===== */
        JPanel left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.setBackground(new Color(245, 247, 250));
        left.setBorder(new EmptyBorder(40, 40, 40, 30));

        JLabel infoTitle = new JLabel("About the Application");
        infoTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));

        JTextArea info = new JTextArea(
                "This application demonstrates DevOps practices\n\n" +
                "• Build automation using Maven\n" +
                "• Executable JAR artifact\n" +
                "• Version control using Git\n" +
                "• Reproducible builds\n\n" +
                "UI enhancements do not affect DevOps logic.\n" +
                "Execution happens via command line."
        );
        info.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        info.setEditable(false);
        info.setBackground(left.getBackground());
        info.setBorder(null);

        left.add(infoTitle);
        left.add(Box.createVerticalStrut(15));
        left.add(info);

        body.add(left);

        /* ===== RIGHT CARD ===== */
        JPanel rightWrap = new JPanel(new GridBagLayout());
        rightWrap.setBackground(new Color(245, 247, 250));

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setPreferredSize(new Dimension(380, 430));
        card.setBorder(new CompoundBorder(
                new LineBorder(new Color(220, 220, 220), 1, true),
                new EmptyBorder(25, 25, 25, 25)
        ));

        JTextField name = field("Student Name");
        JTextField m1 = field("Marks - Subject 1");
        JTextField m2 = field("Marks - Subject 2");
        JTextField m3 = field("Marks - Subject 3");

        JButton calc = new JButton("Calculate Result");
        calc.setFont(new Font("Segoe UI", Font.BOLD, 16));
        calc.setBackground(new Color(52, 152, 219));
        calc.setForeground(Color.WHITE);
        calc.setFocusPainted(false);
        calc.setBorder(new EmptyBorder(12, 25, 12, 25));
        calc.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Hover animation
        calc.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                calc.setBackground(new Color(41, 128, 185));
            }
            public void mouseExited(MouseEvent e) {
                calc.setBackground(new Color(52, 152, 219));
            }
        });

        JTextArea result = new JTextArea();
        result.setEditable(false);
        result.setFont(new Font("Segoe UI", Font.BOLD, 15));
        result.setBackground(new Color(245, 248, 250));
        result.setBorder(new CompoundBorder(
                new LineBorder(new Color(200, 200, 200)),
                new EmptyBorder(12, 12, 12, 12)
        ));
        result.setMaximumSize(new Dimension(Integer.MAX_VALUE, 140));

        card.add(name); card.add(space());
        card.add(m1); card.add(space());
        card.add(m2); card.add(space());
        card.add(m3);
        card.add(Box.createVerticalStrut(20));
        card.add(calc);
        card.add(Box.createVerticalStrut(20));
        card.add(result);

        rightWrap.add(card);
        body.add(rightWrap);

        /* ===== ACTION ===== */
        calc.addActionListener(e -> {
            try {
                Student s = new Student(
                        name.getText(),
                        Integer.parseInt(m1.getText()),
                        Integer.parseInt(m2.getText()),
                        Integer.parseInt(m3.getText())
                );

                double p = s.percentage();
                String g = ResultService.grade(p);

                result.setForeground(new Color(39, 174, 96));
                result.setText(
                        "👤 Student : " + s.getName() +
                        "\n📊 Total Marks : " + s.total() +
                        "\n📈 Percentage : " + String.format("%.2f", p) +
                        "\n🏆 Grade : " + g
                );
            } catch (Exception ex) {
                result.setForeground(Color.RED);
                result.setText("⚠ Please enter valid numeric values.");
            }
        });

        /* ================= FOOTER ================= */
        JLabel footer = new JLabel(
                "Built with Maven | Version Controlled with Git | DevOps Oriented Project",
                SwingConstants.CENTER
        );
        footer.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        footer.setBorder(new EmptyBorder(10, 0, 10, 0));
        footer.setForeground(new Color(120, 120, 120));
        frame.add(footer, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private static JTextField field(String title) {
        JTextField f = new JTextField();
        f.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        f.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        f.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180)),
                title
        ));
        return f;
    }

    private static Component space() {
        return Box.createVerticalStrut(10);
    }
}
