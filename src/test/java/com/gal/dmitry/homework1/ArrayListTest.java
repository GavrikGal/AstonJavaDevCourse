package com.gal.dmitry.homework1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class ArrayListTest {

    private static final String[] STRING_DATA_SET = {"first", "second", "third"};
    private static final int[] INT_DATA_SET = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};


    ArrayList<String> stringArrayList;
    ArrayList<Integer> integerArrayList;

    @BeforeEach
    void createArrayLists() {
        stringArrayList = new ArrayList<String>();
        integerArrayList = new ArrayList<Integer>();
    }

    @Test
    void example() {
        //todo: удалить
        var testArray = new java.util.ArrayList<Integer>();
        testArray.add(1);
        var result = testArray.get(0);
        Assertions.assertEquals(1, result);
    }


    /**
     * Тест работы метода add(E element).
     * Имеется возможность добавления элемента.
     * Тестируется с элементами типа String.
     */
    @Test
    void add_String_canAddElement() {
        stringArrayList.add(STRING_DATA_SET[0]);
    }

    /**
     * Тест работы метода add(E element).
     * Имеется возможность добавления элемента.
     * Тестируется с элементами типа int.
     */
    @Test
    void add_Integer_canAddElement() {
        integerArrayList.add(INT_DATA_SET[0]);
    }


    /**
     * Тест работы метода get(int index).
     * Имеется возможность получения добавленного элемента.
     * Тестируется с элементами типа String.
     */
    @Test
    void get_String_canGetAddedElement() {
        stringArrayList.add(STRING_DATA_SET[0]);
        Assertions.assertEquals(STRING_DATA_SET[0], stringArrayList.get(0));
    }

    /**
     * Тест работы метода get(int index).
     * Имеется возможность получения добавленного элемента.
     * Тестируется с элементами типа int.
     */
    @Test
    void get_Integer_canGetAddedElement() {
        integerArrayList.add(INT_DATA_SET[0]);
        Assertions.assertEquals(INT_DATA_SET[0], integerArrayList.get(0));
    }

    /**
     * Тест работы метода get(int index).
     * Получение элемента с индексом равным ArrayList.size() и выше вызывает ошибку.
     * Тестируется с элементами типа String.
     */
    @Test
    void get_String_getWithIndexHigherThanArraySizeTrowException() {
        stringArrayList.add(STRING_DATA_SET[0]);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () ->
                stringArrayList.get(stringArrayList.size()));
    }

    /**
     * Тест работы метода get(int index).
     * Получение элемента с индексом меньше нуля вызывает ошибку.
     * Тестируется с элементами типа String.
     */
    @Test
    void get_String_getWithIndexLowerThanZeroTrowException() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> stringArrayList.get(-1));
    }

    /**
     * Тест работы метода add(E element).
     * Можно добавить больше чем один элемент в ArrayList.
     * Тестируется с элементами типа String.
     */
    @Test
    void add_String_canAddMoreThanOne() {
        for (var string : STRING_DATA_SET)
            stringArrayList.add(string);
    }

    /**
     * Тест работы метода get(int index).
     * Можно получить последний добавленный элемент,
     * после того, как было добавлено больше одного элемента.
     * Тестируется с элементами типа String.
     */
    @Test
    void get_String_canGetLastAddedElement() {
        for (var string : STRING_DATA_SET)
            stringArrayList.add(string);
        Assertions.assertEquals(
                STRING_DATA_SET[STRING_DATA_SET.length-1],
                stringArrayList.get(STRING_DATA_SET.length-1));
    }

    /**
     * Тест работы метода add(E element).
     * Можно добавить больше чем один элемент в ArrayList.
     * Тестируется с элементами типа int.
     */
    @Test
    void add_Integer_canAddMoreThanOne() {
        for (var element : INT_DATA_SET)
            integerArrayList.add(element);
    }


    /**
     * Тест работы закрытого метода expandDataArray().
     * Вызов метода expandDataArray() успешно увеличит размер внутреннего массива с данными.
     */
    @Test
    void expandDataArray_canExpandDataArray() throws NoSuchMethodException, InvocationTargetException,
            IllegalAccessException, NoSuchFieldException {

        Field data = ArrayList.class.getDeclaredField("data");
        data.setAccessible(true);

        var startData = (Object[]) data.get(integerArrayList);
        var startDataSize = startData.length;

        Method expand = ArrayList.class.getDeclaredMethod("expandDataArray");
        expand.setAccessible(true);
        expand.invoke(integerArrayList);

        var resultData = (Object[]) data.get(integerArrayList);
        var resultDataSize = resultData.length;

        String message = "Start data.size (" + startDataSize + ") should be greater than result ("
                + resultDataSize + ")";
        Assertions.assertTrue(startDataSize < resultDataSize, message);
    }

    /**
     * Тест работы закрытого метода checkIndexInRange(int index).
     * Проверяет успешное вхождение индекса в границы массива.
     */
    @Test
    void checkIndexInRange_passingWithLegalValue() throws NoSuchMethodException {
        integerArrayList.add(INT_DATA_SET[0]);
        Method checkIndexInRange = ArrayList.class.getDeclaredMethod("checkIndexInRange", int.class);
        checkIndexInRange.setAccessible(true);
        Assertions.assertDoesNotThrow(() -> checkIndexInRange.invoke(integerArrayList, 0));
    }

    /**
     * Тест работы закрытого метода checkIndexInRange(int index).
     * Проверяет выход индекса за границы массива.
     */
    @Test
    void checkIndexInRange_throwExceptionWithIllegalValue() throws NoSuchMethodException{
        integerArrayList.add(INT_DATA_SET[0]);
        Method checkIndexInRange = ArrayList.class.getDeclaredMethod("checkIndexInRange", int.class);
        checkIndexInRange.setAccessible(true);

        // IndexOutOfBoundsException is cause of InvocationTargetException
        Assertions.assertThrows(InvocationTargetException.class, () ->
                checkIndexInRange.invoke(integerArrayList, 1));
        Assertions.assertThrows(InvocationTargetException.class, () ->
                checkIndexInRange.invoke(integerArrayList, -1));
    }


    /**
     * Тест работы метода add(E element, int index).
     * Можно добавить элемент на позицию по номеру index.
     * Тестируется с элементами типа String.
     */
    @Test
    void add_StringWithIndex_canAddElementWithIndex() {
        for (String element : STRING_DATA_SET) {
            stringArrayList.add(element);
        }
        stringArrayList.add("fourth", 1);
        Assertions.assertEquals("fourth", stringArrayList.get(1));
    }

    /**
     * Тест работы закрытого метода checkIndexInRangeForAdd(int index).
     * Проверяет успешное вхождение индекса в границы массива (при добавлении элемента по индексу).
     */
    @Test
    void checkIndexInRangeForAdd_passingWithLegalValue() throws NoSuchMethodException {
        Method checkIndexInRangeForAdd = ArrayList.class.getDeclaredMethod("checkIndexInRangeForAdd", int.class);
        checkIndexInRangeForAdd.setAccessible(true);
        Assertions.assertDoesNotThrow(() -> checkIndexInRangeForAdd.invoke(integerArrayList, 0));
    }

    /**
     * Тест работы закрытого метода checkIndexInRangeForAdd(int index).
     * Проверяет выход индекса за границы массива (при добавлении элемента по индексу).
     */
    @Test
    void checkIndexInRangeForAdd_throwExceptionWithIllegalValue() throws NoSuchMethodException{
        integerArrayList.add(INT_DATA_SET[0]);
        Method checkIndexInRangeForAdd = ArrayList.class.getDeclaredMethod("checkIndexInRangeForAdd", int.class);
        checkIndexInRangeForAdd.setAccessible(true);

        // IndexOutOfBoundsException is cause of InvocationTargetException
        Assertions.assertThrows(InvocationTargetException.class, () ->
                checkIndexInRangeForAdd.invoke(integerArrayList, 2));
        Assertions.assertThrows(InvocationTargetException.class, () ->
                checkIndexInRangeForAdd.invoke(integerArrayList, -1));
    }

    /**
     * Тест работы метода size().
     * Вызов метода возвращает количество добавленных ранее элементов.
     * Тестируется с элементами типа String.
     */
    @Test
    void size_String_sizeReturnCountOfElement() {
        for (String element : STRING_DATA_SET) {
            stringArrayList.add(element);
        }
        Assertions.assertEquals(STRING_DATA_SET.length, stringArrayList.size());
    }

    /**
     * Тест работы метода remove(int index).
     * Вызов метода возвращает элемент на позиции index и удаляет его из ArrayList.
     * Размер ArrayList при этом уменьшается.
     * Тестируется с элементами типа String.
     */
    @Test
    void remove_String_canRemoveElement() {
        for (String element : STRING_DATA_SET) {
            stringArrayList.add(element);
        }

        Assertions.assertEquals(STRING_DATA_SET[1], stringArrayList.remove(1));
        Assertions.assertEquals(STRING_DATA_SET.length - 1, stringArrayList.size());
        Assertions.assertEquals(STRING_DATA_SET[2], stringArrayList.get(1));
    }

}