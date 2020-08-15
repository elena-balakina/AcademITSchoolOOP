package ru.academits.balakina.MyArrayList;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private final int DEFAULT_CAPACITY = 10;
    private E[] items;
    private int length;

    public MyArrayList() {
        items = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Передана вместимость списка " + capacity + ". Вместимости должна быть больше нуля.");
        }

        items = (E[]) new Object[capacity];
    }

    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    public void ensureCapacity(int neededCapacity) {
        if (neededCapacity > items.length) {
            items = Arrays.copyOf(items, neededCapacity);
        }
    }

    @Override
    public E set(int index, E element) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Список пустой");
        }

        if (index > length) {
            throw new IllegalArgumentException("Индекс превышает размер списка");
        }

        items[index] = element;

        return null;
    }

    @Override
    public E get(int index) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Список пустой");
        }

        if (index > length) {
            throw new IllegalArgumentException("Индекс превышает размер списка");
        }

        return items[index];
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
        for (int i = 0; i < length; i++) {
            if (get(i) == o) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyListIterator();
    }

    private class MyListIterator implements Iterator<E> {
        private int currentIndex = -1;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция кончилась");
            }

            currentIndex++;
            return items[currentIndex];
        }
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, length);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < length) {
            return (T[]) Arrays.copyOf(items, length, a.getClass());
        }

        System.arraycopy(items, 0, a, 0, length);

        if (a.length > length) {
            a[length] = null;
        }

        return a;
    }

    // добавление в конец
    @Override
    public boolean add(E e) {
        if (length == items.length) {
            increaseCapacity();
        }

        items[length] = e;
        length++;

        return true;
    }

    // удаление по значению
    @Override
    public boolean remove(Object o) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Список пустой");
        }

        for (int i = 0; i < length; i++) {
            if (get(i) == o) {
                System.arraycopy(items, i + 1,
                        items, i, length - i - 1);
            }
        }

        items[length - 1] = null;
        length--;

        return true;
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

    // добавление коллекции в конец списка
    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.isEmpty()) {
            return false;
        }

        return addAll(length, c);
    }

    // добавление коллекции по индексу
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (c.isEmpty()) {
            return false;
        }

        ensureCapacity(length + c.size());

        E[] temp = (E[]) c.toArray();

        if (index != length) {
            System.arraycopy(items, index, items, index + c.size(), length - index);
        }

        System.arraycopy(temp, 0, items, index, temp.length);
        length += c.size();

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.isEmpty()) {
            return false;
        }

        for (Object e : c) {
            if (contains(e)) {
                remove(e);
            }
        }

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c.isEmpty()) {
            return false;
        }

        for (int i = 0; i < size(); i++) {
            if (!c.contains(items[i])) {
                remove(i);
                i--;
            }
        }

        return true;
    }

    @Override
    public void clear() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Список пустой");
        } else {
            for (int i = 0; i < length; i++) {
                items[i] = null;
            }

            length = 0;
        }
    }

    // добавление по индексу
    @Override
    public void add(int index, E element) {
        if (index == length) {
            add(element);
        }

        if (length >= items.length) {
            increaseCapacity();
        }

        System.arraycopy(items, index, items, index + 1, length - index);
        items[index] = element;
        length++;
    }

    // удаление по индексу
    @Override
    public E remove(int index) {
        if (index > length) {
            throw new IllegalArgumentException("Индекс превышает размер списка");
        }

        if (index < length - 1) {
            System.arraycopy(items, index + 1,
                    items, index, length - index - 1);
        }

        items[length - 1] = null;
        length--;

        return null;
    }

    @Override
    public int indexOf(Object o) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Список пустой");
        }

        for (int i = 0; i < length; i++) {
            if (get(i) == o) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Список пустой");
        }

        for (int i = length; i >= 0; i--) {
            if (get(i) == o) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        MyArrayList<E> sublist = new MyArrayList<>(toIndex - fromIndex + 1);

        for (int i = 0; i < length; i++) {
            if (i >= fromIndex && i <= toIndex) {
                sublist.add(get(i));
            }
        }

        return sublist;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("[ ");

        for (E item : items) {
            if (item != null) {
                stringBuilder.append(item).append(", ");
            }
        }

        if (stringBuilder.length() > 2) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }
        stringBuilder.append(" ]");

        return stringBuilder.toString();
    }
}
