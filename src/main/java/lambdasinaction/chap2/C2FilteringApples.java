package lambdasinaction.chap2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class C2FilteringApples {

    public static void main(String... args) {

        List<Apple> inventory = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"),
                new Apple(120, "red"));

        // [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
        List<Apple> greenApples = filterApplesByColor(inventory, "green");
        System.out.println(greenApples);

        // [Apple{color='red', weight=120}]
        List<Apple> redApples = filterApplesByColor(inventory, "red");
        System.out.println(redApples);

        // [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
        List<Apple> greenApples2 = filter(inventory, new AppleColorPredicate());
        // added by ll6
        System.out.println("-- inline4greenapples --");
        List<Apple> greenApples22 = filter(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple a) {
                return "green".equals(a.getColor());
            }
        });
        // added by ll6
        System.out.println("-- lambda4greenapples --");
        List<Apple> greenApples23 = filter(inventory, apple -> "green".equals(apple.getColor()));
        // [Apple{color='green', weight=155}]
        List<Apple> heavyApples = filter(inventory, new AppleWeightPredicate());
        // added by ll6
        System.out.println("-- inline4heavyapples --");
        List<Apple> heavyApples21 = filter(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple a) {
                return a.getWeight() > 150;
            }
        });
        // added by ll6
        System.out.println("-- lambda4heavyapples --");
        List<Apple> heavyApples22 = filter(inventory, apple -> apple.getWeight() > 150);
        // []
        List<Apple> redAndHeavyApples = filter(inventory, new AppleRedAndHeavyPredicate());

        // added by ll6
        System.out.println("-- lambda4redAndheavyapples --");
        List<Apple> heavyApples23 = filter(inventory, apple -> "red".equals(apple.getColor())
                && apple.getWeight() > 150);
        // [Apple{color='red', weight=120}]
        List<Apple> redApples2 = filter(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple a) {
                return "red".equals(a.getColor());
            }
        });

        // added by ll6
        System.out.println("-- lambda4redapples --");
        List<Apple> redApples21 = filter(inventory, apple -> "red".equals(apple.getColor()));
    }

    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApplesByColor(List<Apple> inventory, String color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getColor().equals(color)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > weight) {
                result.add(apple);
            }
        }
        return result;
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

        @Override
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

    static class AppleWeightPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple apple) {
            return apple.getWeight() > 150;
        }
    }

    static class AppleColorPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple apple) {
            return "green".equals(apple.getColor());
        }
    }

    static class AppleRedAndHeavyPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple apple) {
            return "red".equals(apple.getColor())
                    && apple.getWeight() > 150;
        }
    }
}