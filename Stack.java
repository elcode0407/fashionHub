package fashiony;

import java.util.ArrayList;

public class Stack<T>{
    private ArrayList<T> data;

    public Stack(){
        data = new ArrayList<>();
    }

    public void push(T item){
        data.add(item);
    }

    public T pop(){
        if(data.isEmpty()){
            return null;
        }
        // delete element that was added last
        T item = data.get(data.size()-1);
        data.remove(data.size()-1);
        return item;
    }

    public T peek(){
        if(data.isEmpty()){
            return null;
        }
        return data.get(data.size()-1);
    }
    
    public boolean isEmpty(){
        return data.isEmpty();
    }

    public int size(){
        if(!data.isEmpty()){
            return data.size();
        }
        return 0;
    }
    
}
