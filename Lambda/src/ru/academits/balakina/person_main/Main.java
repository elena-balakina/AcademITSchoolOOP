package ru.academits.balakina.person_main;

import ru.academits.balakina.person.Person;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.averagingDouble;

public class Main {
    public static void main(String[] args) {
        ArrayList<Person> people = new ArrayList<>(Arrays.asList(
                new Person("Ivan", 30),
                new Person("Ivan", 35),
                new Person("Mark", 23),
                new Person("Mark", 25),
                new Person("Lera", 15),
                new Person("Lera", 19),
                new Person("Nina", 75)
        ));

        System.out.println("List: " + people);

        // А) получить список уникальных имен
        List<String> uniqueNames = people.stream()
                .map(Person::getName)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        System.out.println("А. Список уникальных имен: " + uniqueNames);
        System.out.println();

        // Б) вывести список уникальных имен в формате: Имена: Иван, Сергей, Петр
        String uniqueNamesString = people.stream()
                .map(Person::getName)
                .distinct()
                .sorted()
                .collect(Collectors.joining(", ", "Имена: ", "."));

        System.out.println("Б. Список уникальных имен в формате строки: \"Имена: Иван, Сергей, Петр.\"");
        System.out.println(uniqueNamesString);
        System.out.println();

        // В) получить список людей младше 18, посчитать для них средний возраст
        List<Person> youngPeople = people.stream()
                .filter(p -> p.getAge() < 18)
                .collect(Collectors.toList());

        System.out.println("В. Список людей младше 18 лет: " + youngPeople);

        double averageAgeUnder18 = youngPeople.stream()
                .collect(averagingDouble(Person::getAge));

        System.out.println("В. Средний возраст людей младше 18 лет = " + averageAgeUnder18);
        System.out.println();

        // Г) при помощи группировки получить Map, в котором ключи –  имена, а значения – средний возраст
        Map<String, Double> peopleByAge = people.stream()
                .collect(Collectors.groupingBy(Person::getName, averagingDouble(Person::getAge)));

        System.out.println("Г. Map (имена, средний возраст): ");
        peopleByAge.forEach((name, averageAge) -> System.out.printf("Name %s, average age = %s%n", name, averageAge));
        System.out.println();

        // Д) получить людей, возраст которых от 20 до 45, вывести в консоль их имена в порядке убывания возраста
        List<String> peopleFrom25To45 = people.stream()
                .filter(p -> p.getAge() >= 20 && p.getAge() < 45)
                .sorted((p1, p2) -> p2.getAge() - p1.getAge())
                .map(Person::getName)
                .collect(Collectors.toList());

        System.out.println("Д. Список имен людей, возраст которых от 20 до 45, в порядке убывания возраста:");
        peopleFrom25To45.forEach(System.out::println);
        System.out.println();
    }
}
