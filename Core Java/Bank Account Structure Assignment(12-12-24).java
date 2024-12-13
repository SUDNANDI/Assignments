package pack.cap.module5.module4;

import java.util.Scanner;

class BankAccount {
    String accountNumber;
    double balance;

    public BankAccount(String accountNumber, double balance) {
        super();
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited successfully: $" + amount + " | New Balance: $" + balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdraw successfully: $" + amount + " | New Balance: $" + balance);
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void checkBalance() {
        System.out.println("Balance for account " + accountNumber + ": $" + balance);
    }
}

// Specialization: SavingsAccount
class SavingsAccount extends BankAccount {
    double intRate;

    public SavingsAccount(String accountNumber, double balance, double intRate) {
        super(accountNumber, balance);
        this.intRate = intRate;
    }

    public void applyInterest() {
        double interest = balance * intRate;
        balance += interest;
        System.out.println("Interest of $ " + interest + " applied. New Balance: $" + balance);
    }
}

// Specialization: CheckingAccount
class CheckingAccount extends BankAccount {
    double fee;

    public CheckingAccount(String accountNumber, double balance, double fee) {
        super(accountNumber, balance);
        this.fee = fee;
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            balance -= fee; // Deduct fee after withdrawal
            System.out.println("Withdraw " + amount + " with a fee of " + fee + " | New Balance: " + balance);
        } else {
            System.out.println("Insufficient balance.");
        }
    }
}

// Specialization: SIP Account (For two months investment)
class SIPAccount extends BankAccount {
    double investmentAmount;
    int months;

    public SIPAccount(String accountNumber, double balance, double investmentAmount, int months) {
        super(accountNumber, balance);
        this.investmentAmount = investmentAmount;
        this.months = months;
    }

    public void invest() {
        for (int i = 1; i <= months; i++) {
            double totalInvestment = investmentAmount;
            balance -= totalInvestment;  // Deduct the SIP investment amount from balance each month
            System.out.println("Month " + i + ": Invested $ " + totalInvestment + " in SIP. New Balance: $" + balance);
        }
    }

    public void checkSIPStatus() {
        System.out.println("SIP Status for account " + accountNumber + ": Total investment of $ " + (investmentAmount * months));
    }
}

// Specialization: Trading Account
class TradingAccount extends BankAccount {
    int numberOfTrades;
    double tradeFee;

    public TradingAccount(String accountNumber, double balance, double tradeFee) {
        super(accountNumber, balance);
        this.tradeFee = tradeFee;
        this.numberOfTrades = 0;
    }

    public void trade(double tradeAmount) {
        if (balance >= tradeAmount + tradeFee) {
            balance -= (tradeAmount + tradeFee);
            numberOfTrades++;
            System.out.println("Trade executed for $ " + tradeAmount + " with a fee of $ " + tradeFee + ". New Balance: " + balance);
        } else {
            System.out.println("Insufficient balance for trade.");
        }
    }

    public void checkTrades() {
        System.out.println("Total number of trades executed: " + numberOfTrades);
    }
}

// Specialization: Business Account
class BusinessAccount extends BankAccount {
    double overdraftLimit;

    public BusinessAccount(String accountNumber, double balance, double overdraftLimit) {
        super(accountNumber, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= (balance + overdraftLimit)) {
            balance -= amount;
            System.out.println("Withdraw successfully: $" + amount + " | New Balance: $" + balance + " (Overdraft used)");
        } else {
            System.out.println("Insufficient balance and overdraft limit for withdrawal.");
        }
    }

    public void checkOverdraftLimit() {
        System.out.println("Overdraft limit for account " + accountNumber + ": $" + overdraftLimit);
    }
}

public class Gen_Spe_Demo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

      
        System.out.print("Enter account number for SIP Account: ");
        String accountNumber = scanner.next();
        System.out.print("Enter initial balance for SIP Account: $");
        double balance = scanner.nextDouble();

        System.out.print("Enter monthly investment amount for SIP: $");
        double investmentAmount = scanner.nextDouble();
        int months = 2; 

        SIPAccount sipAccount = new SIPAccount(accountNumber, balance, investmentAmount, months);
        
      
        System.out.println("\nSIP for two months:");
        sipAccount.invest(); 
        sipAccount.checkSIPStatus(); 

      
        SavingsAccount sa = new SavingsAccount("SA123", 5000, 0.03);
        sa.deposit(10000);
        sa.applyInterest();

        CheckingAccount ca = new CheckingAccount("CA456", 4000, 2.5);
        ca.deposit(1000);
        ca.withdraw(200);

        BusinessAccount ba = new BusinessAccount("BA202", 5000, 10000);
        ba.deposit(5000);
        ba.withdraw(60000);

        TradingAccount ta = new TradingAccount("TA101", 15000, 20);
        ta.deposit(5000);
        ta.trade(2000);

        scanner.close();
    }
}
