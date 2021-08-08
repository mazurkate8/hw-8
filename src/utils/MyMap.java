package utils;

public interface MyMap<K, V> extends Iterable<V> {
    boolean put(K key, V value);
    V get(K key);
    boolean remove(K key);
    boolean clear();
    int size();
}
