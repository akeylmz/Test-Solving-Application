package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PastTests extends JFrame {
    private JTable table;

    public PastTests(String studentName) {
        initializeUI();
        loadTestResults(studentName);
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Past Tests Results");
        setSize(400, 300);
        setLocationRelativeTo(null);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);

        getContentPane().add(scrollPane);
    }

    private void loadTestResults(String studentName) {
        List<String[]> testResults = readTestResultsFromFile();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Student");
        model.addColumn("True");
        model.addColumn("False");
        model.addColumn("Time");

        for (String[] testData : testResults) {
            String testName = testData[0].trim();
            if (testName.equalsIgnoreCase(studentName)) {
                model.addRow(testData);
            }
        }

        table.setModel(model);
    }

    private List<String[]> readTestResultsFromFile() {
        List<String[]> testResults = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("pastTests.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] testData = line.split("\\|");
                testResults.add(testData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return testResults;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                String studentName = "yusuf";
                new PastTests(studentName).setVisible(true);
            }
        });
    }
}

