package hash;

public class Entry

{

    private int key;

    private Object value;



    public Entry(int key, Object value)

    {

        this.key = key;

        this.value = value;

    }



    // Accesseurs

    public int getKey()      { return key;   }

    public Object getValue() { return value; }



    // Mutateurs

    public void setValue(Object value) { this.value = value; }

}