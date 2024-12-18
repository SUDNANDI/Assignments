package pack.cap.module9.collection;

import java.util.Objects;
import java.util.*;
import java.util.stream.Collectors;

class Account {
    private String accountId;
    private double balance;
    private boolean active;

    public Account(String accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
        this.active = true;
    }

    public String getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        this.active = false;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && this.balance >= amount) {
            this.balance -= amount;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountId.equals(account.accountId); 
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId); 
    }

    @Override
    public String toString() {
        return "Account ID: " + accountId + ", Balance: " + balance + ", Active: " + active;
    }
}


public class BankingSystem {
    private Set<Account> accounts;

    public BankingSystem() {
        this.accounts = new HashSet<>();
    }

    
    public boolean addAccount(Account account) {
        return accounts.add(account);
    }

   
    public boolean deposit(String accountId, double amount) {
        Account account = findAccount(accountId);
        if (account != null) {
            account.deposit(amount);
            return true;
        }
        return false;
    }

   
    public boolean withdraw(String accountId, double amount) {
        Account account = findAccount(accountId);
        if (account != null) {
            account.withdraw(amount);
            return true;
        }
        return false;
    }

    
    public double checkBalance(String accountId) {
        Account account = findAccount(accountId);
        if (account != null) {
            return account.getBalance();
        }
        return -1;
    }

  
    public void removeInactiveAccounts() {
        accounts.removeIf(account -> !account.isActive());
    }

    
    public Optional<Account> findAccountWithHighestBalance() {
        return accounts.stream()
                .max(Comparator.comparingDouble(Account::getBalance));
    }

    
    public Optional<Account> findAccountWithLowestBalance() {
        return accounts.stream()
                .min(Comparator.comparingDouble(Account::getBalance));
    }

  
    private Account findAccount(String accountId) {
        return accounts.stream()
                .filter(account -> account.getAccountId().equals(accountId))
                .findFirst()
                .orElse(null);
    }

    
    public void displayAccounts() {
        accounts.forEach(System.out::println);
    }

    public static void main(String[] args) {
        BankingSystem bankingSystem = new BankingSystem();

    
        bankingSystem.addAccount(new Account("A001", 1000));
        bankingSystem.addAccount(new Account("A002", 1500));
        bankingSystem.addAccount(new Account("A003", 500));
        
        bankingSystem.addAccount(new Account("A004", 0));

   
        bankingSystem.deposit("A001", 500);

    
        bankingSystem.withdraw("A002", 300);

    
        System.out.println("\nBalance of A001: " + bankingSystem.checkBalance("A001"));
        System.out.println("Balance of A002: " + bankingSystem.checkBalance("A002"));
        System.out.println("Balance of A003: " + bankingSystem.checkBalance("A003"));
        System.out.println("Balance of A004: " + bankingSystem.checkBalance("A004"));

        
        Account account = bankingSystem.findAccount("A004");
        if (account != null) {
            account.deactivate();
        }

     
        bankingSystem.removeInactiveAccounts();

     
        bankingSystem.displayAccounts();

      
        bankingSystem.findAccountWithHighestBalance()
                .ifPresent(accountWithHighestBalance -> System.out.println("\nAccount with highest balance: " + accountWithHighestBalance));

     
        bankingSystem.findAccountWithLowestBalance()
                .ifPresent(accountWithLowestBalance -> System.out.println("Account with lowest balance: " + accountWithLowestBalance));
    }
}

