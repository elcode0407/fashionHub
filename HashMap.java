package fashiony;

import java.util.ArrayList;

class HTEntry<K, V>{
    K key;
    V value;

    public HTEntry(K key, V value){
        this.key = key;
        this.value = value;
    }
}

//HashMap chaining type, uses custom implemented linked list and generic entries, with ability to resize as well.
public class HashMap<K,V> {
    public  ArrayList<LinkedList<HTEntry<K,V>>> table;
    private int size;
    private int capacity;

    public HashMap(int capacity){
        this.capacity = capacity;
        this.table = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            table.add(new LinkedList<>());
        }
        this.size = 0;
    }

    private int hash(K key){
        return Math.abs(key.hashCode()) % capacity;
    }

    public void resize(){
        ArrayList<LinkedList<HTEntry<K,V>>> table2 = new ArrayList<>(table);
        capacity *= 2;
        table = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            table.add(new LinkedList<>());
        }
        size = 0;
        for (int i = 0; i < table2.size(); i++) {
            Node<HTEntry<K,V>> curr = table2.get(i).head;
            while (curr != null){   
                put(curr.data.key, curr.data.value);
                curr = curr.next;
            }
        }
    }

    public V get(K key){
        if(!table.isEmpty()){
            Node<HTEntry<K,V>> curr = table.get(hash(key)).head;
            while(curr!=null){
                if(curr.data.key.equals(key)){
                    return curr.data.value;
                }
                curr = curr.next;
            }
        }
        return null;
    }

    public void put(K key, V value){
        if ((double) size / capacity >= 0.75) {
            resize();
        }
        int hashIndex = hash(key);

        LinkedList<HTEntry<K,V>> data = table.get(hashIndex);

        for(HTEntry<K,V> entry: data){
            if(entry.key.equals(key)){
                entry.value = value;
                return;
            }
        }

        data.append(new HTEntry<>(key, value));
        size++;
    }

    //GenAI optimized toString to 
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        for (LinkedList<HTEntry<K, V>> bucket : table) {
            Node<HTEntry<K, V>> curr = bucket.head;

            while (curr != null) {
                sb.append(curr.data.key)
                .append(" -> ")
                .append(curr.data.value)
                .append("\n");

                curr = curr.next;
            }
        }

        return sb.toString();
    }
}
