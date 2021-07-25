package implementation.JLinkedList.doublyLinkedList;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DoubleNode<T> {
    private T data;
    private DoubleNode<T> previous;
    private DoubleNode<T> next;


    public DoubleNode(T value) {
        this.data = value;
    }

    public T getData() {
        return this.data;
    }

    public DoubleNode<T> getPrevious() {
        return this.previous;
    }

    public DoubleNode<T> getNext() {
        return this.next;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setPrevious(DoubleNode<T> previous) {
        this.previous = previous;
    }

    public void setNext(DoubleNode<T> next) {
        this.next = next;
    }
}
