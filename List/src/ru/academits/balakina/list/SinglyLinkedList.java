package ru.academits.balakina.list;

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
            throw new NullPointerException("Список пуст, невозможно считать данные первого элемента");
        }

        return head.getData();
    }

    // получение Item по указанному индексу
    private ListItem<E> getItemByIndex(int index) {
        if (index < 0 || index >= getCount()) {
            throw new NullPointerException("Передано значение " + index + ". Индекс выходит за границы списка");
        }

        int i = 0;

        for (ListItem<E> p = head; p != null; p = p.getNext()) {
            if (index == i) {
                return p;
            }

            i++;
        }

        return null;
    }

    // получение значения по указанному индексу
    public E getDataByIndex(int index) {
        if (index < 0 || index >= getCount()) {
            throw new IndexOutOfBoundsException("Передано значение " + index + ". Индекс выходит за границы списка");
        }

        return getItemByIndex(index).getData();
    }

    // изменение значения по указанному индексу
    public E setDataByIndex(int index, E data) {
        if (index < 0 || index >= getCount()) {
            throw new IndexOutOfBoundsException("Передано значение " + index + ". Индекс выходит за границы списка");
        }

        E result = getItemByIndex(index).getData();
        getItemByIndex(index).setData(data);

        return result;
    }

    // удаление элемента по индексу
    public E deleteItemByIndex(int index) {
        if (index < 0 || index >= getCount()) {
            throw new IndexOutOfBoundsException("Передано значение " + index + ". Индекс выходит за границы списка");
        }

        E result;
        ListItem<E> deletedItem = getItemByIndex(index);

        if (index == 0) {
            result = head.getData();
            head = deletedItem.getNext();
            count--;
            return result;
        }

        result = deletedItem.getData();
        ListItem<E> prevItem = getItemByIndex(index - 1);

        prevItem.setNext(deletedItem.getNext());
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
            throw new IndexOutOfBoundsException("Передано значение " + index + ". Индекс выходит за границы списка");
        }

        if (index > getCount()) {
            throw new IndexOutOfBoundsException("Передано значение " + index + ". Индекс превышает значение длины списка + 1");
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
        for (int i = 0; i < count; i++) {
            if (getItemByIndex(i).getData() == data) {
                deleteItemByIndex(i);
                return true;
            }
        }

        return false;
    }

    // удаление первого элемента, пусть выдает значение элемента
    public E removeFromBeginning() {
        if (count == 0) {
            throw new NullPointerException("Список пуст, невозможно удалить первый элемент");
        }

        E result = head.getData();

        head = head.getNext();
        count--;

        return result;
    }

    // разворот списка за линейное время
    public void reverse() {
        ListItem<E> prev = null;
        ListItem<E> current = head;

        while (current != null) {
            ListItem<E> tempNext = current.getNext();

            current.setNext(prev);
            prev = current;
            current = tempNext;
            head = prev;
        }
    }

    // копирование списка
    public SinglyLinkedList<E> copy() {
        SinglyLinkedList<E> singlyLinkedListCopy = new SinglyLinkedList<>();

        if (getCount() == 0) {
            return singlyLinkedListCopy;
        }

        ListItem<E> currentItem = new ListItem<>(head.getData());
        ListItem<E> nextItem = head.getNext();
        singlyLinkedListCopy.head = currentItem;

        for (int i = 1; i < getCount(); i++) {
            currentItem.setNext(new ListItem<>(nextItem.getData()));
            nextItem = nextItem.getNext();
            currentItem = currentItem.getNext();
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
