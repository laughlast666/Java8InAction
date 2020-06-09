package lambdasinaction.chap5;

import lambdasinaction.chap4.Dish;

import java.util.Optional;

import static lambdasinaction.chap4.Dish.menu;

public class C5Finding {

    public static void main(String... args) {
        System.out.println(menu.stream().anyMatch(Dish::isVegetarian) ? "Vegetarian Friendly" : "No Vegetarial");
        System.out.println(menu.stream().anyMatch(dish -> dish.getCalories() < 1000) ? "All Fit" : "Non All Fit");
        System.out.println(menu.stream().noneMatch(dish -> dish.getCalories() >= 1000) ? "All Fit" : "Non All Fit");

        Optional<Dish> firstVegetarian = menu.stream().filter(Dish::isVegetarian).findFirst();
        firstVegetarian.ifPresent(dish -> System.out.println(dish.getName()));
        Optional<Dish> anyVegetarian = menu.stream().filter(Dish::isVegetarian).findAny();
        for (int i = 3; i-- > 0; )
            anyVegetarian.ifPresent(dish -> System.out.println(dish.equals(firstVegetarian.get())));
    }

}
