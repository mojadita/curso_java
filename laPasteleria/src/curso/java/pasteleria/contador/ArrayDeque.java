package curso.java.pasteleria.contador;

import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Deque;

public abstract class ArrayDeque<E> extends AbstractCollection<E> implements Deque<E>, Serializable {

    /*objetivos*/
    /* Lista de  productos */
    /* Lista de Clientes , turnos y colas , atender con la pastelera, comprar el producto, salir */
    /* falta agregar cosas al array, implementar .add o .remove() a la Cola */


    /* Otros Proyectos MiBar, MiRest , MiShop*/


    public String m_name;

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public int getM_price() {
        return m_price;
    }

    public void setM_price(int m_price) {
        this.m_price = m_price;
    }

    public Boolean getM_stock() {
        return m_stock;
    }

    public void setM_stock(Boolean m_stock) {
        this.m_stock = m_stock;
    }

    public int getM_id() {
        return m_id;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
    }

    public int m_price;
    public Boolean m_stock;
    public int m_id;

    public ArrayDeque(String name,int precio,int id){
        m_name = name;
        precio = m_price;
        id = m_id;

    }

   /*public <T> T[] toArray(T[] a) */


    @Override
    public boolean add(E e) {
        return super.add(e);
    }

    @Override
    public void addFirst(E e) {

    }
}
