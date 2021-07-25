package implementation.JLinkedList.doublyLinkedList;

import java.util.Collection;
import java.util.Iterator;

public class DoublyLinkedList<T> {
    private int size;
    private DoubleNode<T> head;
    private DoubleNode<T> tail;

    public DoublyLinkedList() {
        this.size = 0;
    }

    public DoublyLinkedList(T data) {
        addFirstNode(data);
    }

    public DoublyLinkedList(Collection<T> collection) {
        addAll(collection);
    }

    public DoublyLinkedList(Iterator<T> iterator) {
        addAll(iterator);
    }

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public T peekFirst() {
        if (isEmpty()) {
            throw new RuntimeException("No elements in the list");
        }
        return this.head.getData();
    }

    public T peekLast() {
        if (isEmpty()) {
            throw new RuntimeException("No elements in the list");
        }
        return this.tail.getData();
    }

    public T getAtIndex(int index) {
        if (isEmpty()) {
            throw new RuntimeException("No elements in the list");
        }

        if (index == 0) {
            return peekFirst();
        }

        if (index == this.size - 1) {
            return peekLast();
        }

        int count = 0;
        DoubleNode<T> current = head;
        while (count < index) {
            count++;
            current = current.getNext();
        }
        return current.getData();
    }

    public int getIndexOf(T value) {
        if (isEmpty()) {
            return -1;
        }

        if (this.tail.getData().equals(value)) {
            return this.size - 1;
        }

        DoubleNode<T> current = this.head;
        int index = 0;
        while (current != null) {
            if (current.getData().equals(value)) {
                return index;
            }
            current = current.getNext();
            index++;
        }
        return -1;
    }

    public boolean contains(T data) {
        return getIndexOf(data) != -1;
    }

    public void add(T data) {
        append(data);
    }

    public void append(T data) {
        if (isEmpty()) {
            addFirstNode(data);
            return;
        }

        DoubleNode<T> newNode = new DoubleNode<>(data);
        newNode.setPrevious(this.tail);
        this.tail.setNext(newNode);
        this.tail = newNode;
        this.size++;
    }

    public void prepend(T data) {
        if (isEmpty()) {
            addFirstNode(data);
            return;
        }

        DoubleNode<T> newNode = new DoubleNode<>(data);
        this.head.setPrevious(newNode);
        newNode.setNext(this.head);
        this.head = newNode;
        this.size++;
    }

    public void addAtIndex(int index, T data) {
        if (isEmpty() && index > 0 || index > this.size) {
            throw new IndexOutOfBoundsException(String.format("can not add element at index: %d for the list of size: %d", index, this.size));
        }

        if (index == 0) {
            prepend(data);
            return;
        }

        if (index == this.size) {
            append(data);
            return;
        }


        DoubleNode<T> current;
        DoubleNode<T> newNode = new DoubleNode<>(data);
        if (index < this.size / 2) {
            int count = 1;
            current = this.head;
            while (count < index - 1) {
                count++;
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            newNode.setPrevious(current);
            current.getNext().setPrevious(newNode);
            current.setNext(newNode);
        } else {
            current = this.tail; // 5
            int count = this.size - 2; // 3
            while (count > index) {
                current = current.getPrevious();
                count--;
            }
            newNode.setNext(current);
            newNode.setPrevious(current.getPrevious());
            current.getPrevious().setNext(newNode);
            current.setPrevious(newNode);
        }
        this.size++;

    }

    public void addAll(Collection<T> collection) {
        collection.forEach(this::append);
    }

    public void addAll(Iterator<T> iterator) {
        iterator.forEachRemaining(this::append);
    }

    public T deleteFirst() {
        if (isEmpty()) {
            throw new RuntimeException("can not delete element from empty list");
        }
        T data = this.head.getData();
        this.size--;
        if (isEmpty()) {
            this.tail = null;
            this.head = null;
            return data;
        }
        this.head = this.head.getNext();
        this.head.setPrevious(null);
        return data;
    }

    public T deleteLast() {
        if (isEmpty()) {
            throw new RuntimeException("can not delete element from empty list");
        }

        T data = this.tail.getData();
        this.size--;
        if (isEmpty()) {
            this.tail = null;
            this.head = null;
            return data;
        }
        this.tail = this.tail.getPrevious();
        this.tail.setNext(null);

        if (isEmpty()) {
            this.head = null;
        }
        return data;
    }

    public void deleteAtIndex(int index) {
        if (isEmpty()) {
            throw new RuntimeException("can not delete element from empty list");
        }

        if (index == 0) {
            deleteFirst();
            return;
        }

        if (index == this.size - 1) {
            deleteLast();
            return;
        }

        int count = 0;
        DoubleNode<T> current = this.head;
        while (count < index) {
            count++;
            current = current.getNext();
        }
        current.getPrevious().setNext(current.getNext());
        current.getNext().setPrevious(current.getPrevious());

        if (isEmpty()) {
            this.tail = null;
        }
        this.size --;
    }

    public void delete(T value) {
        if (isEmpty()) {
            throw new RuntimeException("can not delete element from empty list");
        }
        int index = getIndexOf(value);
        deleteAtIndex(index);

    }

    public Iterator<T> iterator() {
        return new Iterator<>() {
            DoubleNode<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T data = current.getData();
                current = current.getNext();
                return data;
            }
        };
    }

    public void print() {
        if (isEmpty()) {
            System.out.println("Empty List");
            return;
        }

        DoubleNode<T> current = this.head;
        while (current.getNext() != null) {
            System.out.printf("%s --> ", current.getData());
            current = current.getNext();
        }
        System.out.println(current.getData());
    }

    public String toString() {
        if (isEmpty()) {
            return null;
        }

        StringBuilder s = new StringBuilder();
        DoubleNode<T> current = this.head;
        s.append("[");
        while (current.getNext() != null) {
            s.append(String.format("%s, ", current.getData().toString()));
            current = current.getNext();
        }
        s.append(String.format("%s]", current.getData()));
        return s.toString();
    }

    public Object[] toArray() {
        Object[] arr = new Object[this.size];
        if (isEmpty()) {
            return arr;
        }
        DoubleNode<T> current = head;
        int index = 0;
        while (current != null) {
            arr[index] = current.getData();
            index ++;
            current = current.getNext();
        }
        return arr;
    }

    private void addFirstNode(T data) {
        DoubleNode<T> node = new DoubleNode<>(data);
        this.head = node;
        this.tail = node;
        this.size++;
    }
}
