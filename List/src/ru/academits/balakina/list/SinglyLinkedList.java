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
            return null;
        }

        return head.getData();
    }

    // получение Item по указанному индексу
    public ListItem<E> getItemByIndex(int index) {
        ListItem<E> result = null;
        int i = 0;

        for (ListItem<E> p = head; p != null; p = p.getNext()) {
            if (index == i) {
                result = p;
            }
            i++;
        }

        return result;
    }

    // получение значения по указанному индексу
    public E getDataByIndex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Передано значение " + index + ". Индекс не может быть отрицатальной величиной");
        }

        if (index >= getCount()) {
            throw new IndexOutOfBoundsException("Передано значение " + index + ". Индекс выходит за границы списка");
        }

        E result = null;
        int i = 0;

        for (ListItem<E> p = head; p != null; p = p.getNext()) {
            if (index == i) {
                result = p.getData();
            }
            i++;
        }

        return result;
    }

    // изменение значения по указанному индексу
    public E setDataByIndex(int index, E data) {
        if (index < 0) {
            throw new IllegalArgumentException("Передано значение " + index + ". Индекс не может быть отрицатальной величиной");
        }

        if (index >= getCount()) {
            throw new IndexOutOfBoundsException("Передано значение " + index + ". Индекс выходит за границы списка");
        }

        E result = null;
        int i = 0;

        for (ListItem<E> p = head; p != null; p = p.getNext()) {
            if (index == i) {
                result = p.getData();
                p.setData(data);
            }
            i++;
        }
        return result;
    }

    // удаление элемента по индексу
    public E deleteItemByIndex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Передано значение " + index + ". Индекс не может быть отрицатальной величиной");
        }

        if (index >= getCount()) {
            throw new IndexOutOfBoundsException("Передано значение " + index + ". Индекс выходит за границы списка");
        }

        E result = null;
        int i = 0;

        for (ListItem<E> p = head, prev = null; p != null; prev = p, p = p.getNext()) {
            if (index == 0) {
                result = head.getData();
                head = p.getNext();
                count--;
                return result;
            } else {
                if (index == i) {
                    result = p.getData();
                    prev.setNext(p.getNext());
                    count--;
                }
                i++;
            }
        }

        return result;
    }

    // вставка элемента в начало
    public void addToBeginning(E data) {
        ListItem<E> newItem = new ListItem<>(data);

        newItem.setNext(head);
        head = newItem;
        count++;
    }

    // вставка элемента по индексу
    public void addItemByIndex(int index, E data) {
        if (index < 0) {
            throw new IllegalArgumentException("Передано значение " + index + ". Индекс не может быть отрицатальной величиной");
        }

        if (index > getCount()) {
            throw new IndexOutOfBoundsException("Передано значение " + index + ". Индекс превышает значение длины списка + 1");
        }

        if (index == 0) {
            addToBeginning(data);
            return;
        }

        ListItem<E> newItem = new ListItem<>(data);

        if (index == getCount()) {
            getItemByIndex(getCount() - 1).setNext(newItem);
            count++;
            return;
        }

        int i = 0;

        for (ListItem<E> p = head, prev = null; p != null; prev = p, p = p.getNext()) {
            if (index == i) {
                prev.setNext(newItem);
                newItem.setNext(p);
                count++;
            }
            i++;
        }
    }

    // удаление узла по значению, пусть выдает true, если элемент был удален
    public boolean removeByData(E data) {
        for (ListItem<E> p = head, prev = null; p != null; prev = p, p = p.getNext()) {
            if (p.getData() == data) {
                prev.setNext(p.getNext());
                count--;
                return true;
            }
        }

        return false;
    }

    // удаление первого элемента, пусть выдает значение элемента
    public E removeFromBeginning() {
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

        for (int i = 0; i < getCount(); i++) {
            singlyLinkedListCopy.addToBeginning(getItemByIndex(i).getData());
        }

        singlyLinkedListCopy.reverse();
        return singlyLinkedListCopy;
    }

    public void print() {
        for (ListItem<E> p = head; p != null; p = p.getNext()) {
            System.out.println(p.getData());
        }
    }
}
