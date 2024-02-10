package com.gal.dmitry.homework1;

public class ArrayList<E> {

    private static final int DEFAULT_COUNT = 10;

    private Object[] data;
    private int size;

    public ArrayList() {
        data = new Object[DEFAULT_COUNT];
        size = 0;
    }

    public void add(E element) {
        data[size] = element;
        size++;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        return (E) data[index];
    }
}
