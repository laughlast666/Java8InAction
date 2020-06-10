package lambdasinaction.chap6;

import java.util.*;

import static java.util.stream.Collectors.*;
import static lambdasinaction.chap6.Dish.dishTags;
import static lambdasinaction.chap6.Dish.menu;

public class C6Grouping {

    enum CaloricLevel {DIET, NORMAL, FAT}

    ;

    public static void main(String... args) {
        System.out.println("Dishes grouped by type: groupDishesByType()\n" +
                menu.stream().collect(groupingBy(Dish::getType)));
        System.out.println("Dish names grouped by type: groupDishNamesByType()\n" +
                menu.stream().collect(groupingBy(Dish::getType, mapping(Dish::getName, toList()))));
        System.out.println("Dish tags grouped by type: groupDishTagsByType()\n" +
                menu.stream().collect(groupingBy(Dish::getType,
                        flatMapping(dish -> dishTags.get(dish.getName()).stream(), toSet()))));
        System.out.println("Caloric dishes grouped by type: groupCaloricDishesByType()\n" +
                menu.stream().collect(groupingBy(Dish::getType,
                        filtering(dish -> dish.getCalories() > 500, toList()))));
        System.out.println("Dishes grouped by caloric level: groupDishesByCaloricLevel()\n" +
                menu.stream().collect(groupingBy(dish -> getCaloricLevel(dish))));
        System.out.println("Dishes grouped by type and caloric level: groupDishedByTypeAndCaloricLevel()\n" +
                menu.stream().collect(groupingBy(Dish::getType, groupingBy(dish -> getCaloricLevel(dish)))));
        System.out.println("Count dishes in groups: countDishesInGroups()\n" +
                menu.stream().collect(groupingBy(Dish::getType, counting())));
        System.out.println("Most caloric dishes by type: mostCaloricDishesByType() \n" +
                menu.stream().collect(groupingBy(Dish::getType, maxBy(Comparator.comparingInt(Dish::getCalories)))));
        System.out.println("Most caloric dishes by type:+ mostCaloricDishesByTypeWithoutOprionals() \n" +
                menu.stream().collect(groupingBy(Dish::getType,
                        collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)),
                                Optional::get))));
        System.out.println("Sum calories by type: sumCaloriesByType() \n" +
                menu.stream().collect(groupingBy(Dish::getType, summingInt(Dish::getCalories))));
        System.out.println("Caloric levels by type: caloricLevelsByType()\n" +
                menu.stream().collect(groupingBy(Dish::getType, mapping(dish -> getCaloricLevel(dish), toSet()))));
    }

    private static Map<Dish.Type, List<Dish>> groupDishesByType() {
        return menu.stream().collect(groupingBy(Dish::getType));
    }

    private static Map<Dish.Type, List<String>> groupDishNamesByType() {
        return menu.stream().collect(groupingBy(Dish::getType, mapping(Dish::getName, toList())));
    }

    private static Map<Dish.Type, Set<String>> groupDishTagsByType() {
        return menu.stream().collect(groupingBy(Dish::getType, flatMapping(dish -> dishTags.get(dish.getName()).stream(), toSet())));
    }

    private static Map<Dish.Type, List<Dish>> groupCaloricDishesByType() {
//        return menu.stream().filter(dish -> dish.getCalories() > 500).collect(groupingBy(Dish::getType));
        return menu.stream().collect(groupingBy(Dish::getType, filtering(dish -> dish.getCalories() > 500, toList())));
    }

    private static Map<CaloricLevel, List<Dish>> groupDishesByCaloricLevel() {
        return menu.stream().collect(
                groupingBy(dish -> {
                    return getCaloricLevel(dish);
                }));
    }

    private static CaloricLevel getCaloricLevel(Dish dish) {
        if (dish.getCalories() <= 400)
            return CaloricLevel.DIET;
        else if (dish.getCalories() <= 700)
            return CaloricLevel.NORMAL;
        else
            return CaloricLevel.FAT;
    }

    private static Map<Dish.Type, Map<CaloricLevel, List<Dish>>> groupDishedByTypeAndCaloricLevel() {
        return menu.stream().collect(
                groupingBy(Dish::getType,
                        groupingBy((Dish dish) -> {
                            return getCaloricLevel(dish);
                        })
                )
        );
    }

    private static Map<Dish.Type, Long> countDishesInGroups() {
        return menu.stream().collect(groupingBy(Dish::getType, counting()));
    }

    private static Map<Dish.Type, Optional<Dish>> mostCaloricDishesByType() {
        return menu.stream().collect(
                groupingBy(Dish::getType,
                        reducing((Dish d1, Dish d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)));
    }

    private static Map<Dish.Type, Dish> mostCaloricDishesByTypeWithoutOprionals() {
        return menu.stream().collect(
                groupingBy(Dish::getType,
                        collectingAndThen(
                                reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2),
                                Optional::get)));
    }

    private static Map<Dish.Type, Integer> sumCaloriesByType() {
        return menu.stream().collect(groupingBy(Dish::getType,
                summingInt(Dish::getCalories)));
    }

    private static Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType() {
        return menu.stream().collect(
                groupingBy(Dish::getType, mapping(
                        dish -> {
                            return getCaloricLevel(dish);
                        },
                        toSet())));
    }
}
