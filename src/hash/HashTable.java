package hash;


import java.util.Random;

public class HashTable implements SymbolTable

{
    private Bucket[] bucketArray;        // Tableau contenant les paires "clé-valeur"
    private int nbrObject = 0;           // Nombre d'éléments insérés
    private CollisionManagement manager; // Gestionnaire de collision
    private int a, b;
    private int p;

    public HashTable(int size)
    {
        this(size, new LinearProbing());
    }

    public HashTable(int size, CollisionManagement manager)
    {
        this.manager = manager;
        bucketArray = new Bucket[size];
        Random generator = new Random();
        p = 1073676287;
        a = (Math.abs(generator.nextInt()) % p)  + 1;
        b = Math.abs(generator.nextInt()) % p;
    }

    public void put(int key, Object value) throws HashTableException
    {
        if(bucketArray.length == nbrObject) // D'abord vérifier s'il reste de la place

            throw new HashTableException("Table is full.");
        int index = getBucketIndex(key); // L'emplacement de la paire "clé-valeur"
        boolean inc = true;
        if(bucketArray[index] == null) // Si l'emplacement est vide...
            bucketArray[index] = new Bucket(new Entry(key, value)); // ... alors on y place la paire !
        else // Si l'emplacement est déjà occupé ou libre...
        {
            inc = bucketArray[index].isFree(); // On incrémente que dans le cas où la case est libre
            bucketArray[index].setValue(value); // On remplace la valeur (choix d'implémentation)
        }
        if(inc)
            nbrObject++;

    }


    public Object get(int key) throws HashTableException

    {

        if(nbrObject == 0) // Si la table est vide, cela ne sert à rien de continuer.

            throw new HashTableException("Table vide.");



        Bucket bucket = bucketArray[getBucketIndex(key)]; // Case dans laquelle devrait se trouver la clé



        if(bucket != null && !bucket.isFree()) // Si la case est occupée, on a trouvé notre clé !

            return bucket.getValue();

        else

            throw new HashTableException("Clé non trouvée");

    }


    public void remove(int key) throws HashTableException

    {

        if(nbrObject == 0) // Si la table est vide, cela ne sert à rien de continuer

            throw new HashTableException("Table vide.");



        Bucket delete = bucketArray[getBucketIndex(key)]; // La case à nettoyer



        if(delete == null || delete.isFree()) // Si la case est vide ou déjà nettoyée, on peut s'arrêter

            throw new HashTableException("Clée non trouvée");



        delete.clean();

        nbrObject--;

    }
    public boolean contains(int key)

    {

        Bucket bucket = bucketArray[getBucketIndex(key)];

        return (bucket != null && !bucket.isFree());

    }


    public int size()
    {
        return nbrObject;
    }

    private int hash(String s) {
        int h = 0;
        int p = 11;
        for (int i = 0; i < s.length(); i++) // On parcourt tous les caractères
        {
            h = (h << 5) | (h >>> 27);
            h += (int) s.charAt(i);
        }
        return (Math.abs(h) % p) % bucketArray.length;
    }

    private int getBucketIndex(int key)
    {
        int h = hash(new Integer(key).toString()); // Calcul du hach code
        int index = h; // index sera l'indice de la clé
        for(int i = 0 ; bucketArray[index] != null && bucketArray[index].getKey() != key ; i++)

            index = manager.nextIndex(h, i) % bucketArray.length; // Obtention de la prochaine case
        return index;

    }


}