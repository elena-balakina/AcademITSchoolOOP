package ru.academits.balakina;

import java.util.ArrayList;
import java.util.Arrays;

public class DeleteEvenNumbersFromList {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 4, 2, 3, 0, 5, 6, 7, 8, 9, 2, -2, 10));

        System.out.println("Исходный список: " + numbers);

        for (int i = 0; i < numbers.size(); i++) {
            int currentNumber = numbers.get(i);

            if (currentNumber % 2 == 0) {
                numbers.remove(i);
                i--;
            }
        }

        System.out.println("Список после удаления четных чисел: " + numbers);
    }
}
