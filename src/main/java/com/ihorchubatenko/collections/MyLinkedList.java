package com.ihorchubatenko.collections;

public class MyLinkedList<T> {
    private Node head;
    private Node tail;
    private int size;

    public MyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addAtFirst(T data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    public void addAtLast(T data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public void addAtIndex(T data, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        Node<T> newNode = new Node<>(data);

        if (index == 0) {
            addAtFirst(newNode.data);
        } else if (index == size) {
            addAtLast(newNode.data);

        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            newNode.prev = current;
            current.next.prev = newNode;
            current.next = newNode;
        }

        size++;
    }

    public T get(int index) {

        if (isEmpty()) {
            System.out.println("The list is empty");
            return null;
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
            if (current == null) {
                throw new IndexOutOfBoundsException("Invalid index");
            }
        }
        System.out.println(current.data);
        return current.data;
    }

    public void removeFirst() {
        if (isEmpty()) {
            System.out.println("The list is empty");
            return;
        }
        head = head.next;
        if (head != null) {
            head.prev = null;
        }
        size--;
    }

    public void removeLast() {
        if (isEmpty()) {
            System.out.println("The list is empty");
            return;
        }
        tail = tail.prev;
        if (tail != null) {
            tail.next = null;
        } else {
            head = null;
        }
        size--;
    }

    public void remove(int index) {
        if (isEmpty() || index < 0 || index >= size) {
            System.out.println("Invalid index");
            return;
        }
        if (index == 0) {
            removeFirst();
            return;
        }
        if (index == size - 1) {
            removeLast();
            return;
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.prev.next = current.next;
        current.next.prev = current.prev;
        size--;
    }

    public void remove(T value) {
        if (isEmpty()) {
            System.out.println("The list is empty");
            return;
        }
        if (head.data.equals(value)) {
            removeFirst();
            return;
        }
        if (tail.data.equals(value)) {
            removeLast();
            return;
        }
        Node current = head;
        while (current != null && !current.data.equals(value)) {
            current = current.next;
        }
        if (current != null) {
            current.prev.next = current.next;
            current.next.prev = current.prev;
            size--;
        }
    }

    public void removeAll() {
        if (isEmpty()) {
            System.out.println("The list is empty");
            return;
        } else {
            head = null;
            tail = null;
        }
    }

    public void printList() {
        if (isEmpty()) {
            System.out.println("The list is empty");
            return;
        }
        Node current = head;
        System.out.print(getClass().getName() + " {");
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(", ");
            }
            current = current.next;
        }
        System.out.print("}");
        System.out.println();
    }
}
