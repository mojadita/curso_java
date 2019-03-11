package course.java;

import java.util.Map.Entry;
import java.util.TreeMap;

public class MultilevelTreeMap<K, V> extends TreeMap<K, V> {

	private static final long serialVersionUID = 4719639307781153135L;
	
	MultilevelTreeMap<K, V> m_parent;
	String m_name;
	
	/**
	 * This constructor is the unique contructor and requires to specify
	 * @param parent
	 * @param nombre TODO
	 */
	public MultilevelTreeMap(MultilevelTreeMap<K, V> parent, String nombre) {
		m_parent = parent;
		m_name = nombre;
	}
	

	/**
	 * @return the parent map.
	 */
	public MultilevelTreeMap<K, V> getParent() {
		return m_parent;
	}


	/**
	 * @param parent the parent to set
	 */
	public void setParent(MultilevelTreeMap<K, V> parent) {
		m_parent = parent;
	}


	/* (non-Javadoc)
	 * @see java.util.TreeMap#get(java.lang.Object)
	 */
	@Override
	public V get(Object clave_de_busqueda) {
		V value = super.get(clave_de_busqueda); // lo buscamos en este mapa.
		if (value == null && m_parent != null) // si no existe en este mapa && hay un parent
			return m_parent.get(clave_de_busqueda);
		return value;
	}
	
	@Override
	protected void finalize() {
	    System.out.println("Soy el map \"" + m_name + "\" y me están aniliquilando... 8(");
	}


	@Override
	public String toString() {
		String s = "";
		for(Entry<K, V> e: this.entrySet()) {
			s += e.getKey() + " => " + e.getValue() + "\n";
		}
		return s;
	}
}
