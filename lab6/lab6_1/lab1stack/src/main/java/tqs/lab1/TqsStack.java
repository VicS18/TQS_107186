package tqs.lab1;

import java.util.NoSuchElementException;
import java.util.LinkedList;

public class TqsStack<T>{

    private LinkedList<T> stack;

    public TqsStack(){
        stack = new LinkedList<T>();
    }

    public T pop() throws NoSuchElementException{
        return stack.pop();
    }

    public void push(T elem){
        stack.add(elem);
    }

    public int size(){
        return stack.size();
    }

    public T peek(){
        return stack.getLast();
    }

    public boolean isEmpty(){
        return stack.isEmpty();
    }
}