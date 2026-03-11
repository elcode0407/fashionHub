package fashiony;


public class Node<T>{
    Node<T> next;
    T data;

    public Node(T data){
        this.data = data;
        this.next = null;
    }
}