package ru.academits.balakina.my_array_list_main;

import ru.academits.balakina.my_array_list.MyArrayList;

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

        // contains
        System.out.println("Список содержит \"three\"? - " + arrayList.contains("three"));

        // indexOf
        System.out.println("Индекс \"three\" = " + arrayList.indexOf("three"));
        System.out.println("Индекс последнего вхождения \"three\" = " + arrayList.lastIndexOf("three"));
        System.out.println();

        // addAll в конец
        MyArrayList<String> listForAdding1 = new MyArrayList<>(3);
        listForAdding1.add("five");
        listForAdding1.add("six");
        listForAdding1.add("seven");

        arrayList.addAll(listForAdding1);
        System.out.println("Список: ");
        System.out.println(arrayList);
        System.out.println("Длина списка = " + arrayList.size());
        System.out.println();

        // add по индексу
        arrayList.add(1, "one");
        arrayList.add(2, "two");

        // remove по индексу
        arrayList.remove(3);

        System.out.println("Список: ");
        System.out.println(arrayList);
        System.out.println("Длина списка = " + arrayList.size());
        System.out.println();

        // addAll в конец списка
        System.out.println("Добавим в конец списка {eight, nine, ten}");
        MyArrayList<String> listForAdding2 = new MyArrayList<>(3);
        listForAdding2.add("eight");
        listForAdding2.add("nine");
        listForAdding2.add("ten");

        arrayList.addAll(listForAdding2);

        System.out.println("Список: ");
        System.out.println(arrayList);
        System.out.println("Длина списка = " + arrayList.size());
        System.out.println();

        // addAll по индексу
        System.out.println("Добавим {six, six} по индексу 6 (после six)");
        MyArrayList<String> listForAdding3 = new MyArrayList<>(2);
        listForAdding3.add("six");
        listForAdding3.add("six");

        arrayList.addAll(6, listForAdding3);

        System.out.println("Список: ");
        System.out.println(arrayList);
        System.out.println("Длина списка = " + arrayList.size());
        System.out.println();

        // containsAll
        MyArrayList<String> listForCheckingContainsAll = new MyArrayList<>(1);
        listForCheckingContainsAll.add("six");
        System.out.println("Список содержит {six} ??? --> " + arrayList.containsAll(listForCheckingContainsAll));
        System.out.println();

        // removeAll
        System.out.println("Удалим {six} " + arrayList.removeAll(listForCheckingContainsAll));

        System.out.println("Список: ");
        System.out.println(arrayList);
        System.out.println("Длина списка = " + arrayList.size());
        System.out.println("Список содержит {six} ??? --> " + arrayList.containsAll(listForCheckingContainsAll));
        System.out.println();

        // retainAll
        System.out.println("Удалим все кроме {eight, nine, ten} " + arrayList.retainAll(listForAdding2));

        System.out.println("Список: ");
        System.out.println(arrayList);
        System.out.println("Длина списка = " + arrayList.size());

        // indexOf
        System.out.println("Индекс элемента {eight} = " + arrayList.indexOf("eight"));
        System.out.println("Индекс последнего элемента {ten} = " + arrayList.lastIndexOf("ten"));
        System.out.println();

        // очистка списка
        arrayList.clear();
        System.out.println("Список после очистки: ");
        System.out.println(arrayList);
        System.out.println("Длина списка = " + arrayList.size());
        System.out.println();
    }
}
