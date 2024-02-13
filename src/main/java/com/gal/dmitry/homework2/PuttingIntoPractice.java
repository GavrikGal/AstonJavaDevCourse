package com.gal.dmitry.homework2;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
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

        System.out.println("1. All transaction in 2011 by value:");
        List<Transaction> transaction2011byValue = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .toList();
        System.out.println(transaction2011byValue);

        System.out.println("\n2. City set:");
        List<String> tradersCities = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .collect(Collectors.toSet()).stream()
                .toList();

        System.out.println(tradersCities);

        System.out.println("\n3. All traders from Cambridge by name:");
        List<Trader> tradersFromCambridge = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> Objects.equals(trader.getCity(), "Cambridge"))
                .collect(Collectors.toSet()).stream()
                .sorted(Comparator.comparing(Trader::getName))
                .toList();
        System.out.println(tradersFromCambridge);

        System.out.println("\n4. String with sorted trader's names:");
        String tradersNames = transactions.stream()
                .map(Transaction::getTrader)
                .collect(Collectors.toSet()).stream()
                .map(Trader::getName)
                .sorted()
                .collect(Collectors.joining(" "));
        System.out.println(tradersNames);

    }
}
