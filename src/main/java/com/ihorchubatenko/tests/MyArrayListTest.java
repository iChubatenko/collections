package com.ihorchubatenko.tests;

import com.ihorchubatenko.collections.MyArrayList;

public class MyArrayListTest {
    public static void main(String[] args) {

        MyArrayList<String> arrayList = new MyArrayList<>();

        arrayList.add("Hello");
        arrayList.add("Bye");


        System.out.println(arrayList.get(0));

        arrayList.add(0, "How are you?");

        System.out.println(arrayList);

        arrayList.remove(2);

        System.out.println(arrayList);

        arrayList.add(1, "Good");

        System.out.println(arrayList);

        arrayList.set(1, "Super");

        System.out.println(arrayList);

    }
}
