package com.gal.dmitry.homework2;

import java.util.*;
import java.util.stream.Collectors;

public class PuttingIntoPractice {

    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        List<Transaction> transaction2011byValue = task1(transactions);
        printResult(1, "All transaction in 2011 by value",
                transaction2011byValue);

        List<String> tradersCities = task2(transactions);
        printResult(2, "City set",
                tradersCities);

        List<Trader> tradersFromCambridge = task3(transactions);
        printResult(3, "All traders from Cambridge by name",
                tradersFromCambridge);

        String tradersNames = task4(transactions);
        printResult(4, "String with sorted trader's names:",
                tradersNames);

        boolean hasTraderFromMilan = task5(transactions);
        printResult(5, "Has any trader from Milan",
                hasTraderFromMilan);

        int sumTransactionsCambridgeTraders = task6(transactions);
        printResult(6, "Sum of transactions by traders from Cambridge",
                sumTransactionsCambridgeTraders);

        int maxSumOfTransaction = task7(transactions);
        printResult(7, "Max sum of transactin is",
                maxSumOfTransaction);

        Transaction minSumTransaction = task8(transactions);
        printResult(8, "Transaction with min sum",
                minSumTransaction);

    }

    public static List<Transaction> task1(Collection<Transaction> transactions) {
        return transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .toList();
    }

    public static List<String> task2(Collection<Transaction> transactions) {
        return transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .toList();
    }

    public static List<Trader> task3(Collection<Transaction> transactions) {
        return transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> Objects.equals(trader.getCity(), "Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .toList();
    }

    public static String task4(Collection<Transaction> transactions) {
        return transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .map(Trader::getName)
                .sorted()
                .collect(Collectors.joining(" "));
    }

    public static boolean task5(Collection<Transaction> transactions) {
        return transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .anyMatch(trader -> trader.getCity().equals("Milan"));
    }

    public static int task6(Collection<Transaction> transactions) {
        return transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .mapToInt(Transaction::getValue)
                .sum();
    }

    public static int task7(Collection<Transaction> transactions) {
        return transactions.stream()
                .mapToInt(Transaction::getValue)
                .max().orElse(0);
    }

    public static Transaction task8(Collection<Transaction> transactions) {
        return transactions.stream()
                .min(Comparator.comparingInt(Transaction::getValue)).orElseThrow();
    }

    public static void printResult(int taskNum, String taskName,
                                   Object result) {
        System.out.println(taskNum + ". " + taskName + ":");
        System.out.println(result);
        System.out.println();
    }
}
