/* Name: NuestraLista.java
 * Author: Luis Colorado <luiscoloradourcola@gmail.com>
 * Date: 19 mar. 2019 15:53:02
 * Project: NuestraPrimeraCollection
 * Package: curso.java
 * Copyright: (C) 2019 LUIS COLORADO.  All rights reserved.
 */
package curso.java;

import java.util.Iterator;

/**
 * @author lcu
 *
 */
public class NuestraLista<E> implements Iterable<E> {
    final E[] m_losElementos;
   // final E[] m_items; No me deja crear otra lista

   // public NuestraLista(int cap2) { m_items = (E[]) new Object()[cap2]; }

    private class Iter implements Iterator<E> {
        int m_index = 0;
        @Override
        public boolean hasNext() {
            return m_index < m_losElementos.length;
        }
        @Override
        public E next() {
            if (!hasNext())
                throw new ArrayIndexOutOfBoundsException();
            return m_losElementos[m_index++];
        }
    }
    
    @SuppressWarnings( "unchecked" )
    public NuestraLista(int cap) {
        m_losElementos = (E[]) new Object[cap];
    }
    
    public E get(int i) {
        return m_losElementos[i];
    }
    
    public void set(int i, E val) {
        m_losElementos[i] = val;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iter();
    }
}
