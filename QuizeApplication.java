
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizeApplication{
    public static void main(String[] args) {
        Question q1 = new Question(
            "What is the capital of France?",
            new String[]{"Berlin", "Madrid", "Paris", "Rome"},
            2
        );

        Question q2 = new Question(
            "Which planet is known as the Red Planet?",
            new String[]{"Earth", "Mars", "Jupiter", "Saturn"},
            1
        );

        Question q3 = new Question(
            "What is the largest ocean on Earth?",
            new String[]{"Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean"},
            3
        );

        List<Question> questions = Arrays.asList(q1, q2, q3);
        Quiz quiz = new Quiz(questions);
        quiz.start();
    }
}




class Question {
    private String question;
    private String[] options;
    private int correctAnswerIndex;

    public Question(String question, String[] options, int correctAnswerIndex) {
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public boolean isCorrect(int userAnswerIndex) {
        return userAnswerIndex == correctAnswerIndex;
    }
}




class Quiz {
    private List<Question> questions;
    private int score;
    private int currentQuestionIndex;
    private Scanner scanner;

    public Quiz(List<Question> questions) {
        this.questions = questions;
        this.score = 0;
        this.currentQuestionIndex = 0;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to the Quiz!");

        for (currentQuestionIndex = 0; currentQuestionIndex < questions.size(); currentQuestionIndex++) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            System.out.println("\nQuestion " + (currentQuestionIndex + 1) + ": " + currentQuestion.getQuestion());
            String[] options = currentQuestion.getOptions();

            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }

            int userAnswer = getUserAnswer();

            if (currentQuestion.isCorrect(userAnswer - 1)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer was " + (currentQuestion.getCorrectAnswerIndex() + 1) + ". " + options[currentQuestion.getCorrectAnswerIndex()]);
            }
        }

        displayResult();
    }

    private int getUserAnswer() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime's up! Moving to the next question.");
                System.exit(0);
            }
        };

        timer.schedule(task, 15000); // 15 seconds timer

        System.out.print("Your answer (1-4): ");
        int answer = scanner.nextInt();
        timer.cancel();

        return answer;
    }

    private void displayResult() {
        System.out.println("\nQuiz Over!");
        System.out.println("Your final score is: " + score + " out of " + questions.size());
    }
}
