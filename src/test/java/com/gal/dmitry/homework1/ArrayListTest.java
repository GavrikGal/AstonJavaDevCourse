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
    void createStringArrayList() {
        stringArrayList = new ArrayList<String>();
        integerArrayList = new ArrayList<Integer>();
    }

    @Test
    void example() {
        var testArray = new java.util.ArrayList<Integer>();
        testArray.add(1);
        var result = testArray.get(0);
        Assertions.assertEquals(1, result);
    }


    @Test
    void add_String_canAddElement() {
        stringArrayList.add(STRING_DATA_SET[0]);
    }

    @Test
    void add_Integer_canAddElement() {
        integerArrayList.add(INT_DATA_SET[0]);
    }


    @Test
    void get_String_canGetAddedElement() {
        stringArrayList.add(STRING_DATA_SET[0]);
        Assertions.assertEquals(STRING_DATA_SET[0], stringArrayList.get(0));
    }

    @Test
    void get_Integer_canGetAddedElement() {
        integerArrayList.add(INT_DATA_SET[0]);
        Assertions.assertEquals(INT_DATA_SET[0], integerArrayList.get(0));
    }


    @Test
    void get_String_getWithIndexHigherThanArraySizeTrowException() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> stringArrayList.get(42));
    }

    @Test
    void get_String_getWithIndexLowerThanZeroTrowException() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> stringArrayList.get(-1));
    }


    @Test
    void add_String_canAddMoreThanOneElement() {
        for (var string : STRING_DATA_SET)
            stringArrayList.add(string);
    }

    @Test
    void get_String_canGetLastAddedElement() {
        for (var string : STRING_DATA_SET)
            stringArrayList.add(string);
        Assertions.assertEquals(
                STRING_DATA_SET[STRING_DATA_SET.length-1],
                stringArrayList.get(STRING_DATA_SET.length-1));
    }


    @Test
    void add_Integer_canAddMoreThanOneElement() {
        for (var element : INT_DATA_SET)
            integerArrayList.add(element);
    }


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


    @Test
    void checkIndexInRange_passingWithLegalValue() throws NoSuchMethodException {
        integerArrayList.add(INT_DATA_SET[0]);
        Method checkIndexInRange = ArrayList.class.getDeclaredMethod("checkIndexInRange", int.class);
        checkIndexInRange.setAccessible(true);
        Assertions.assertDoesNotThrow(() -> checkIndexInRange.invoke(integerArrayList, 0));
    }

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


    @Test
    void add_StringWithIndex_canAddElementWithIndex() {
        for (String element : STRING_DATA_SET) {
            stringArrayList.add(element);
        }
        stringArrayList.add("fourth", 1);
        Assertions.assertEquals("fourth", stringArrayList.get(1));
    }


    @Test
    void checkIndexInRangeForAdd_passingWithLegalValue() throws NoSuchMethodException {
        Method checkIndexInRangeForAdd = ArrayList.class.getDeclaredMethod("checkIndexInRangeForAdd", int.class);
        checkIndexInRangeForAdd.setAccessible(true);
        Assertions.assertDoesNotThrow(() -> checkIndexInRangeForAdd.invoke(integerArrayList, 0));
    }

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

}