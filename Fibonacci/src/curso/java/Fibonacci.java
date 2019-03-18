package curso.java;

import java.util.Iterator;

public class Fibonacci implements Iterable<Long>, Iterator<Long> {
    
    private long a = 0, b = 1;
    
    @Override
    public boolean hasNext() {
        return b <= a + b;
    }

    @Override
    public Long next() {
        long resul = a + b;
        a = b; b = resul;
        return resul;
    }

    @Override
    public Iterator<Long> iterator() {
        return this;
    }

}
