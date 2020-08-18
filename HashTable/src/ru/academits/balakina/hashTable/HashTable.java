package ru.academits.balakina.hashTable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashTable<T> implements Collection<T> {
    private final int DEFAULT_CAPACITY = 10;
    public int capacity;
    private ArrayList<T>[] items;
    private int length = 0;

    // Конструктор без аргументов - создаетя массив пустых списков размером DEFAULT_CAPACITY
    public HashTable() {
        this.capacity = DEFAULT_CAPACITY;
        items = new ArrayList[capacity];
    }

    // Конструктор c 1 аргументом - capacity
    public HashTable(int capacity) {
        this.capacity = capacity;
        items = new ArrayList[capacity];
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public boolean contains(Object o) {
        int index = Math.abs(o.hashCode() % capacity);

        if (items[index] == null) {
            return false;
        }

        return items[index].contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();
    }

    private class MyListIterator implements Iterator<T> {
        private T[] array = (T[]) toArray();
        private int currentIndex = -1;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < array.length;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Заполненные элементы хэш-таблицы кончились");
            }

            currentIndex++;
            return array[currentIndex];
        }
    }

    @Override
    public Object[] toArray() {
        int index = 0;
        Object[] array = new Object[length];

        for (ArrayList<T> item : items) {
            if (item != null) {
                System.arraycopy(item.toArray(), 0, array, index, item.toArray().length);
                index += item.toArray().length;
            }
        }

        return array;
    }

    // TODO: сделать
    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        int index = Math.abs(t.hashCode() % capacity);

        if (items[index] == null) {
            items[index] = new ArrayList<>();
            items[index].add(t);
            length++;

            return true;
        }

        if (!items[index].contains(t)) {
            items[index].add(t);
            length++;

            return true;
        }

        return false;
    }

    @Override
    public boolean remove(Object o) {
        if (length == 0) {
            throw new NullPointerException("Количество заполненных элементов хэш-таблицы = 0, нет ни одного элемента для удаления");
        }

        if (!contains(o)) {
            return false;
        }

        int index = Math.abs(o.hashCode() % capacity);

        if (items[index].size() == 1) {
            items[index].clear();
            length--;
            return true;
        } else if (items[index].size() > 1) {
            items[index].remove(o);
            length--;
            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c.isEmpty()) {
            return false;
        }

        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (containsAll(c)) {
            return false;
        }

        for (Object e : c) {
            int index = Math.abs(e.hashCode() % capacity);

            if (items[index] == null) {
                items[index] = new ArrayList<>();
                items[index].add((T) e);
                length++;
            }

            if (!items[index].contains(e)) {
                items[index].add((T) e);
                length++;
            }
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (!containsAll(c)) {
            return false;
        }

        for (Object e : c) {
            int index = Math.abs(e.hashCode() % capacity);

            if (items[index].size() == 1) {
                items[index].clear();
                length--;
            } else if (items[index].size() > 1) {
                items[index].remove(e);
                length--;
            }
        }

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (!containsAll(c)) {
            return false;
        }

        ArrayList<Object> listToRemove = new ArrayList<>(this);

        for (Object e : c) {
            if (contains(e)) {
                listToRemove.remove(e);
            }
        }

        removeAll(listToRemove);

        return true;
    }

    @Override
    public void clear() {
        if (length != 0) {
            for (ArrayList<T> item : items) {
                if (item != null) {
                    item.clear();
                }
            }

            length = 0;
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Hashtable:").append(System.lineSeparator());

        for (int i = 0; i < items.length; i++) {
            s.append(i).append(": ");
            s.append(items[i]);
            if (i != items.length - 1) {
                s.append(System.lineSeparator());
            }
        }

        return s.toString();
    }
}
