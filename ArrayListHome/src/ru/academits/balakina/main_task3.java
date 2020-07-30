package ru.academits.balakina;

import java.util.ArrayList;
import java.util.Arrays;

public class main_task3 {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(-1, 0, 0, 0, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6, 7, 8, 9, 10));
        ArrayList<Integer> result = new ArrayList<>();

        for (Integer number: numbers){
            if (!result.contains(Integer.valueOf(number))) {
                result.add(number);
            }
        }

        System.out.println("Исходный массив: " + numbers);
        System.out.println("Массив без повторений: " + result);
    }
}
