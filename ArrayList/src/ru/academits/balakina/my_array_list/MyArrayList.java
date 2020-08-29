package ru.academits.balakina.my_array_list;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private E[] items;
    private int length;
    private int modCount;

    public MyArrayList() {
        //noinspection unchecked
        items = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Передана вместимость списка " + capacity + ". Вместимости должна быть неотрицательным значением.");
        }

        //noinspection unchecked
        items = (E[]) new Object[capacity];
    }

    private void increaseCapacity() {
        items = Arrays.copyOf(items, (items.length + 1) * 2);
    }

    public void ensureCapacity(int neededCapacity) {
        if (neededCapacity > items.length) {
            items = Arrays.copyOf(items, neededCapacity);
        }
    }

    @Override
    public E set(int index, E element) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("Передан индекс: " + index + ". Индекс должен быть в диапазоне от 0 до " + (length - 1));
        }

        E temp = items[index];
        items[index] = element;

        return temp;
    }

    @Override
    public E get(int index) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("Передан индекс: " + index + ". Индекс должен быть в диапазоне от 0 до " + (length - 1));
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
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyListIterator();
    }

    private class MyListIterator implements Iterator<E> {
        private int currentIndex = -1;
        private final int currentModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция кончилась");
            }

            if (currentModCount != modCount) {
                throw new ConcurrentModificationException("Коллекция изменилась за время обхода");
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
            //noinspection unchecked
            return (T[]) Arrays.copyOf(items, length, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
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
        modCount++;

        return true;
    }

    // удаление по значению
    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);

        if (index >= 0) {
            remove(index);
            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
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

        System.arraycopy(items, index, items, index + c.size(), length - index);

        int i = index;

        for (E e : c) {
            items[i] = e;
            i++;
        }

        length += c.size();
        modCount++;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.isEmpty()) {
            return false;
        }

        int currentSize = size();

        for (int i = 0; i < length; i++) {
            if (c.contains(items[i])) {
                remove(i);
                i--;
            }
        }

        return currentSize != size();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c.isEmpty()) {
            return false;
        }

        int currentSize = size();

        for (int i = 0; i < length; i++) {
            if (!c.contains(items[i])) {
                remove(i);
                i--;
            }
        }

        return currentSize != size();
    }

    @Override
    public void clear() {
        Arrays.fill(items, null);

        length = 0;
        modCount++;
    }

    // добавление по индексу
    @Override
    public void add(int index, E element) {
        // Проверка индекса
        if (index > length || index < 0) {
            throw new IndexOutOfBoundsException("Переданный индекс = : " + index + ". Индекс должен быть больше нуля и меньше либо равен длине списка " + length);
        }

        if (index == length) {
            add(element);
            return;
        }

        if (length >= items.length) {
            increaseCapacity();
        }

        System.arraycopy(items, index, items, index + 1, length - index);
        items[index] = element;
        length++;
        modCount++;
    }

    // удаление по индексу
    @Override
    public E remove(int index) {
        // Проверка индекса
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("Передан индекс: " + index + ". Индекс должен быть в диапазоне от 0 до " + (length - 1));
        }

        if (index < length - 1) {
            System.arraycopy(items, index + 1,
                    items, index, length - index - 1);
        }

        items[length - 1] = null;
        length--;
        modCount++;

        return null;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < length; i++) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = length - 1; i >= 0; i--) {
            if (Objects.equals(items[i], o)) {
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
        return null;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("[ ");

        for (E item : items) {
            stringBuilder.append(item).append(", ");
        }

        if (stringBuilder.length() > 2) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }
        stringBuilder.append(" ]");

        return stringBuilder.toString();
    }
}
