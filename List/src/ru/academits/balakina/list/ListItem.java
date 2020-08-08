package ru.academits.balakina.list;

public class ListItem<E> {
    private E data;
    private ListItem<E> next;

    // конструктор для создани узла списка без ссылки на следующий узел => ListItem(data, null)
    public ListItem(E data) {
        this.data = data;
    }

    public ListItem(E data, ListItem<E> next) {
        this.data = data;
        this.next = next;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public ListItem<E> getNext() {
        return next;
    }

    public void setNext(ListItem<E> next) {
        this.next = next;
    }
}
