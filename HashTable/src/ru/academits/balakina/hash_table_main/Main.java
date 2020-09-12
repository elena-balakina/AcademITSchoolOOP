package ru.academits.balakina.hash_table_main;

import ru.academits.balakina.hash_table.HashTable;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        HashTable<Integer> hashTable = new HashTable<>();

        System.out.println(hashTable);
        System.out.println("size = " + hashTable.size());
        System.out.println();

        System.out.println("Добавим 5 в хэш-таблицу: " + hashTable.add(5));
        System.out.println(hashTable);
        System.out.println("size = " + hashTable.size());
        System.out.println();

        System.out.println("Добавим 10 в хэш-таблицу: " + hashTable.add(10));
        System.out.println(hashTable);
        System.out.println("size = " + hashTable.size());
        System.out.println();

        System.out.println("Добавим 20 в хэш-таблицу: " + hashTable.add(20));
        System.out.println(hashTable);
        System.out.println("size = " + hashTable.size());
        System.out.println();

        System.out.println("Хэш-таблица содержит 4? -  " + hashTable.contains(4));
        System.out.println("Хэш-таблица содержит 5? -  " + hashTable.contains(5));
        System.out.println("Хэш-таблица содержит 10? -  " + hashTable.contains(10));
        System.out.println();

        System.out.println("Удалим 20: " + hashTable.remove(20));
        System.out.println(hashTable);
        System.out.println("size = " + hashTable.size());
        System.out.println();

        // список для проверки containsAll
        ArrayList<Integer> arrayToCheck = new ArrayList<>();
        arrayToCheck.add(5);
        arrayToCheck.add(10);
        System.out.println("Хэш-таблица содержит [5, 10]? -  " + hashTable.containsAll(arrayToCheck));
        System.out.println("Добавим в массив 20: " + arrayToCheck.add(20));
        System.out.println("Хэш-таблица содержит [5, 10, 20]? -  " + hashTable.containsAll(arrayToCheck));
        System.out.println();


        System.out.println("Добавим в хэш-таблицу [5, 10, 20]: " + hashTable.addAll(arrayToCheck));
        System.out.println(hashTable);
        System.out.println("size = " + hashTable.size());
        System.out.println();

        System.out.println("Добавим в хэш-таблицу [6, 7, 4, 2, 3, 8, 8, 9]: " + hashTable.addAll(new ArrayList<>(Arrays.asList(6, 7, 4, 2, 3, 8, 8, 9))));
        System.out.println(hashTable);
        System.out.println("size = " + hashTable.size());
        System.out.println();

        // Проверка итератора
        int i = 1;

        System.out.println("(!) Проверка итератора:");
        for (int item : hashTable) {
            System.out.println("Element " + i + ": " + item);
            i++;
        }

        System.out.println();
        System.out.println("Удалим из хэш-таблицы [5, 10, 20]: " + hashTable.removeAll(arrayToCheck));
        System.out.println(hashTable);
        System.out.println("size = " + hashTable.size());
        System.out.println();

        System.out.println("Добавим 2 в хэш-таблицу: " + hashTable.add(2));
        System.out.println(hashTable);
        System.out.println("size = " + hashTable.size());
        System.out.println();

        // retainAll
        System.out.println("Оставим в хэш-таблице только [2, 3]: " + hashTable.retainAll(new ArrayList<>(Arrays.asList(2, 3))));
        System.out.println(hashTable);
        System.out.println("size = " + hashTable.size());
        System.out.println();

        System.out.println("Очистим хэш-таблицу");
        hashTable.clear();
        System.out.println(hashTable);
        System.out.println("size = " + hashTable.size());
        System.out.println();
    }
}
