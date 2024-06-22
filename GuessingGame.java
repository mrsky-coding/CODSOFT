import java.util.Random;
import java.util.Scanner;

public class GuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int maxAttempts = 10;
        boolean playAgain = true;
        int totalScore = 0;

        while (playAgain) {
            int numberGuess = random.nextInt(100) + 1;
            int attempt = 0;
            boolean guessCorrectly = false;
            System.out.println("Welcome to the Guessing Game!");
            System.out.println("I have generated a number between 1 and 100. Try to guess it!");

            while (attempt < maxAttempts && !guessCorrectly) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempt++;

                if (userGuess < numberGuess) {
                    System.out.println("Too low! Try again.");
                } else if (userGuess > numberGuess) {
                    System.out.println("Too high! Try again.");
                } else {
                    System.out.println("Congratulations! You've guessed the correct number.");
                    guessCorrectly = true;
                    totalScore += (maxAttempts - attempt + 1); 
                }
            }

            if (!guessCorrectly) {
                System.out.println("You've used all your attempt. The correct number was " + numberGuess);
            }

            System.out.println("Your current score is: " + totalScore);
            System.out.print("Do you want to play another round? (yes/no): ");
            scanner.nextLine(); 
            String response = scanner.nextLine();
            playAgain = response.equalsIgnoreCase("yes");
        }

        System.out.println("Thank you for playing! Your final score is: " + totalScore);
        scanner.close();
    }
}
