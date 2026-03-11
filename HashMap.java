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
public class HashMap<K,V> {
    private ArrayList<LinkedList<HTEntry<K,V>>> table;
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

    public void put(K key, V value){
        int hashIndex = hash(key);

        LinkedList<HTEntry<K,V>> data = table.get(hashIndex);

        for(HTEntry<K,V> entry: data){
            if(entry.key.equals(key)){
                entry.value = value;
            }
        }

        data.append(new HTEntry<>(key, value));
        size++;
    }
}
