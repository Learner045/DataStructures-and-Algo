public interface List<T extends Comparable<T>> {

    void insert(T data);
    void remove(T data);
    void printList();
    int getSize();
}
