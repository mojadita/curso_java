/**
 * 
 */
package course.java;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author lcu
 *
 */
public class TestMultilevelTreeMap {
    public static MultilevelTreeMap<String, Integer> map, map_hijo;
	@Before
	public void buildMap() {
		map = new MultilevelTreeMap<String, Integer>(null, "pepito");
		map_hijo = new MultilevelTreeMap<String, Integer>(map, "pepito");

		map.put("pepe", 26);
		map.put("juan", 23);
		map.put("pio", 12);
		map.put("pablo", 6);
		map_hijo.put("pepito", 1);
		map_hijo.put("juanito", 2);
		map_hijo.put("pio", 8);

	}
	
	@Test
	public void testParentWithEntry() {
	    assertEquals(new Integer(26), map.get("pepe"));
	}
	
	@Test
	public void testParentWithoutEntry() {
	    assertNull(map.get( "ambrosio" ));
	}
	
	@Test
	public void testChildWithEntryAndInParent() {
	    assertEquals(new Integer(8), map_hijo.get( "pio" ));
	}
	
	@Test
	public void testChildWithEntryAndNotInParent() {
	    assertEquals(new Integer(1), map_hijo.get( "pepito" ));
	}
	
	@Test
	public void testChildWithoutEntryAndOkInParent() {
	    assertEquals(new Integer(23), map_hijo.get( "juan" ));
	}
	
	@Test
	public void testChildWithoutEntryAndNorInParent() {
	    assertNull(map_hijo.get( "ambrosio" ));
	}
}
