package simple;

/**
 * Recursive method to sum numbers input by the user. Scanner for user input, 
 * count to track number of inputs in recursion, 
 * sum to provide the currently calculated sum
 * should return the total sum at the end of recursion when all 5 numbers have been added during each step. 
 * step-by-step to keep the user involved in the process (also to reduce errors in input data if numbers a delineated or not)
 */
import java.util.Scanner;

public class RecursiveSum {


    public static int sumNumbers(Scanner scanner, int count, int sum) {
        // Base case is that 5 numbers were entered. 
        if (count == 5) {
            return sum;
        }

        System.out.print("Enter number " + (count + 1) + ": ");
        //input validation (Correct)
        if (scanner.hasNextInt()) {
            int number = scanner.nextInt();
            int newSum = sum + number;
            System.out.println("Running Sum after " + (count + 1) + " number(s): " + newSum);
            return sumNumbers(scanner, count + 1, newSum);
        } else {
            // Input validation (Incorrect) 
            String invalidInput = scanner.next(); 
            System.out.println("Invalid input '" + invalidInput + "'. Please enter a valid integer.");
            return sumNumbers(scanner, count, sum); 
        }
    }
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.println("Welcome! Please enter 5 numbers one by one.");

        int totalSum = sumNumbers(scn, 0, 0);
        System.out.println("Final Sum of all 5 numbers: " + totalSum);
        
        scn.close();
    }
}