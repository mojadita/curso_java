package curso.java;


import java.util.Iterator;

public abstract class OtherList2<E> implements Iterable<E> {

    final E[] items;

    protected OtherList2(E[] items) {
        this.items = items;
    }
    @Override
    public Iterator<E> iterator() {
        return null;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int id = 0;

    @Override
    public String toString() {
        return super.toString();
    }
    public static void main(String[] args) {

        System.out.println(super.toString());
        System.out.println(items);
        items.getClass();





    }


}
