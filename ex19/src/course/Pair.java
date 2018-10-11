package course;

/**
 *
 * @author wizard
 */
public class Pair<K,V> {
    private K key;
    private V value;
    
    public Pair(K key, V value){
        super();
        setKey(key);
        setValue(value);
    }
    
    public Pair(){
        this(null,null);
    }
    
    public void setKey(K key){
        this.key = key;
    }
    
    public void setValue(V value){
        this.value = value;
    }
    
    public K getKey(){
        return key;
    }
    
    public V getValue(){
        return value;
    }
    
    
}
