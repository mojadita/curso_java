package curso.java;

import java.util.Iterator;

public class NewIterator implements Iterable<Long>, Iterator<Long> {


    private long z = 0,w = 1;

    @Override
    public boolean hasNext() {

        return z <= z + 1;
    }

    @Override
    public Long next() {

        long resul = z + w;
        z = w;
       w = resul;
        return resul;
    }

    @Override
    public Iterator<Long> iterator() {

        return this;
    }

}
