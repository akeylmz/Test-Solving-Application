package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AddStudentPage extends JFrame {
    private JTextField studentNameField;
    private final String usersFile = "students.txt";

    public AddStudentPage() {
        // Frame özelliklerini ayarla
        setTitle("Öðrenci Ekle");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        // Öðrenci ekleme paneli
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel studentLabel = new JLabel("Öðrenci Adý:");
        studentNameField = new JTextField(15);
        JButton addButton = new JButton("Ekle");

        panel.add(studentLabel);
        panel.add(studentNameField);
        panel.add(addButton);

        // Elemanlarý frame'e ekle
        add(panel);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String studentName = studentNameField.getText();
                addStudentToFile(studentName);
                JOptionPane.showMessageDialog(AddStudentPage.this, "Öðrenci eklendi: " + studentName, "Bilgi", JOptionPane.INFORMATION_MESSAGE);
                studentNameField.setText("");
            }
        });

        // Frame'i görünür yap
        setVisible(true);
    }

    private void addStudentToFile(String studentName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(usersFile, true))) {
            writer.write("," + studentName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

