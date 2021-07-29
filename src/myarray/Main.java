package myarray;

public class Main {

    public static void main(String[] args) {
    MyArrayList<String> list = new MyArrayList<>();
    list.add("Катя");
    list.add("Максим");

    list.remove(1);
    System.out.println(list.size());
    list.clear();
    System.out.println(list.size());
    list.add("ssss");
    System.out.println(list.get(0));

    }

}
