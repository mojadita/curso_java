import java.util.Map.Entry;
import java.util.TreeMap;

public class MultilevelTreeMap<K, V> extends TreeMap<K, V> {

	private static final long serialVersionUID = 4719639307781153135L;

	@Override
	public String toString() {
		String s = "";
		for(Entry<K, V> e: this.entrySet()) {
			s += e.getKey() + " => " + e.getValue() + "\n";
		}
		return s;
	}
}
