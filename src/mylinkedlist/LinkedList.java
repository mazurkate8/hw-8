package mylinkedlist;


import mylinkedlist.structure.List;

import java.util.NoSuchElementException;
import java.util.Objects;


public class LinkedList<T> implements List<T> {
    static class Node <T> {
        T element;
        Node<T> next;
        public Node(T element) {
            this.element=element;
        }
    }
    private Node<T> first;
    private Node <T> last;
    private int size;


    public static <T> LinkedList<T> of(T... elements) {
       LinkedList<T> linkedList= new LinkedList<>();
       for ( T e:elements) {
           linkedList.add(e);
       }
       return  linkedList;
    }


    @Override
    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if (first == null) {
            first = last = newNode;

        } else {
            last.next = newNode;
            last=newNode;
        }
        size++;
    }

    private Node<T> getNodeByIndex(int index) {
        Node<T> current = first;
        for (int i = 0; i < index; i++) {
            current=current.next;
        }
        return current;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index,size);
        return getNodeByIndex(index).element;
    }


    @Override
    public T remove(int index) {
        Objects.checkIndex(index,size);
        T removedElement;
    if ( index==0) {
      removedElement = first.element;
      first=first.next;
      if(first==null)  {
          last=null;
      }
  } else {
        Node<T> prev=getNodeByIndex(index-1);
        removedElement = prev.next.element;
        prev.next=prev.next.next;
        if (index==size-1) {
            last = prev;
        }
    }
        size--;
        return  removedElement;
    }

    @Override
    public int size() {
       return size;
    }

    @Override
    public void clear() {
       first=last=null;
       size = 0;
    }
}
