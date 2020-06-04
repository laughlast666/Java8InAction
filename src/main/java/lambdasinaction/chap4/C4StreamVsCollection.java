package lambdasinaction.chap4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


public class C4StreamVsCollection {

    public static void main(String... args) {
        List<String> names = Arrays.asList("Java8", "Lambdas", "In", "Action");
        Stream<String> s = names.stream();
        s.forEach(System.out::println);
        // s must be assigned again because streams can be consumed only once
        s = names.stream();
        s.forEach(System.out::println);
    }
}