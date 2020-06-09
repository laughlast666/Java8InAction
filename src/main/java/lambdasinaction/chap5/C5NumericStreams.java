package lambdasinaction.chap5;

import lambdasinaction.chap4.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static lambdasinaction.chap4.Dish.menu;

public class C5NumericStreams {

    public static void main(String... args) {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.forEach(System.out::println);
        IntStream.rangeClosed(1, 5).forEach(System.out::println);
        Arrays.stream(numbers.toArray()).forEach(System.out::println);
        int calories = menu.stream()
                .mapToInt(Dish::getCalories)
                .reduce(0, Integer::sum);
        System.out.println("Number of calories:" + calories);


        // max and OptionalInt
        OptionalInt maxCalories = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();

        menu.stream().mapToInt(Dish::getCalories).max()
                .ifPresentOrElse(m -> System.out.println("max found :" + m), () -> System.out.println(1));

        // numeric ranges
        IntStream evenNumbers = IntStream.rangeClosed(1, 100).filter(n -> n % 2 == 0);
        IntStream evenNumbers1 = IntStream.range(1, 100).filter(n -> n % 2 == 0);

        System.out.println("Even Numbers #: in rangeClosed(1,100) " + evenNumbers.count() + ", " +
                "and in range(1,100) " + evenNumbers1.count());

        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 20).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 20).boxed()
                        .filter(b -> C5NumericStreams.isPerfectSquare(a * a + b * b))
                        .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}));

        pythagoreanTriples.forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

    }

    public static boolean isPerfectSquare(int n) {
        return Math.sqrt(n) % 1 == 0;
    }

}
