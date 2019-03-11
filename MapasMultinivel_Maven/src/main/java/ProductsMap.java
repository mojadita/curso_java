import java.util.Map;
import java.util.TreeMap;

/**
 * Reference / Price (The essence of a Product , Value and Product or Price)
 * Here you can find Source for a CheckOut System with products.
 * @param <R> Reference Ex: P3456
 * @param <P> Price Ex: 500 €
 */
public class ProductsMap<R, P> extends TreeMap<R, P> {
    ProductsMap<R, P> p_parent;

    public ProductsMap(ProductsMap<R, P> parent ) {
        p_parent = parent ;
    }

    public ProductsMap<R, P> getP_parent() { return  p_parent;}
    public void setP_parent(ProductsMap<R, P> parent) {
        p_parent = parent;
    }
    @Override
    public P get(Object clave_de_busqueda) {
        P value = super.get(clave_de_busqueda);
        if(value == null && p_parent != null)
            return p_parent.get(clave_de_busqueda);
        return value;
    }
    @Override
    public String toString() {
        String s = "";
        for (Map.Entry<R, P> e : this.entrySet()) {
            s += e.getKey() + " =>" + e.getValue() + "\n";
        }
        return s;

    }

} /* EnD Of The File */
