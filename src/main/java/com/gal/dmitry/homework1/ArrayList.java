package com.gal.dmitry.homework1;

public class ArrayList<E> {

    private static final int DEFAULT_COUNT = 10;

    private Object[] data;
    private int size;

    public ArrayList() {
        data = new Object[DEFAULT_COUNT];
    }

    public void add(E element) {
        data[size] = element;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        return (E) data[index];
    }
}
