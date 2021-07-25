package implementation.JLinkedList.SinglyLinkedList;

import java.util.Collection;
import java.util.Iterator;

public class SinglyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

//    1. constructors

    //  empty constructor
    public SinglyLinkedList() {
        this.size = 0;
    }

    //    constructor with single value
    public SinglyLinkedList(T data) {
        addFirst(data);
    }

    //    constructor with iterator
    public SinglyLinkedList(Iterator<T> data) {
        addAll(data);
    }

    // constructor with collection
    public SinglyLinkedList(Collection<T> c) {
        addAll(c);
    }
//    2. Getters

    //    get size
    public int getSize() {
        return this.size;
    }

    //     get First element
    public T peekFirst() {
        if (isEmpty()) {
            throw new RuntimeException("Empty List");
        }

        return this.head.getValue();
    }

    //    get last element
    public T peekLast() {
        if (isEmpty()) {
            throw new RuntimeException("Empty List");
        }

        return this.tail.getValue();
    }

    // get data at index
    public T getAtIndex(int index) {
//        check if list is empty
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Can not fetch element at index: " + index + " from an empty list");
        }
//        check if index is out of bound
        if (this.size < index) {
            throw new IndexOutOfBoundsException("Can not fetch element at index: " + index + " from a list of size: " + this.size);
        }

//        traverse through the node and till the desired index and return the value
        Node<T> current = this.head;
        int count = 0;
        while (count < index) {
            current = current.getNext();
            count++;
        }
        return current.getValue();
    }

    //     get index of first occurrence of data
    public int indexOf(T data) {
//         check if list is empty
        if (isEmpty()) {
            return -1;
        }

//        traverse through the node and return the index of first matching value
        Node<T> current = this.head;
        int index = 0;
        while (current != null) {
            if (current.getValue().equals(data)) {
                return index;
            }
            current = current.getNext();
        }
        return -1;
    }

    //    check if list contains the element
    public boolean contains(T data) {
        int index = indexOf(data);
        return index != -1;
    }

    //    is empty
    public boolean isEmpty() {
        return this.size == 0;
    }

//    3. Add elements

    //    Add to first element to the list
    private void addFirst(T data) {
        this.head = new Node<>(data);
        this.tail = this.head;
        this.size = 1;
    }

    //     Add all elements from a collection to the list
    public void addAll(Collection<T> c) {
        c.forEach(this::append);
    }

    //    Add all elements from an iterator to the list
    public void addAll(Iterator<T> i) {
        while (i.hasNext()) {
            this.append(i.next());
        }
    }

    //    Add element to the list
    public void add(T data) {
        append(data);
    }

    //    Add element to last index
    public void append(T data) {
//        check if list is empty
        if (isEmpty()) {
            addFirst(data);
            return;
        }
//        iterate to the last non null Node and add a new list
        Node<T> newNode = new Node<>(data);
        this.tail.setNext(newNode);
        this.tail = newNode;
        this.size++;
    }

    //    Add element to the begining of the list
    public void prepend(T data) {
//        check if list is empty
        if (isEmpty()) {
            addFirst(data);
            return;
        }
//      create a new head node
        Node<T> newRoot = new Node<>(data);
        newRoot.setNext(this.head);
        this.head = newRoot;
        this.size++;
    }

    //    Add element at an index to the list
    public void addAtIndex(int index, T data) {
//        check if index is out of bound
        if (isEmpty() && index > 0) {
            throw new IndexOutOfBoundsException("Can not add element at index: " + index + " to an empty list");
        }
        if (this.size < index) {
            throw new IndexOutOfBoundsException("Can not add element at index: " + index + " to a list of size: " + this.size);
        }

//        prepend to the list, if index is 0
        if (index == 0) {
            prepend(data);
            return;
        }

//        append to the list if index is last one
        if (index == this.size) {
            append(data);
            return;
        }

//        iterate over the list and add the node
        Node<T> current = this.head;

        int count = 0;
        while (count < index - 1) {
            current = current.getNext();
            count++;
        }
        Node<T> newNode = new Node<>(data);
        newNode.setNext(current.getNext());
        current.setNext(newNode);
        this.size++;
    }

//    Delete elements

    //    delete at an index
    public void deleteAtIndex(int index) {
//         check if index is out of bound
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Can not delete element at index: " + index + " from an empty list");
        }
        if (this.size < index) {
            throw new IndexOutOfBoundsException("Can not delete element at index: " + index + " from a list of size: " + this.size);
        }
//      delete first if index = 0
        if (index == 0) {
            deleteFirst();
            return;
        }

//        delete last if index is last
        if (index == this.size - 1) {
            deleteLast();
            return;
        }

//       iterate over the list, stop before index and delete the index node
        Node<T> current = this.head;
        int count = 0;

        while (count < index - 1) {
            current = current.getNext();
            count++;
        }

        current.setNext(current.getNext().getNext());
        this.size--;
    }

    //    delete first occurrence of an element
    public void delete(T data) {
//         check if list is empty
        if (isEmpty()) {
            return;
        }

//        check if data is at head node
        if (this.head.getValue().equals(data)) {
            this.head = this.head.getNext();
            this.size--;
            return;
        }

//         iterate through list and delete the first occurrence of node
        Node<T> current = this.head;
        while (current.getNext() != null) {
            if (current.getNext().getValue().equals(data)) {
                current.setNext(current.getNext().getNext());
                this.size--;
                if (current.getNext() == null) {
                    this.tail = current;
                }
                return;
            }
            current = current.getNext();
        }
    }

    //    delete first element from the list
    public T deleteFirst() {
        if (isEmpty()) {
            throw new RuntimeException("Can not remove element from an empty List");
        }
        T data = this.head.getValue();
        this.head = this.head.getNext();
        this.size--;
        if (isEmpty()) {
            this.tail = null;
        }
        return data;
    }

    //    delete last element from the list
    public T deleteLast() {
        if (isEmpty()) {
            throw new RuntimeException("Can not remove element from an empty List");
        }

        if (this.size == 1) {
            T data = this.head.getValue();
            this.head = null;
            this.tail = null;
            this.size--;
            return data;
        }

        Node<T> current = this.head;
        int count = 0;
        while (count < this.size - 2) {
            current = current.getNext();
            count++;
        }
        T data = current.getNext().getValue();
        current.setNext(null);
        this.tail = current;
        this.size--;
        return data;
    }

    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                T data = node.getValue();
                node = node.getNext();
                return data;
            }
        };
    }

//    Utility methods

    public void reverse() {
        if (isEmpty()) {
            return;
        }
        Node<T> previous = null;
        Node<T> current = this.head;
        Node<T> next;

        while (current != null) {
            next = current.getNext();
            current.setNext(previous);
            previous = current;
            current = next;
        }
        this.head = previous;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        Node<T> current = this.head;
        s.append("[");
        while (current.getNext() != null) {
            s.append(String.format("%s, ", current.getValue().toString()));
            current = current.getNext();
        }
        s.append(current.getValue().toString());
        s.append("]");
        return s.toString();
    }

    public void print() {
        if (isEmpty()) {
            System.out.println("Empty list");
            return;
        }

        Node<T> current = this.head;
        while (current.getNext() != null) {
            System.out.print(current.getValue() + " --> ");
            current = current.getNext();
        }
        System.out.println(current.getValue());
    }
}
