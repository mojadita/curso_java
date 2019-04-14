/**
 * 
 */
package curso.java.recursive_data;


/**
 * @author lcu
 *
 */
public class Node<T> {
    private T data;
    private Node<T> next;
    
    /**
     * @return the {@code int} {@code data}.
     */
    public T getData() {
        return data;
    }
    
    /**
     * @param data the {@code int} {@code data} to set
     */
    public void setData( T data ) {
        this.data = data;
    }
    
    /**
     * @return the {@code Nodo} {@code next}.
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * @param next the {@code Nodo<T>} {@code next} to set
     */
    public void setNext( Node<T> next ) {
        this.next = next;
    }    

}
