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
        setTitle("��renci Paneli");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        studentNameLabel = new JLabel("Ho� Geldin " + studentName);
        studentNameLabel.setHorizontalAlignment(JLabel.CENTER);

        JButton viewPastTestsButton = new JButton("Ge�mi� Testler");
        JButton practiceButton = new JButton("Al��t�rma Yap");

        viewPastTestsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openPastTests(studentName); // PastTests'i a�mak i�in metod �a�r�s�
            }
        });

        practiceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openSolveTestPage(studentName); // SolveTestPage'i a�mak i�in metod �a�r�s�
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(viewPastTestsButton);
        buttonPanel.add(practiceButton);

        add(studentNameLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    // PastTests'i a�an metod
    private void openPastTests(String studentName) {
        PastTests pastTests = new PastTests(studentName);
        pastTests.setVisible(true);
        dispose(); // ��renci Paneli'ni kapat
    }

    // SolveTestPage'i a�an metod
    private void openSolveTestPage(String studentName) {
        SolveTestPage solveTestPage = new SolveTestPage(studentName);
        solveTestPage.setVisible(true);
        dispose(); // ��renci Paneli'ni kapat
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentPage("��renci").setVisible(true);
            }
        });
    }
}

