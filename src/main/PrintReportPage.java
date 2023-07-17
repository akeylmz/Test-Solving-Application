package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PrintReportPage extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel chartPanel;

    public PrintReportPage() {
        initializeUI();
        loadPastTestResults();
        generateReport();
    }

    private static final long serialVersionUID = 1L;
    
    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Raporlama Ekran�");
        setSize(800, 600);
        setLocationRelativeTo(null);

        JScrollPane scrollPane = new JScrollPane();
        table = new JTable();
        scrollPane.setViewportView(table);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        chartPanel = new JPanel();
        getContentPane().add(chartPanel, BorderLayout.EAST);
    }

    private void loadPastTestResults() {
        List<Student> students = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("pastTests.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    String name = parts[0];
                    int correctAnswers = Integer.parseInt(parts[1]);
                    int wrongAnswers = Integer.parseInt(parts[2]);
                    int timeTaken = Integer.parseInt(parts[3]);

                    Student student = new Student(name, correctAnswers, wrongAnswers, timeTaken);
                    students.add(student);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        populateTable(students);
    }

    private void populateTable(List<Student> students) {
        tableModel = new DefaultTableModel();
        tableModel.addColumn("��renci Ad�");
        tableModel.addColumn("Do�ru");
        tableModel.addColumn("Yanl��");
        tableModel.addColumn("S�re");

        for (Student student : students) {
            Object[] rowData = { student.getName(), student.getCorrectAnswers(), student.getWrongAnswers(), student.getTimeTaken() };
            tableModel.addRow(rowData);
        }

        table.setModel(tableModel);
    }

    private void generateReport() {
        // G�ncel raporu report.csv dosyas�na yazd�r
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("report.csv"))) {
            // Ba�l�k sat�r�n� yazd�r
            for (int i = 0; i < tableModel.getColumnCount(); i++) {
                writer.write(tableModel.getColumnName(i));
                if (i < tableModel.getColumnCount() - 1) {
                    writer.write(",");
                }
            }
            writer.newLine();

            // Veri sat�rlar�n� yazd�r
            for (int row = 0; row < tableModel.getRowCount(); row++) {
                for (int column = 0; column < tableModel.getColumnCount(); column++) {
                    writer.write(tableModel.getValueAt(row, column).toString());
                    if (column < tableModel.getColumnCount() - 1) {
                        writer.write(",");
                    }
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Grafik olu�turma i�lemini burada ger�ekle�tirin
        // chartPanel i�ine istedi�iniz grafik t�r�n� yerle�tirin
        // �rne�in: chartPanel.add(new JLabel("Grafik"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PrintReportPage page = new PrintReportPage();
            page.setVisible(true);
        });
    }
}
