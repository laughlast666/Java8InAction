package lambdasinaction.chap5;

import lambdasinaction.chap4.Dish;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static lambdasinaction.chap4.Dish.menu;

public class C5Filtering {

    public static void main(String... args) {

        System.out.println("---- Filtering with predicate ----");
        List<Dish> vegetarianMenu = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(toList());
        vegetarianMenu.forEach(System.out::println);

        System.out.println("---- Skipping elements ----");
        List<Dish> skippedMenu = menu.stream()
                .filter(Dish::isVegetarian)
                .skip(3)
                .collect(toList());
        skippedMenu.forEach(System.out::println);

        System.out.println("---- Truncating elements ----");
        List<Dish> limit3Menu = menu.stream()
                .filter(Dish::isVegetarian)
                .limit(3)
                .collect(toList());
        limit3Menu.forEach(System.out::println);

        System.out.println("---- Choosing unique elements ----");
        List<Integer> uniqueMenu = menu.stream()
                .map(Dish::getCalories)
                .distinct()
                .sorted()
                .collect(toList()); // .forEach here error
        uniqueMenu.forEach(System.out::println);

        System.out.println("---- Choosing unique numbers ----");
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println); //.forEach here okay

    }
}
