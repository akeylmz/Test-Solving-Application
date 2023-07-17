package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MakeTestPage extends JFrame {
    private JTextField aField;
    private JTextField bField;
    private JTextField nField;
    private JTextField tField;

    public MakeTestPage() {
        initializeUI();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Test Oluþtur");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));

        JLabel aLabel = new JLabel("a Deðeri:");
        aField = new JTextField();
        JLabel bLabel = new JLabel("b Deðeri:");
        bField = new JTextField();
        JLabel nLabel = new JLabel("Soru Sayýsý (N):");
        nField = new JTextField();
        JLabel tLabel = new JLabel("Süre (saniye):");
        tField = new JTextField();

        inputPanel.add(aLabel);
        inputPanel.add(aField);
        inputPanel.add(bLabel);
        inputPanel.add(bField);
        inputPanel.add(nLabel);
        inputPanel.add(nField);
        inputPanel.add(tLabel);
        inputPanel.add(tField);

        JButton generateButton = new JButton("Test Oluþtur");

        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generateTest();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(generateButton);

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void generateTest() {
        int a = Integer.parseInt(aField.getText());
        int b = Integer.parseInt(bField.getText());
        int n = Integer.parseInt(nField.getText());
        int t = Integer.parseInt(tField.getText());

        List<String> questions = new ArrayList<>();
        List<String> answers = new ArrayList<>();
        Random random = new Random();

        while (questions.size() < n) {
            int x = random.nextInt(a) + 1;
            int y = random.nextInt(b) + 1;
            String question = x + "*" + y;
            String answer = String.valueOf(x * y);
            questions.add(question);
            answers.add(answer);
        }

        StringBuilder testContent = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String question = questions.get(i);
            String answer = answers.get(i);
            testContent.append(question).append("|").append(answer).append("\n");
        }

        testContent.append(t);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("tests.txt", false))) {
            writer.write(testContent.toString());
            writer.newLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        JOptionPane.showMessageDialog(this, "Test oluþturuldu ve dosyaya kaydedildi.");

        dispose();
    }




    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MakeTestPage().setVisible(true);
            }
        });
    }
}

