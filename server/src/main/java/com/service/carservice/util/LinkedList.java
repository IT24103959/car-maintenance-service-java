package com.service.carservice.util;

class Link<T>{
    public T instance;
    public Link<T> next;

    public Link(T instance){
        this.instance = instance;
    }
}

public class LinkedList<T> {
    private Link<T> first;
    private int size;

    public LinkedList(){
        first = null;
        size = 0;
    }



    public void insert(T value){
        Link<T> newLink = new Link<T>(value);
        if(first == null){
            first = newLink;
        }else{
            Link<T> temp = first;
            while(temp.next != null){
                temp = temp.next;
            }
            temp.next = newLink;
        }
        size++;
    }


    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public void deleteByValue(T value){
        Link<T> temp = first;
        Link<T> prev = null;
        while(temp != null){
            prev = temp;
            if(temp.instance.equals(value)){
                prev.next = temp.next;
                size--;
                return;
            }
            temp = temp.next;
        }
    }


    public void destroy(){
        first = null;
        size = 0;
    }

}
