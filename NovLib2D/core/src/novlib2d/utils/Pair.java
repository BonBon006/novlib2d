package novlib2d.utils;

public class Pair<K,V> {
    private final K k;
    private final V v;

    public Pair(K k, V v) {
        this.k = k;
        this.v = v;
    }

    public K getVal_K() { return k; }
    public V getVal_V() { return v; }
}
