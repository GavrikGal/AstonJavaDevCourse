package com.gal.dmitry.homework1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArrayListTest {

    private static final String[] TEST_STRING_DATA_SET = {"first", "second", "third"};
    private static final int[] TEST_INTEGER_DATA_SET = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};


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
        var expect = testArray.get(0);
        Assertions.assertEquals(expect, 1);
    }


    @Test
    void add_String_CanAddElement() {
        stringArrayList.add(TEST_STRING_DATA_SET[0]);
    }

    @Test
    void add_Integer_CanAddElement() {
        integerArrayList.add(TEST_INTEGER_DATA_SET[0]);
    }


    @Test
    void get_String_CanGetAddedElement() {
        stringArrayList.add(TEST_STRING_DATA_SET[0]);
        Assertions.assertEquals(stringArrayList.get(0), TEST_STRING_DATA_SET[0]);
    }

    @Test
    void get_Integer_CanGetAddedElement() {
        integerArrayList.add(TEST_INTEGER_DATA_SET[0]);
        Assertions.assertEquals(integerArrayList.get(0), TEST_INTEGER_DATA_SET[0]);
    }

}