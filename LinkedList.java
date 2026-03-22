package fashiony;

import java.util.Iterator;

//LinkedList implemented from our class lectures, implements Iterable to be able to iterate thorugh data easily.
public class LinkedList<T>  implements Iterable<T>{
    Node<T> head;
    
    int length;

    public LinkedList(){
        this.head = null;
        length = 0;
    }
    // ---------------- INSERT ----------------
    public void append(T data) {
        if (head == null) {
            this.head = new Node<>(data);
            this.length++;
        } else {
            Node<T> curr = this.head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = new Node<>(data);
            this.length++;
        }
    }
    
    // ---------------- PRINT ----------------
    public void print() {
        if (head == null) {
            return;
        }

        Node<T> curr = this.head;

        while (curr.next != null) {
            System.out.print(curr.data + " -> ");
            curr = curr.next;
        }

        System.out.println(curr.data);
    }

    // ---------------- REMOVE VALUE ----------------
    public T removeHead() {
        if(head == null) return null;
        T data = head.data;
        head = head.next;
        length--;
        return data;
    }
    
    // ---------------- SEARCH ----------------
    public boolean search(T target) {
        if (this.head == null) {
            return false;
        }

        Node<T> curr = this.head;

        while (curr != null) {
            if (curr.data.equals(target)) {
                return true;
            }
            curr = curr.next;
        }

        return false;
    }

    public boolean isEmpty() {
        return this.head == null;
    }
    public int size() {
        return this.length;
    }

    // Written by GenAI
    @Override
    public Iterator<T> iterator(){
        return new Iterator<T>() {
            Node<T> current = head;

            public boolean hasNext(){
                return current != null;
            }

            public T next(){
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }
}