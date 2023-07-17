package main;

public class Student {
    private String name;
    private int correctAnswers;
    private int wrongAnswers;
    private int timeTaken;

    public Student(String name, int correctAnswers, int wrongAnswers, int timeTaken) {
        this.name = name;
        this.correctAnswers = correctAnswers;
        this.wrongAnswers = wrongAnswers;
        this.timeTaken = timeTaken;
    }

    public String getName() {
        return name;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int getWrongAnswers() {
        return wrongAnswers;
    }

    public int getTimeTaken() {
        return timeTaken;
    }
}

