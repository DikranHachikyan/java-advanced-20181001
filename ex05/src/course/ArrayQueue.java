package course;

import java.util.AbstractQueue;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayQueue<E> extends AbstractQueue<E> {
    private Object [] elements;
    private int head = 0;
    private int tail = 0;
    private int count = 0;
    private int modcount = 0; /*брояч на промени в данните на опашката */
    
    public ArrayQueue( int capacity){
        super();
        elements = new Object[capacity];
    }
    
    @Override
    public boolean offer( E newElement){
        if( newElement == null ) return false;
        if( count < elements.length ){
            elements[tail] = newElement;
            tail = (tail + 1) % elements.length;
            count++;
            modcount++;
            return true;
        }
        return false;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public E peek(){
        if( count == 0 ) return null;
        return (E)elements[head];
    }
    
    @Override
    public E poll(){
        if( count == 0 ) return null;
        E ret = peek();
        elements[head] = null;
        head = ( head + 1) % elements.length;
        count--;
        modcount++;
        return ret;
    }
    
    @Override
    public int size(){
        return count;
    }
    
    @Override
    public Iterator<E> iterator(){
        return new QueueIterator();
    }
    
    private class QueueIterator implements Iterator<E>{
        private int offset = 0;
        private int expectedModeCount = modcount;
        
        public QueueIterator(){
            super();
        }
        
        @Override
        public boolean hasNext(){
            if( modcount != expectedModeCount )
                throw new ConcurrentModificationException();
            return offset < count;
        }
        
        @Override
        public E next(){
            if( !hasNext() ) throw new NoSuchElementException();
            @SuppressWarnings("unchecked")
            E ret =(E) elements[ (head + offset) % elements.length ];
            offset++;
            return ret;
        }
    }
}
