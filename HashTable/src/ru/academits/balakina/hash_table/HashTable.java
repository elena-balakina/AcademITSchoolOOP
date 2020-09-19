package ru.academits.balakina.hash_table;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private final static int DEFAULT_CAPACITY = 10;
    private final ArrayList<T>[] hashTableArray;
    private int length;
    private int modCount;

    // Конструктор без аргументов - создаетя массив пустых списков размером DEFAULT_CAPACITY
    public HashTable() {
        //noinspection unchecked
        hashTableArray = new ArrayList[DEFAULT_CAPACITY];
    }

    // Конструктор c 1 аргументом - capacity
    public HashTable(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Передана вместимость списка " + capacity + ". Вместимости должна быть больше 0.");
        }

        //noinspection unchecked
        hashTableArray = new ArrayList[capacity];
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    private int getIndex(Object o) {
        return (o == null) ? 0 : Math.abs(o.hashCode() % hashTableArray.length);
    }

    @Override
    public boolean contains(Object o) {
        int index = getIndex(o);

        if (hashTableArray[index] == null) {
            return false;
        }

        return hashTableArray[index].contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();
    }

    private class MyListIterator implements Iterator<T> {
        private int currentHashTableArrayIndex;
        private int elementIndex;
        private int currentListIndex;
        private final int currentModCount;

        public MyListIterator() {
            currentHashTableArrayIndex = 0;
            elementIndex = -1;
            currentListIndex = -1;
            currentModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            return elementIndex + 1 < length;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Заполненные элементы хэш-таблицы кончились");
            }

            if (currentModCount != modCount) {
                throw new ConcurrentModificationException("В коллекции изменилось количество элементов за время обхода");
            }

            for (int i = currentHashTableArrayIndex; i < hashTableArray.length; i++) {
                if (hashTableArray[i] == null) {
                    currentHashTableArrayIndex++;
                } else {
                    if (currentListIndex + 1 < hashTableArray[i].size()) {
                        currentListIndex++;
                        elementIndex++;
                    } else {
                        currentHashTableArrayIndex++;
                        currentListIndex = -1;
                        continue;
                    }
                    return hashTableArray[i].get(currentListIndex);
                }
            }

            return null;
        }
    }

    @Override
    public Object[] toArray() {
        int i = 0;
        Object[] array = new Object[length];

        for (T item : this) {
            array[i] = item;
            i++;
        }

        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        Object[] array = toArray();

        if (a.length < length) {
            //noinspection unchecked
            return (T1[]) Arrays.copyOf(array, length, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(array, 0, a, 0, length);

        if (a.length > length) {
            array[length] = null;
        }

        return a;
    }

    @Override
    public boolean add(T t) {
        int index = getIndex(t);

        if (hashTableArray[index] == null) {
            hashTableArray[index] = new ArrayList<>();
        }

        hashTableArray[index].add(t);
        length++;
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = getIndex(o);

        if (hashTableArray[index] == null) {
            return false;
        }

        if (hashTableArray[index].remove(o)) {
            length--;
            modCount++;
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

    @Override
    public boolean addAll(Collection<? extends T> c) {
        int oldLength = length;

        for (T e : c) {
            add(e);
        }

        return length != oldLength;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int oldLength = length;

        for (ArrayList<T> list : hashTableArray) {
            if (list != null) {
                length -= list.size();
                list.removeAll(c);
                length += list.size();
            }
        }

        if (length != oldLength) {
            modCount++;
        }

        return length != oldLength;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int oldLength = length;

        for (ArrayList<T> list : hashTableArray) {
            if (list != null) {
                length -= list.size();
                list.retainAll(c);
                length += list.size();
            }
        }

        if (length != oldLength) {
            modCount++;
        }

        return length != oldLength;
    }

    @Override
    public void clear() {
        Arrays.fill(hashTableArray, null);
        length = 0;
        modCount++;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Hashtable:").append(System.lineSeparator());

        for (int i = 0; i < hashTableArray.length; i++) {
            s.append(i).append(": ");
            s.append(hashTableArray[i]);
            if (i != hashTableArray.length - 1) {
                s.append(System.lineSeparator());
            }
        }

        return s.toString();
    }
}
