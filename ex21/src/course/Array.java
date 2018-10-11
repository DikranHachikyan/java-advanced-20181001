package course;

/**
 *
 * @author wizard
 */
public class Array<T extends Comparable<T>> implements IArray<T> {
    private T [] elements;
    
    public Array( T [] elements){
        this.elements = elements;
    }
    
    @Override
    public T min(){
        if(elements.length == 0) return null;
        T value = elements[0];
        for( int i = 0; i < elements.length ; i++ ){
            if( elements[i].compareTo(value) < 0 ) value = elements[i];
        }
        return value;
    }
    
    @Override
    public T max(){
        if(elements.length == 0) return null;
        T value = elements[0];
        for( int i = 0; i < elements.length ; i++ ){
            if( elements[i].compareTo(value) > 0 ) value = elements[i];
        }
        return value;
    }
}
