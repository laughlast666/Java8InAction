package lambdasinaction.chap5;

import lambdasinaction.chap4.Dish;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static lambdasinaction.chap4.Dish.menu;

public class C5Mapping {

    public static void main(String... args) {

        // map
        List<String> dishNames = menu.stream().map(Dish::getName).collect(toList());
        System.out.println(dishNames);
        List<Integer> dishCalorial = menu.stream().map(Dish::getCalories).collect(toList());
        dishCalorial.forEach(System.out::println);

        // map
        List<String> words = Arrays.asList("Hi", "World!");
        List<Integer> wordLengths = words.stream().map(String::length).collect(toList());
        System.out.println(wordLengths);
        List<String> upperCaseWords = words.stream().map(String::toUpperCase).collect(toList());
        upperCaseWords.forEach(System.out::println);

        // flatMap
        words.stream().flatMap(line -> Arrays.stream(line.split("")))
                .distinct().forEach(System.out::println);

        // flatMap
        List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> numbers2 = Arrays.asList(6, 7, 8);
        List<int[]> pairs = numbers1.stream()
                .flatMap(i -> numbers2.stream().map(j -> new int[]{i, j}))
                .filter(pair -> (pair[0] + pair[1]) % 6 == 0).collect(toList());
        pairs.forEach(pair -> System.out.println("(" + pair[0] + ", " + pair[1] + ")"));
    }
}
