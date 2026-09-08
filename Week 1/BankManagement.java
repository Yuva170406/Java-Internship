import java.util.Scanner;

public class BankManagement {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        double balance = 0;
        int choice;

        do {
            System.out.println("\n===== BANK MANAGEMENT SYSTEM =====");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter deposit amount: ");
                    double deposit = sc.nextDouble();

                    if (deposit > 0) {
                        balance = balance + deposit;
                        System.out.println("Amount deposited successfully.");
                        System.out.println("Current Balance: " + balance);
                    } else {
                        System.out.println("Invalid deposit amount.");
                    }
                    break;

                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double withdraw = sc.nextDouble();

                    if (withdraw <= 0) {
                        System.out.println("Invalid withdrawal amount.");
                    } 
                    else if (withdraw > balance) {
                        System.out.println("Insufficient balance.");
                    } 
                    else {
                        balance = balance - withdraw;
                        System.out.println("Amount withdrawn successfully.");
                        System.out.println("Current Balance: " + balance);
                    }
                    break;

                case 3:
                    System.out.println("Current Balance: " + balance);
                    break;

                case 4:
                    System.out.println("Thank you for using the Bank Management System.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 4);

        sc.close();
    }
} 
