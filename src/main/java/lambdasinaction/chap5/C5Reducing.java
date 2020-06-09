package lambdasinaction.chap5;

import lambdasinaction.chap4.Dish;

import java.util.Arrays;
import java.util.List;

import static lambdasinaction.chap4.Dish.menu;

public class C5Reducing {

    public static void main(String... args) {

        List<Integer> numbers = Arrays.asList(1, 3, 5, 7, 9);
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);
        int sum2 = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum2);

        int max = numbers.stream().reduce(0, (a, b) -> Integer.max(a, b));
        System.out.println(max);
        numbers.stream().reduce(Integer::max).ifPresent(System.out::println);
        numbers.stream().reduce(Integer::min).ifPresent(i -> System.out.println("minimal value:" + i));

        int calories = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);
        System.out.println("Number of calories:" + calories);

        System.out.println("Sum of calories:" + menu.stream().mapToInt(Dish::getCalories).sum());
    }
}
