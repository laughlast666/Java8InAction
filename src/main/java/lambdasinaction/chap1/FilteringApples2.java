/*
 *  www.laughlast666.org Inc.
 * Copyright (c) 2020-2020 All rights reserved.
 */

package lambdasinaction.chap1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class FilteringApples2 {

    public static void main(String... args) {

        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));
        //
        List<Apple> greenApples1 = filterApples(inventory, new Predicate<Apple>() {
            @Override
            public boolean test(Apple apple) {
                return isGreenApple(apple);
            }
        });
        //
        List<Apple> greenApples2 = filterApples(inventory, (Apple apple) -> isGreenApple(apple));
        //
        List<Apple> greenApples3 = filterApples(inventory, (apple -> isGreenApple(apple)));
        //
        List<Apple> greenApples4 = filterApples(inventory, FilteringApples2::isGreenApple);
        //
        FilteringApples2 filteringApples2 = new FilteringApples2();
        List<Apple> greenApples5 = filterApples(inventory, filteringApples2::isGreenApple4Demo);
        //
        List<Apple> inventory1 = new ArrayList<>(inventory);
        inventory1.add(new Apple(166, "green"));
        List<Apple> greenHeavyApples = filterApples(inventory1, new Predicate<Apple>() {
            @Override
            public boolean test(Apple apple) {
                return isGreenApple(apple) && isHeavyApple(apple);
            }
        });
        //
        List<Apple> greenHeavyApples1 = filterApples(inventory1, apple -> isGreenApple(apple) && isHeavyApple(apple));

    }

    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (isGreenApple(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterHeavyApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (isHeavyApple(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public boolean isGreenApple4Demo(Apple apple) {
        return "green".equals(apple.getColor());
    }

    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
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

}
