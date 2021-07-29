import java.util.Arrays;

public class MyArrayList<T> {
    private final int DEFAULT_CAPACITY = 10;
    private final int CUT_RATE = 4;
    private Object[] elementData = new Object[DEFAULT_CAPACITY];
    private int modCount = 0;


    public void add(T item) {
        if(modCount == elementData.length-1)
            resize(elementData.length*2);
        elementData[modCount++] = item;
    }

    public T get(int index) {
        return (T) elementData[index];
    }

    public void remove(int index) {
        for (int i = index; i< modCount; i++)
            elementData[i] = elementData[i+1];
        elementData[modCount] = null;
        modCount--;
        if (elementData.length > DEFAULT_CAPACITY && modCount < elementData.length / CUT_RATE)
            resize(elementData.length/2);
    }

    public int size() {
        return modCount;
    }

    private void resize(int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(elementData, 0, newArray, 0, modCount);
        elementData = newArray;
    }

    public void clear() {
        modCount++;
        Arrays.fill(elementData, null);
        modCount = 0;
    }

}