package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPage extends JFrame {
    private JLabel adminNameLabel;

    public AdminPage(String adminName) {
        initializeUI(adminName);
    }

    private void initializeUI(String adminName) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Admin Page");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        adminNameLabel = new JLabel("Welcome " + adminName);
        adminNameLabel.setHorizontalAlignment(JLabel.CENTER);

        JButton createTestButton = new JButton("Create Test");
        JButton addStudentButton = new JButton("Add Student");
        JButton viewReportButton = new JButton("Create Report");

        createTestButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openMakeTestPage();
            }
        });

        addStudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAddStudentPage();
            }
        });

        viewReportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openPrintReportPage();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(createTestButton);
        buttonPanel.add(addStudentButton);
        buttonPanel.add(viewReportButton);

        add(adminNameLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    private void openMakeTestPage() {
        MakeTestPage makeTestPage = new MakeTestPage();
        makeTestPage.setVisible(true);
        dispose();
    }

    private void openAddStudentPage() {
        AddStudentPage addStudentPage = new AddStudentPage();
        addStudentPage.setVisible(true);
        dispose();
    }

    private void openPrintReportPage() {
        PrintReportPage printReportPage = new PrintReportPage();
        printReportPage.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AdminPage("Admin");
            }
        });
    }
}

