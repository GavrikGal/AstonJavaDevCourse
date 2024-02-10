package com.gal.dmitry.homework1;

public class ArrayList<E> {

    /**
     * Емкость внутреннего массива данных
     */
    private static final int DEFAULT_COUNT = 10;

    /**
     * Внутренний массив данных
     */
    private Object[] data;

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
    public ArrayList() {
        data = new Object[DEFAULT_COUNT];
        dataLength = DEFAULT_COUNT;
        size = 0;
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
     * @param element добавляемый элемент
     * @param index позиция добавляемого элемента
     */
    public void add(E element, int index) {
        checkIndexInRangeForAdd(index);
        if (size == dataLength) expandDataArray();
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
        size++;
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
    private void expandDataArray() {
        var buffer = data;
        dataLength = (int) (data.length * 1.5);
        data = new Object[dataLength];

        if (size >= 0) System.arraycopy(buffer, 0, data, 0, size);

    }
}
