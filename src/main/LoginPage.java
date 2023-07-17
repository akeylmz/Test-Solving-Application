package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginPage extends JFrame {
    private JTextField adminNameField;
    private JTextField studentNameField;
    private final String usersFile = "students.txt";

    public LoginPage() {
        // Frame �zelliklerini ayarla
        setTitle("Giri� Sayfas�");
        setSize(501, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new FlowLayout());

        // Admin giri�i
        JPanel adminPanel = new JPanel();
        adminPanel.setLayout(new FlowLayout());

        JLabel adminLabel = new JLabel("Admin Ad�:");
        adminNameField = new JTextField(20);
        JButton adminButton = new JButton("Admin Paneli");

        adminPanel.add(adminLabel);
        adminPanel.add(adminNameField);
        adminPanel.add(adminButton);

        // ��renci giri�i
        JPanel studentPanel = new JPanel();
        studentPanel.setLayout(new FlowLayout());

        JLabel studentLabel = new JLabel("��renci Ad�:");
        studentNameField = new JTextField(20);
        JButton studentButton = new JButton("��renci Paneli");

        studentPanel.add(studentLabel);
        studentPanel.add(studentNameField);
        studentPanel.add(studentButton);

        // Elemanlar� frame'e ekle
        getContentPane().add(adminPanel);
        getContentPane().add(studentPanel);

        adminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String adminName = adminNameField.getText();
                if (isAdmin(adminName)) {
                    openAdminPanel(adminName);
                } else {
                    JOptionPane.showMessageDialog(LoginPage.this, "Bu kullan�c� kay�tl� de�il.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        studentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String studentName = studentNameField.getText();
                if (isStudent(studentName)) {
                    openStudentPanel(studentName);
                } else {
                    JOptionPane.showMessageDialog(LoginPage.this, "Bu kullan�c� kay�tl� de�il.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Frame'i g�r�n�r yap
        setVisible(true);
    }

    private boolean isAdmin(String adminName) {
        if (adminName.equals(readFirstLine(usersFile))) {
            return true;
        }
        return false;
    }

    private boolean isStudent(String studentName) {
        String students = readSecondLine(usersFile);
        if (students.contains(studentName)) {
            return true;
        }
        return false;
    }

    private String readFirstLine(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String readSecondLine(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            reader.readLine(); // Skip the first line
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void openAdminPanel(String adminName) {
        AdminPage adminPage = new AdminPage(adminName);
        adminPage.setVisible(true);
        dispose();
    }

    private void openStudentPanel(String studentName) {
        StudentPage studentPage = new StudentPage(studentName);
        studentPage.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginPage();
            }
        });
    }
}
