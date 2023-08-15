package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentPage extends JFrame {
    private JLabel studentNameLabel;

    public StudentPage(String studentName) {
        initializeUI(studentName);
    }

    private void initializeUI(String studentName) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Student Page");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        studentNameLabel = new JLabel("Welcome " + studentName);
        studentNameLabel.setHorizontalAlignment(JLabel.CENTER);

        JButton viewPastTestsButton = new JButton("Past Tests");
        JButton practiceButton = new JButton("Sove Test");

        viewPastTestsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openPastTests(studentName); // PastTests'i açmak için metod çaðrýsý
            }
        });

        practiceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openSolveTestPage(studentName); // SolveTestPage'i açmak için metod çaðrýsý
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(viewPastTestsButton);
        buttonPanel.add(practiceButton);

        add(studentNameLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    // open PastTests
    private void openPastTests(String studentName) {
        PastTests pastTests = new PastTests(studentName);
        pastTests.setVisible(true);
        dispose(); // Student Page'ni kapat
    }

    // open SolveTestPage
    private void openSolveTestPage(String studentName) {
        SolveTestPage solveTestPage = new SolveTestPage(studentName);
        solveTestPage.setVisible(true);
        dispose(); // Student Page'ni kapat
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentPage("Student").setVisible(true);
            }
        });
    }
}

