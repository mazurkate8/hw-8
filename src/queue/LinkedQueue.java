package queue;

import utils.Queue;

import java.util.Objects;

public class LinkedQueue<T> implements Queue<T> {

    static final class Node<T> {
        private T element;
        private Node<T> next;

        static <T> Node<T> valueOf(T element) {
            return new Node<>(element);
        }

        private Node(T element) {
            this.element = element;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public void add(T element) {
        Node<T> newNode = Node.valueOf(element);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public T poll() {
        if (head != null) {
            T element = head.element;
            head = head.next;
            if (head == null) {
                tail = null;
            }
            size--;
            return element;
        } else {
            return null;
        }
    }

    public int size() {
        return size;
    }

    public T peek() {
        T element;
        if (head != null) {
            element = head.element;
            return element;

        }
        return null;
    }

    public void clear() {
        head = tail = null;
        size = 0;
    }

    public T remove(int index) {
        Objects.checkIndex(index, size);
        T removedElement = null;
        if (index == 0) {
            removedElement = head.element;
            head = head.next;
        } else if (head == null) {
            tail = null;
        }
        size--;
        return removedElement;
    }
}

