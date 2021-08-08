package utils;


public interface Queue<T> {
    void add(T element);
    T poll();
    int size();
}
