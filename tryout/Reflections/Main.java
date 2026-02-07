import java.lang.reflect.*;
import java.util.Scanner;

// Bank class
class Banking {

    private double balance;

    public Banking() {
        balance = 0;
    }

    public void deposit(double amount) {
        balance = balance + amount;
        System.out.println("Amount Deposited Successfully!");
    }

    public void withdraw(double amount) {

        if (amount > balance) {
            System.out.println("Insufficient Balance!");
        } 
        else {
            balance = balance - amount;
            System.out.println("Amount Withdrawn Successfully!");
        }
    }

    public void showBalance() {
        System.out.println("Current Balance: " + balance);
    }
}

// Main class
public class Main {

    public static void main(String[] args) {

        try {

            Scanner sc = new Scanner(System.in);

            
            Class<?> c = Class.forName("Banking");

            
            Object obj = c.getDeclaredConstructor().newInstance();

            
            Method depositMethod = c.getMethod("deposit", double.class);
            Method withdrawMethod = c.getMethod("withdraw", double.class);
            Method showMethod = c.getMethod("showBalance");

            int choice;

            do {

                System.out.println("\n====== BANK MENU ======");
                System.out.println("1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. Check Balance");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");

                choice = sc.nextInt();

                switch (choice) {

                    case 1:
                        System.out.print("Enter amount to deposit: ");
                        double dep = sc.nextDouble();

                        depositMethod.invoke(obj, dep);
                        break;

                    case 2:
                        System.out.print("Enter amount to withdraw: ");
                        double wit = sc.nextDouble();

                        withdrawMethod.invoke(obj, wit);
                        break;

                    case 3:
                        showMethod.invoke(obj);
                        break;

                    case 4:
                        System.out.println("Thank you for using Bank System!");
                        break;

                    default:
                        System.out.println("Invalid Choice!");
                }

            } while (choice != 4);

            sc.close();

        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
