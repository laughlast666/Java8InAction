package lambdasinaction.chap3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class C3Sorting {

    public static void main(String... args) {

        // 1
        List<Apple> inventory = new ArrayList<>();
        inventory.addAll(Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red")));
        //added by ll6

        AppleComparatorByWeight comparatorByWeight = new AppleComparatorByWeight();
        inventory.sort(comparatorByWeight);
        System.out.println(inventory);
        //
        inventory.set(1, new Apple(30, "green"));
        inventory.sort(comparatorByWeight);
        System.out.println(inventory);
        //
        inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
        System.out.println(inventory);
        //
        inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
        System.out.println(inventory);
        //
        inventory.set(1, new Apple(10, "red"));
        inventory.sort(Comparator.comparing(Apple::getWeight));
        System.out.println(inventory);

    }

    //
    static class AppleComparatorByWeight implements Comparator<Apple> {
        @Override
        public int compare(Apple a1, Apple a2) {
            return a1.getWeight().compareTo(a2.getWeight());
        }
    }

    public static class Apple {
        private Integer weight = 0;
        private String color = "";

        public Apple(Integer weight, String color) {
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
