import java.util.TreeMap;
import java.util.Map.Entry;



public class TonyMapa<
        P,
        V> extends TreeMap<P, V> {


    private static final long serialVersionUID = 4719639307781153135L;

    TonyMapa<P, V> m_parent;

    /**
     * Constructor
     */


    public TonyMapa( TonyMapa<P, V> parent ) {

        m_parent = parent;
    }
    /* Esta parte es poco entendible ^^ */

    /**
     * return
     *
     */

    public TonyMapa<P, V> getM_parent() { return m_parent; }

    /**
     * parent to set
     */

    public void setM_parent( TonyMapa<P, V> parent ) { m_parent = parent; }

    @Override
    public V get( Object clave_de_busqueda ) {

        V value = super.get( clave_de_busqueda );
        if ( value == null && m_parent != null )
            return m_parent.get( clave_de_busqueda );
        return value;
    }

    @Override
    public String toString() {

        String s = "";
        for ( Entry<P, V> e: this.entrySet() ) {
            s += e.getKey() + " =>" + e.getValue() + "\n";
        }
        return s;

    }



}
