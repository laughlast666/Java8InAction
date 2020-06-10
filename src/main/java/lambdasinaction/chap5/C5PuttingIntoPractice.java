package lambdasinaction.chap5;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.toList;

public class C5PuttingIntoPractice {
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

        // Query 1: Find all transactions from year 2011 and sort them by value (small to high).
        List<Transaction> transactions2011 = transactions.stream().filter(t -> 2011 == t.getYear())
                .sorted(comparing(Transaction::getValue)).collect(toList());
        transactions2011.forEach(System.out::println);

        // Query 2: What are all the unique cities where the traders work?
        List<String> cities = transactions.stream().map(t -> t.getTrader().getCity()).distinct().collect(toList());
        System.out.println("unique cities of traders:\n" + cities);

        // Query 3: Find all traders from Cambridge and sort them by name.
        List<Trader> traders = transactions.stream().map(Transaction::getTrader)
                .filter(t -> "Cambridge".equals(t.getCity()))
                .sorted(comparing(Trader::getName)).collect(toList());
        System.out.println("Traders from Cambbridges:\n" + traders);

        // Query 4: Return a string of all traders’ names sorted alphabetically.
        String traderStr = transactions.stream().map(t -> t.getTrader().getName() + " ")
                .sorted().distinct().reduce("", (n1, n2) -> n1 + n2);
        System.out.println("All unique traders'name in one line:\n" + traderStr);
        System.out.println("All unique traders'name joining in one line:\n"
                + transactions.stream().map(t -> t.getTrader().getName())
                .sorted().distinct().collect(Collectors.joining(", ")));
        System.out.println("All traders'name reducing in one line:\n"
                + transactions.stream()
                .collect(reducing("", t -> t.getTrader().getName() + " ", (n1, n2) -> n1 + n2)));

        // Query 5: Are there any trader based in Milan?
        boolean milanBased =
                transactions.stream().anyMatch(t -> "Milan".equals(t.getTrader().getCity()));
        System.out.println("Some Trader In Milan ?\n" + milanBased);


        // Query 6: Update all transactions so that the traders from Milan are set to Cambridge.
        transactions.stream().map(Transaction::getTrader).filter(trader -> "Milan".equals(trader.getCity()))
                .forEach(trader -> trader.setCity("Cambridge"));
        System.out.println(transactions);

        // Query 7: What's the highest value in all the transactions?
        int highestValue =
                transactions.stream()
                        .map(Transaction::getValue)
                        .reduce(0, Integer::max);
        System.out.println(highestValue);
        System.out.println("Hightest value :"
                + transactions.stream().mapToInt(Transaction::getValue).max().getAsInt());
    }
}