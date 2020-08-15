package ru.academits.balakina.MyArrayList_main;

import ru.academits.balakina.MyArrayList.MyArrayList;

public class Main {
    public static void main(String[] args) {
        MyArrayList<String> arrayList = new MyArrayList<>(11);

        // добавление в список + set + get
        arrayList.add("1");
        System.out.println("Список: ");
        System.out.println(arrayList);
        System.out.println("Длина списка = " + arrayList.size());
        System.out.println();

        arrayList.set(0, "zero");
        System.out.println("Элемент списка по индексу 0 = " + arrayList.get(0));
        System.out.println("Список: ");
        System.out.println(arrayList);
        System.out.println("Длина списка = " + arrayList.size());
        System.out.println();

        arrayList.add("two");
        arrayList.add("three");
        arrayList.add("three");
        arrayList.add("four");
        System.out.println("Список: ");
        System.out.println(arrayList);
        System.out.println("Длина списка = " + arrayList.size());
        System.out.println();

        arrayList.add(1, "one");
        System.out.println("Список: ");
        System.out.println(arrayList);
        System.out.println("Длина списка = " + arrayList.size());
        System.out.println();

        // удаление по индексу
        arrayList.remove(2);
        System.out.println("Список: ");
        System.out.println(arrayList);
        System.out.println("Длина списка = " + arrayList.size());
        System.out.println();

        // удаление по значению
        arrayList.remove("one");
        System.out.println("Список: ");
        System.out.println(arrayList);
        System.out.println("Длина списка = " + arrayList.size());
        System.out.println();

        // очистка списка
        /*arrayList.clear();
        System.out.println("Список: ");
        System.out.println(arrayList);
        System.out.println("Длина списка = " + arrayList.size());
        System.out.println();*/

        // contains
        System.out.println("Список содержит \"three\"? - " + arrayList.contains("three"));

        // indexOf
        System.out.println("Индекс \"three\" = " + arrayList.indexOf("three"));
        System.out.println("Индекс последнего вхождения \"three\" = " + arrayList.lastIndexOf("three"));
        System.out.println();

        // sublist
        System.out.println("Sublist по индексам от 1 до 2: ");
        System.out.println(arrayList.subList(1, 2));
        System.out.println("Длина sublist'а = " + arrayList.subList(1, 2).size());
        System.out.println();

        // addAll в конец
        MyArrayList<String> arrayToAdd = new MyArrayList<>(3);
        arrayList.add("five");
        arrayList.add("six");
        arrayList.add("seven");

        arrayList.addAll(arrayToAdd);
        System.out.println("Список: ");
        System.out.println(arrayList);
        System.out.println("Длина списка = " + arrayList.size());
        System.out.println("/////////////////////");


        // add по индексу
        arrayList.add(0, "one");
        arrayList.set(1, "two");

        // remove по индексу
        arrayList.remove(2);

        System.out.println("Список: ");
        System.out.println(arrayList);
        System.out.println("Длина списка = " + arrayList.size());
        System.out.println();


        // addAll в конец списка
        System.out.println("Добавим в конец списка {eight, nine, ten}");
        MyArrayList<String> arrayToAdd2 = new MyArrayList<>(3);
        arrayToAdd2.add("eight");
        arrayToAdd2.add("nine");
        arrayToAdd2.add("ten");

        arrayList.addAll(arrayToAdd2);

        System.out.println("Список: ");
        System.out.println(arrayList);
        System.out.println("Длина списка = " + arrayList.size());
        System.out.println();

        // addAll по индексу
        System.out.println("Добавим {six1, six2} по индексу 6 (после six)");
        MyArrayList<String> arrayToAdd3 = new MyArrayList<>(2);
        arrayToAdd3.add("six1");
        arrayToAdd3.add("six2");

        arrayList.addAll(6, arrayToAdd3);

        System.out.println("Список: ");
        System.out.println(arrayList);
        System.out.println("Длина списка = " + arrayList.size());
        System.out.println();

        // containsAll
        System.out.println("Список содержит {six1, six2} ??? --> " + arrayList.containsAll(arrayToAdd3));
        System.out.println();

        // removeAll
        System.out.println("Удалим {six1, six2}");

        arrayList.removeAll(arrayToAdd3);

        System.out.println("Список: ");
        System.out.println(arrayList);
        System.out.println("Длина списка = " + arrayList.size());
        System.out.println("Список содержит {six1, six2} ??? --> " + arrayList.containsAll(arrayToAdd3));
        System.out.println();

        // retainAll
        System.out.println("Удалим все кроме {eight, nine, ten}");

        arrayList.retainAll(arrayToAdd2);

        System.out.println("Список: ");
        System.out.println(arrayList);
        System.out.println("Длина списка = " + arrayList.size());
        System.out.println();


    }
}
