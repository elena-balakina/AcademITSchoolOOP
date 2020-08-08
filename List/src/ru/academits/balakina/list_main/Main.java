package ru.academits.balakina.list_main;

import ru.academits.balakina.list.ListItem;
import ru.academits.balakina.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<String> singlyLinkedList = new SinglyLinkedList<>();

        singlyLinkedList.addToBeginning("third");
        singlyLinkedList.addToBeginning("second");
        singlyLinkedList.addToBeginning("first");
        singlyLinkedList.print();
        System.out.println("Размер списка = " + singlyLinkedList.getCount());

        // получение значение первого элемента
        if (singlyLinkedList.getCount() == 0) {
            System.out.println("Список пустой");
        } else {
            System.out.println("Значение первого элемента списка = " + singlyLinkedList.getFirstItemData());
        }

        // получение значения по указанному индексу
        try {
            System.out.println("Значение элемента по индексу 2 = " + singlyLinkedList.getDataByIndex(2));
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            System.out.println();
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
            System.out.println();
        }

        // изменение значения по указанному индексу
        System.out.println("Старое значение по индексу 0 = " + singlyLinkedList.setDataByIndex(0, "newFirst"));
        System.out.println("Новое значение по индексу 0 = " + singlyLinkedList.getDataByIndex(0));
        System.out.println();

        // удаление элемента по индексу
        System.out.println("Значение удаленного элемента по индексу 0 = " + singlyLinkedList.deleteItemByIndex(0));
        System.out.println("Список после удаления элемента по индексу 0:");
        singlyLinkedList.print();
        System.out.println("Размер списка = " + singlyLinkedList.getCount());
        System.out.println();

        // вставка элемента по индексу
        System.out.println("Вставим элемент \"first\" по индексу 0:");
        singlyLinkedList.addItemByIndex(0, "first");
        System.out.println("Список после вставки нового элемента:");
        singlyLinkedList.print();
        System.out.println("Размер списка после вставки = " + singlyLinkedList.getCount());
        System.out.println();
        System.out.println("Вставим элемент \"fourth\" по индексу 3:");
        singlyLinkedList.addItemByIndex(3, "fourth");
        System.out.println("Список после вставки нового элемента:");
        singlyLinkedList.print();
        System.out.println("Размер списка = " + singlyLinkedList.getCount());
        System.out.println();
        System.out.println("Вставим элемент \"between 1 and 2\" по индексу 1:");
        singlyLinkedList.addItemByIndex(1, "between 1 and 2");
        System.out.println("Список после вставки нового элемента:");
        singlyLinkedList.print();
        System.out.println("Размер списка = " + singlyLinkedList.getCount());
        System.out.println();

        // удаление узла по значению, пусть выдает true, если элемент был удален
        if (singlyLinkedList.removeByData("between 1 and 2")) {
            System.out.println("Элемент between 1 and 2 был удален");
            System.out.println("Список после удаления:");
            singlyLinkedList.print();
            System.out.println("Размер списка = " + singlyLinkedList.getCount());
            System.out.println();
        } else {
            System.out.println("Элемент between 1 and 2 не был удален");
        }

        // удаление первого элемента, пусть выдает значение элемента
        System.out.println("Удален первый элемент \"" + singlyLinkedList.removeFromBeginning() + " \"");
        System.out.println("Список после удаления первого элемента:");
        singlyLinkedList.print();
        System.out.println("Размер списка = " + singlyLinkedList.getCount());
        System.out.println();

        singlyLinkedList.addToBeginning("first");
        System.out.println("Список:");
        singlyLinkedList.print();
        System.out.println("Размер списка = " + singlyLinkedList.getCount());
        System.out.println();

        // разворот списка
        singlyLinkedList.reverse();
        System.out.println("Список после разворота:");
        singlyLinkedList.print();
        System.out.println();

        // копирование списка
        SinglyLinkedList<String> singlyLinkedListCopy = singlyLinkedList.copy();
        System.out.println("Копия списка:");
        singlyLinkedListCopy.print();
    }
}
