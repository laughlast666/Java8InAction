package lambdasinaction.chap3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class C3Lambdas {
    public static void main(String... args) {

        //
        Runnable r = () -> {
            System.out.println("Hi,Lambda!");
        };
        Runnable r2 = () -> System.out.println("Hi,Lambda again!");
        r.run();
        r2.run();
        //
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(166, "green"),
                new Apple(120, "red"));
        List<Apple> redApples = filter(inventory, (Apple a) -> "red".equals(a.getColor()));
        List<Apple> greenApples = filter(inventory, apple -> "green".equals(apple.getColor()));
        List<Apple> heavyApples = filter(inventory, apple -> apple.getWeight() > 150);
        List<Apple> greenAndHeavyApples = filter(inventory, (Apple a) -> "green".equals(a.getColor())
                && a.getWeight() > 150);
        //
        Comparator<Apple> cmpByWeight = (a1, a2) -> a1.getWeight().compareTo(a2.getWeight());
        inventory.sort(cmpByWeight);
        System.out.println(inventory);


    }

    public static List<Apple> filter(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        System.out.println(result);
        return result;
    }

    public static class Apple {
        private int weight = 0;
        private String color = "";

        public Apple(int weight, String color) {
            this.weight = weight;
            this.color = color;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String toString() {
            return "Apple{" +
                    "color='" + color + '\'' +
                    ", weight=" + weight +
                    '}';
        }
    }

    interface ApplePredicate {
        public boolean test(Apple a);
    }
}