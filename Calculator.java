/**
 * Represents a Calculator
 *
 * @author rjayanthi30 (Raghav Raahul Manoharan Jayanthi)
 * @version 1.0
 */
import java.util.Scanner;
public class Calculator {
    /**
     * Creates a main method with required parameter.
     * This method is used to create a calculator to add, subtract, multiply, divide,
     * and alphabetize only two integers, doubles, and strings.
     * In order to help learn course concepts, I worked on the homework with nobody else,
     * discussed homework topics and issues with nobody else, and/or consulted related
     * material which is entitled Java How to Program by Paul Deitel and Harvey Deitel.
     * @param args arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String sentence;
        int tokenLength = 0;
        int index;
        String firstString = "";
        String secondString = "";
        String firstStringLowerCase = "";
        String secondStringLowerCase = "";
        int firstNumber = 0;
        int secondNumber = 0;
        double firstDouble = 0;
        double secondDouble = 0;
        System.out.print("List of operations: add subtract multiply divide alphabetize\nEnter an operation:\n");
        String operation = input.next();
        operation = operation.toLowerCase().trim();
        if (operation.equals("add") || operation.equals("subtract")) {
            System.out.print("Enter two integers:\n");
            if (input.hasNextInt()) {
                firstNumber = input.nextInt();
            } else {
                System.out.println("Invalid input entered. Terminating...");
                return;
            }
            if (input.hasNextInt()) {
                secondNumber = input.nextInt();
            } else {
                System.out.println("Invalid input entered. Terminating...");
                return;
            }
        } else if (operation.equals("multiply") || operation.equals("divide")) {
            System.out.print("Enter two doubles:\n");
            if (input.hasNextDouble()) {
                firstDouble = input.nextDouble();
            } else {
                System.out.println("Invalid input entered. Terminating...");
                return;
            }
            if (input.hasNextDouble()) {
                secondDouble = input.nextDouble();
            } else {
                System.out.println("Invalid input entered. Terminating...");
                return;
            }
        } else if (operation.equals("alphabetize")) {
            System.out.print("Enter two words:\n");
            if (input.hasNext()) {
                firstString = input.next();
            } else {
                System.out.println("Invalid input entered. Terminating...");
                return;
            }
            if (input.hasNext()) {
                secondString = input.next();
            } else {
                System.out.println("Invalid input entered. Terminating...");
                return;
            }
            firstStringLowerCase = firstString.toLowerCase();
            secondStringLowerCase = secondString.toLowerCase();
        } else {
            System.out.println("Invalid input entered. Terminating...");
            return;
        }
        switch (operation) {
        case "add":
            System.out.printf("Answer: %d\n", firstNumber + secondNumber);
            break;
        case "subtract":
            System.out.printf("Answer: %d\n", firstNumber - secondNumber);
            break;
        case "multiply":
            System.out.printf("Answer: %.2f\n", firstDouble * secondDouble);
            break;
        case "divide":
            if (secondDouble != 0) {
                System.out.printf("Answer: %.2f\n", firstDouble / secondDouble);
            } else {
                System.out.println("Invalid input entered. Terminating...");
            }
            break;
        case "alphabetize":
            if (firstStringLowerCase.compareTo(secondStringLowerCase) > 0) {
                System.out.printf("Answer: %s comes before %s alphabetically.\n", secondString, firstString);
            } else if (firstStringLowerCase.compareTo(secondStringLowerCase) < 0) {
                System.out.printf("Answer: %s comes before %s alphabetically.\n", firstString, secondString);
            } else {
                System.out.println("Answer: Chicken or Egg.");
            }
            break;
        default:
            System.out.println("Invalid input entered. Terminating...");
            break;
        }
    }
}
