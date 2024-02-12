package com.gal.dmitry.homework1;

import java.lang.reflect.Array;
import java.util.Collection;

public class ArrayList<E extends Comparable<E>> {

    /**
     * Емкость внутреннего массива данных
     */
    private static final int DEFAULT_COUNT = 10;

    /**
     * Внутренний массив данных
     */
    private E[] data;

    /**
     * Текущая емкость внутреннего массива данных
     */
    private int dataLength;

    /**
     * Количество данных в ArrayList
     */
    private int size;

    /**
     * Конструктор пустого ArrayList
     */
    @SuppressWarnings("unchecked")
    public ArrayList() {
        dataLength = DEFAULT_COUNT;
        size = 0;
        data = (E[]) Array.newInstance(Comparable.class, dataLength);
    }

    /**
     * Конструктор ArrayList, который принимает другую коллекцию в качестве параметра
     */
    @SuppressWarnings("unchecked")
    public ArrayList(Collection<? extends E> collection) {
        dataLength = DEFAULT_COUNT;
        data = (E[]) Array.newInstance(Comparable.class, dataLength);
        while (collection.size() > dataLength)
            expandDataArray();
        size = collection.size();
        if (size == 0) return;
        Object[] array = collection.toArray();
        System.arraycopy(array, 0, data, 0, size);
    }

    /**
     * Добавить элемент
     * @param element добавляемый элемент
     */
    public void add(E element) {
        if (size == dataLength) expandDataArray();
        data[size] = element;
        size++;
    }

    /**
     * Добавить элемент на позицию index.
     * Элементы, находящиеся от позиции index и далее, сдвигаются
     * @param index позиция добавляемого элемента
     * @param element добавляемый элемент
     */
    public void add(int index, E element) {
        checkIndexInRangeForAdd(index);
        if (size == dataLength) expandDataArray();
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
        size++;
    }

    /**
     * Добавить все элементы.
     * @param collection коллекция для добавления
     */
    public boolean addAll(Collection<? extends E> collection) {
        if (collection.isEmpty()) return false;
        while (size + collection.size() > dataLength) expandDataArray();
        Object[] array = collection.toArray();
        System.arraycopy(array, 0, data, size, array.length);
        size += array.length;
        return true;
    }

    /**
     * Добавить все элементы на позицию index.
     * Элементы находящиеся за позицией index сдвигаются на длину добавляемой коллекции
     * @param index - позиция добавляемого элемента
     * @param collection коллекция для добавления
     */
    public boolean addAll(int index, Collection<? extends E> collection) {
        checkIndexInRangeForAdd(index);
        if (collection.isEmpty()) return false;
        while (size + collection.size() > dataLength) expandDataArray();
        Object[] array = collection.toArray();

        System.arraycopy(data, index, data, index+array.length, size - index);
        System.arraycopy(array, 0, data, index, array.length);

        size += array.length;
        return true;
    }

    /**
     * Получение элемента с позицией index
     * @param index int позиция элемента
     * @return элемент на позиции index
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndexInRange(index);
        return (E) data[index];
    }

    /**
     * Получение количества элементов в ArrayList
     * @return int количество элементов
     */
    public int size() {
        return size;
    }

    /**
     * Удаления элемента на позиции index.
     * Расположенные после index сдвигаются на место удаляемого элемента.
     * Удаляемый элемент возвращается методом
     * @param index (int) позиция удаляемого элемента
     * @return удаляемый элемент
     */
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        checkIndexInRange(index);
        E returnedValue = (E) data[index];
        size--;
        System.arraycopy(data, index + 1, data, index, size - index);
        return returnedValue;
    }

    /**
     * Сортировка элементов пузырьком.
     * После сортировки элементы находятся в порядке возрастания
     */
    @SuppressWarnings("unchecked")
    public <T extends Comparable<T>> void sort() {
        if (this.isSorted())
            return;
        T[] data = (T[]) this.data;
        for (int out = size - 1; out >= 1; out--){
            for (int in = 0; in < out; in++){
                if(data[in].compareTo(data[in + 1]) > 0) {
                    T buff = data[in];
                    data[in] = data[in+1];
                    data[in+1] = buff;
                }
            }
        }
    }

    /**
     * Проверяет, является ли коллекция отсортированной
     * @return true - коллекция отсортирована
     */
    public boolean isSorted() {
        for (int i=0; i<size-1; i++) {
            if (data[i].compareTo(data[i+1]) > 0)
                return false;
        }
        return true;
    }

    /**
     * Служебный метод проверки нахождения индекса в границах массива
     * @param index (int) проверяемый индекс
     */
    private void checkIndexInRange(int index) {
        if ((index >= size) || (index < 0)) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Служебный метод проверки нахождения индекса в границах массива,
     * при добавлении элемента на позицию
     * @param index (int) проверяемый индекс
     */
    private void checkIndexInRangeForAdd(int index) {
        if ((index > size) || (index < 0)) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Служебный метод расширения внутреннего массива данных
     */
    @SuppressWarnings("unchecked")
    private void expandDataArray() {
        var buffer = data;
        dataLength = (int) (data.length * 1.5);
//        data = new Object[dataLength];
        data = (E[]) Array.newInstance(Comparable.class, dataLength);
        System.arraycopy(buffer, 0, data, 0, size);
    }
}
