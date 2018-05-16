package hash;

public class LinearProbing implements CollisionManagement

{
    public int nextIndex(int h, int i)

    {

        return h + i;

    }
}