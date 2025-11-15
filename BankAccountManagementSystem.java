import java.util.ArrayList;
import java.util.Scanner;

class BankAccount{
    private String accountNumber;
    private String holderName;
    private double balance;

    public BankAccount(String accountNumber, String holderName, double balance){
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    public String getAccountNumber(){
        return accountNumber;
    }

    public String getHolderName(){
        return holderName;
    }

    public double getBalance(){
        return balance;
    }

    public void deposit(double amount){
        balance += amount;
    }

    public boolean withdraw(double amount){
        if (amount >= 0 && amount <= balance){
            balance -= amount;
            return true;
        }
        return false;
    }

    public String toString(){
        return "Account No: " + accountNumber +
               " | Holder: " + holderName +
               " | Balance: Rs. " + balance;
    }
}

public class BankAccountManagementSystem{
    private static ArrayList<BankAccount> accounts = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;
        do {
            System.out.println("\n===== Bank Account Management System =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Amount");
            System.out.println("3. Withdraw Amount");
            System.out.println("4. View Balance");
            System.out.println("5. View All Accounts");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // clear Memory

            switch(choice) {
                case 1: createAccount(); break;
                case 2: depositAmount(); break;
                case 3: withdrawAmount(); break;
                case 4: viewBalance(); break;
                case 5: showAllAccounts(); break;
                case 6: System.out.println("Exiting... Thank you!"); break;
                default: System.out.println("Invalid choice! Please try again.");
            }
        } while(choice != 6);

    }

    private static BankAccount findAccount(String accNo){
        for (BankAccount acc : accounts){
            if (acc.getAccountNumber().equals(accNo)){
                return acc;
            }
        }
        return null;
    }

    private static void createAccount() {

        System.out.print("Enter Account Number: ");
        String accNo = sc.nextLine().trim();

        if(accNo.isEmpty()){
            System.out.println("Account Number cannot be empty!");
            return;
        }

        // Check if already exists
        if(findAccount(accNo) != null){
            System.out.println("Account with this number already exists!");
            return;
        }

        System.out.print("Enter Account Holder Name: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()){
            System.out.println("Name cannot be empty!");
            return;
        }

        System.out.print("Enter Initial Deposit Amount: ");
        double amount = sc.nextDouble();

        if (amount < 0){
            System.out.println("Initial amount cannot be negative!");
            return;
        }

        BankAccount acc = new BankAccount(accNo, name, amount);
        accounts.add(acc);

        System.out.println("Account Created Successfully!");
    }

    private static void depositAmount(){
        System.out.print("Enter Account Number: ");
        String accNo = sc.nextLine();

        BankAccount acc = findAccount(accNo);
        if (acc == null) {
            System.out.println("Account not found!");
            return;
        }

        System.out.print("Enter Deposit Amount: ");
        double amount = sc.nextDouble();

        if (amount <= 0){
            System.out.println("Amount should be greater than 0!");
            return;
        }

        acc.deposit(amount);
        System.out.println("Deposit Successful! New Balance: Rs. " + acc.getBalance());
    }

    private static void withdrawAmount(){
        System.out.print("Enter Account Number: ");
        String accNo = sc.nextLine();

        BankAccount acc = findAccount(accNo);
        if (acc == null){
            System.out.println("Account not found!");
            return;
        }

        System.out.print("Enter Withdraw Amount: ");
        double amount = sc.nextDouble();

        if (amount <= 0){
            System.out.println("Amount should be greater than 0!");
            return;
        }

        if (acc.withdraw(amount)){
            System.out.println("Withdrawal Successful! New Balance: Rs. " + acc.getBalance());
        }
        else{
            System.out.println("Insufficient Balance!");
        }
    }

    private static void viewBalance(){
        System.out.print("Enter Account Number: ");
        String accNo = sc.nextLine();

        BankAccount acc = findAccount(accNo);
        if (acc != null){
            System.out.println("\n" + acc.toString());
        } else{
            System.out.println("Account not found!");
        }
    }

    private static void showAllAccounts(){
        if (accounts.isEmpty()){
            System.out.println("No accounts available!");
            return;
        }

        System.out.println("\n===== All Accounts =====");
        for (BankAccount acc : accounts){
            System.out.println(acc);
        }
    }
}
