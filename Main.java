package Bank;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CheckingAccount acc = new CheckingAccount("Alice", "Brown", 555, 2.5);

        acc.deposit(100);
        acc.processWithdrawal(150); // Triggers overdraft
        acc.displayAccount();

        // Using dynamic set/get
        acc.set("interestrate", 3.0);
        System.out.println("Updated Interest Rate: " + acc.get("interestrate"));

        // Invalid usage
        acc.set("accountid", "wrong type"); // Should trigger error
        System.out.println("Attempting to get unknown field: " + acc.get("foo")); // Should show error
    }
}
