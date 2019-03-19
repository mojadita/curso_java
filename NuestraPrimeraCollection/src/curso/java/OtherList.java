package curso.java;



import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;



public class OtherList<E> implements Iterable<E> {

    final E[] productos;


    public OtherList(E[] productos) {
        this.productos = productos;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iter();
    }

    @Override
    public void forEach(Consumer<? super E> action) {

    }

    @Override
    public Spliterator<E> spliterator() {
        return null;
    }

    private class Iter implements Iterator<E> {
        int m_index = 0;
        @Override
        public boolean hasNext() {
            return m_index < productos.length;
        }
        @Override
        public E next() {
            if (!hasNext())
                throw new ArrayIndexOutOfBoundsException();
            return productos[m_index++];
        }
    }
    public OtherList(int cap) {
        productos = (E[]) new Object[cap];
    }
    /* Copy/paste */
    public E get(int i) {
        return productos[i];
    }

    public void set(int i, E val) {
        productos[i] = val;
    }
}
