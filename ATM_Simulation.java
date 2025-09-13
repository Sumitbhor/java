import java.util.Scanner;

class ATM {
    private double balance;

    public ATM(double initialBalance) {
        this.balance = initialBalance;
    }

    // Method to check balance
    public void checkBalance() {
        System.out.println("Current Balance: ₹" + balance);
    }

    // Method to deposit money
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than 0.");
        }
        balance += amount;
        System.out.println("₹" + amount + " deposited successfully.");
    }

    // Method to withdraw money
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than 0.");
        }
        else if (amount > balance) {
            throw new ArithmeticException("Insufficient funds! Cannot withdraw ₹" + amount);
        }
       else { balance -= amount;
        System.out.println("₹" + amount + " withdrawn successfully.");
       }
    }
}

public class ATM_Simulation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ATM atm = new ATM(5000.0);  // Initial balance

        while (true) {
            try {
                System.out.println("\n=== ATM MENU ===");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit Money");
                System.out.println("3. Withdraw Money");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        atm.checkBalance();
                        break;

                    case 2:
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = sc.nextDouble();
                        atm.deposit(depositAmount);
                        break;

                    case 3:
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawAmount = sc.nextDouble();
                        atm.withdraw(withdrawAmount);
                        break;

                    case 4:
                        System.out.println("Thank you for using ATM. Goodbye!");
                        sc.close();
                        return;

                    default:
                        throw new IllegalArgumentException("Invalid menu choice. Please enter 1-4.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Hi");
                System.out.println("Error: " + e.getMessage());
            } catch (ArithmeticException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected Error: " + e.getMessage());
            } finally {
                System.out.println("Operation completed. Returning to main menu...\n");
            }
        }
    }
}