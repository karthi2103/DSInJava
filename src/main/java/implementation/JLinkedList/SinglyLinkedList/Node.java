package implementation.JLinkedList.SinglyLinkedList;

public class Node<T> {
    private T value;
    private Node<T> next;

    public Node(T value){
        this.value = value;
        this.next = null;
    }

    public Node(){
        this.value = null;
    }

    public T getValue(){
        return value;
    }

    public Node<T> getNext(){
        return next;
    }

    public void setValue(T value){
        this.value = value;
    }

    public void setNext(Node<T> next){
        this.next = next;
    }
}
