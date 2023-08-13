package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SolveTestPage extends JFrame {
    private JLabel studentNameLabel;
    private String studentName;
    private JLabel testLabel;
    private JLabel timeLabel;
    private List<String> questions;
    private List<String> answers;
    private int timeLimit;
    private int currentQuestionIndex;
    private int correctCount;
    private int wrongCount;
    private Timer timer;

    public SolveTestPage(String studentName) {
        this.studentName = studentName;
        initializeUI();
        loadTestData();
        displayNextQuestion();
        startTimer();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Test Solving Page");
        setSize(400, 300);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        studentNameLabel = new JLabel("Student Name: " + studentName);
        studentNameLabel.setHorizontalAlignment(JLabel.CENTER);

        getContentPane().add(studentNameLabel, BorderLayout.NORTH);

        testLabel = new JLabel();
        testLabel.setBounds(10, 50, 380, 150);
        testLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));

        timeLabel = new JLabel();
        timeLabel.setBounds(150, 10, 100, 30);

        JButton nextButton = new JButton("Answer");
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                displayNextQuestion();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBounds(0, 232, 386, 31);
        buttonPanel.add(nextButton);
        getContentPane().setLayout(null);

        getContentPane().add(testLabel, BorderLayout.CENTER);
        getContentPane().add(timeLabel);
        getContentPane().add(buttonPanel);
    }

    private void loadTestData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("tests.txt"))) {
            String line;
            List<String> lines = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            String timeLine = lines.remove(lines.size() - 1);
            timeLine = timeLine.trim();
            timeLimit = Integer.parseInt(timeLine);

            questions = new ArrayList<>();
            answers = new ArrayList<>();
            for (String questionLine : lines) {
                String[] parts = questionLine.split("\\|");
                String question = parts[0].trim();
                String answer = parts[1].trim();
                questions.add(question);
                answers.add(answer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Invalid data format. Check the file.");
            System.exit(0);
        }
    }

    private void displayNextQuestion() {
        if (currentQuestionIndex < questions.size()) {
            String question = questions.get(currentQuestionIndex);
            testLabel.setText(question);
            timeLabel.setText("Remaining Time: " + timeLimit + " minute");
            currentQuestionIndex++;
        } else {
            endTest();
        }
    }

    private void checkAnswer() {
        String userAnswer = JOptionPane.showInputDialog(this, "Enter the Answer:");

        if (userAnswer != null && !userAnswer.isEmpty()) {
            String correctAnswer = answers.get(currentQuestionIndex - 1);
            if (userAnswer.equals(correctAnswer)) {
                correctCount++;
            } else {
                wrongCount++;
            }
        }
    }

    private long totalTimeElapsed;

    private void startTimer() {
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timeLimit--;
                totalTimeElapsed++;
                timeLabel.setText("Kalan Süre: " + timeLimit + " sn");
                if (timeLimit <= 0) {
                    timer.stop();
                    endTest();
                }
            }
        });
        timer.start();
    }

    private void endTest() {
        timer.stop();
        String message = "Test tamamlandý!\n\n";
        message += "Doðru Sayýsý: " + correctCount + "\n";
        message += "Yanlýþ Sayýsý: " + wrongCount + "\n";
        message += "Geçen Süre: " + totalTimeElapsed + " saniye";
        JOptionPane.showMessageDialog(this, message);

        // Geçmiþ test sonuçlarýný dosyaya yazma
        writeTestResultToFile(studentName, correctCount, wrongCount, totalTimeElapsed);

        System.exit(0);
    }

    private void writeTestResultToFile(String studentName, int correctCount, int wrongCount, long totalTimeElapsed) {
        String fileName = "pastTests.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formattedDateTime = now.format(formatter);

            String resultLine = studentName + "|" + correctCount + "|" + wrongCount + "|" + totalTimeElapsed;

            writer.newLine();
            writer.write(resultLine);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SolveTestPage("Öðrenci").setVisible(true);
            }
        });
    }
}

