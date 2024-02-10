package com.gal.dmitry.homework1;

public class ArrayList<E> {

    private static final int DEFAULT_COUNT = 10;

    private Object[] data;
    private int dataLength;
    private int size;

    public ArrayList() {
        data = new Object[DEFAULT_COUNT];
        dataLength = DEFAULT_COUNT;
        size = 0;
    }

    public void add(E element) {
        if (size == dataLength) expandDataArray();
        data[size] = element;
        size++;
    }

    public void add(E element, int index) {
        checkIndexInRangeForAdd(index);
        if (size == dataLength) expandDataArray();
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
        size++;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndexInRange(index);
        return (E) data[index];
    }

    private void checkIndexInRange(int index) {
        if ((index >= size) || (index < 0)) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void checkIndexInRangeForAdd(int index) {
        if ((index > size) || (index < 0)) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void expandDataArray() {
        var buffer = data;
        dataLength = (int) (data.length * 1.5);
        data = new Object[dataLength];

        if (size >= 0) System.arraycopy(buffer, 0, data, 0, size);

    }
}
