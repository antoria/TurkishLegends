package hash;

public interface SymbolTable

{
    void put(int key, Object value) throws HashTableException;

    Object get(int key) throws HashTableException;

    void remove(int key) throws HashTableException;

    boolean contains(int key);

    int size();

}