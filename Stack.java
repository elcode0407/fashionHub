package fashiony;

import java.util.ArrayList;

//Stack data structure implemented from our lectures, but using ArrayList to be able to resize automatically. 
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
            System.out.println("Nothing to reverse");
            return null;
        }
        T item = data.get(data.size()-1);
        data.remove(data.size()-1);
        System.out.println("Your last action was reversed");
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
    
    @Override
public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("Stack (top -> bottom):\n");

    for (int i = data.size() - 1; i >= 0; i--) {
        sb.append(data.get(i)).append("\n");
    }

    return sb.toString();
}
}
