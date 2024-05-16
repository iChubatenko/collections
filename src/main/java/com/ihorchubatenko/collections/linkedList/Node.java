package com.ihorchubatenko.collections.linkedList;

public class Node<T> {
    T data;
    Node prev;
    Node next;

    public Node(T data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}
