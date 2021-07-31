package stack;

//package OOP;
public interface Stack<E> {

	void push(E item);
	E pop();
	void peek();
	void print();
    boolean isEmpty();
    boolean isFull();
    void clear();
}