package ru.academits.balakina.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<E> {
    private ListItem<E> head; // указатель на первый элемент списка
    private int count; // длина списка

    public SinglyLinkedList() {
    }

    // получение размера списка
    public int getCount() {
        return count;
    }

    // получение значение первого элемента
    public E getFirstItemData() {
        if (count == 0) {
            throw new NoSuchElementException("Список пуст, невозможно считать данные первого элемента");
        }

        return head.getData();
    }

    // получение Item по указанному индексу
    private ListItem<E> getItemByIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Передано значение " + index + ". Индекс выходит за границы списка: [0, " + (count - 1) + "]");
        }

        int i = 0;
        ListItem<E> result = head;

        for (ListItem<E> p = head; p != null; p = p.getNext()) {
            if (index == i) {
                result = p;
                break;
            }

            i++;
        }

        return result;
    }

    // получение значения по указанному индексу
    public E getDataByIndex(int index) {
        return getItemByIndex(index).getData();
    }

    // изменение значения по указанному индексу
    public E setDataByIndex(int index, E data) {
        ListItem<E> item = getItemByIndex(index);
        E result = item.getData();

        item.setData(data);

        return result;
    }

    // удаление элемента по индексу
    public E deleteItemByIndex(int index) {
        if (index == 0) {
            return removeFromBeginning();
        }

        ListItem<E> prevItem = getItemByIndex(index - 1);
        E result = prevItem.getNext().getData();

        prevItem.setNext(prevItem.getNext().getNext());
        count--;

        return result;
    }

    // вставка элемента в начало
    public void addToBeginning(E data) {
        head = new ListItem<>(data, head);
        count++;
    }

    // вставка элемента по индексу
    public void addItemByIndex(int index, E data) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Передано значение " + index + ". Индекс выходит за границы списка: [0, " + (count - 1) + "]");
        }

        if (index > count) {
            throw new IndexOutOfBoundsException("Передано значение " + index + ". Индекс превышает значение длины списка + 1 = " + (count + 1));
        }

        if (index == 0) {
            addToBeginning(data);
            return;
        }

        ListItem<E> newItem = new ListItem<>(data);
        ListItem<E> prevItem = getItemByIndex(index - 1);
        ListItem<E> currentItem = prevItem.getNext();

        prevItem.setNext(newItem);
        newItem.setNext(currentItem);
        count++;
    }

    // удаление узла по значению, пусть выдает true, если элемент был удален
    public boolean removeByData(E data) {
        if (count == 0) {
            throw new IllegalArgumentException("Список пуст, невозможно удалить элемент по значению");
        }

//        if (head.getData().equals(data)) {
//            removeFromBeginning();
//            return true;
//        }

        if (Objects.equals(head.getData(), data)) {
            removeFromBeginning();
            return true;
        }

        ListItem<E> prevItem = head;
        ListItem<E> currentItem = head.getNext();

        while (currentItem != null) {
            if (Objects.equals(currentItem.getData(), data)) {
                prevItem.setNext(currentItem.getNext());
                count--;
                return true;
            }

            prevItem = currentItem;
            currentItem = currentItem.getNext();
        }

        return false;
    }

    // удаление первого элемента, пусть выдает значение элемента
    public E removeFromBeginning() {
        if (count == 0) {
            throw new NoSuchElementException("Список пуст, невозможно удалить первый элемент");
        }

        E result = head.getData();

        head = head.getNext();
        count--;

        return result;
    }

    // разворот списка за линейное время
    public void reverse() {
        ListItem<E> prevItem = null;
        ListItem<E> currentItem = head;

        while (currentItem != null) {
            ListItem<E> tempNext = currentItem.getNext();

            currentItem.setNext(prevItem);
            prevItem = currentItem;
            currentItem = tempNext;
            head = prevItem;
        }
    }

    // копирование списка
    public SinglyLinkedList<E> copy() {
        SinglyLinkedList<E> singlyLinkedListCopy = new SinglyLinkedList<>();

        if (count == 0) {
            return singlyLinkedListCopy;
        }

        ListItem<E> currentItemCopy = new ListItem<>(head.getData());
        ListItem<E> nextItem = head.getNext();
        singlyLinkedListCopy.head = currentItemCopy;

        for (int i = 1; i < count; i++) {
            currentItemCopy.setNext(new ListItem<>(nextItem.getData()));
            nextItem = nextItem.getNext();
            currentItemCopy = currentItemCopy.getNext();
        }

        return singlyLinkedListCopy;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{ ");

        for (ListItem<E> p = head; p != null; p = p.getNext()) {
            stringBuilder.append(p.getData());
            stringBuilder.append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(" }");

        return stringBuilder.toString();
    }
}
