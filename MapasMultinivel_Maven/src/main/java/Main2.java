import jdk.internal.org.objectweb.asm.tree.analysis.Value;

public class Main2 {
	public static void p(String s) {
		System.out.println(s);
	}

	/* Otra anotación tipo ShortCut */

	public static void a(String r) {
		System.out.println(r);
	}

	public static void main(String[] args) {

		TonyMapa<String, Number> globo = new TonyMapa<String, Number>(null);
		TonyMapa<String, Number> globo_hijo = new TonyMapa<String, Number>(globo);


		/*  meto cosas en el mapa padre*/
		System.out.println("=============================");
		System.out.println("Nuestra Cartera de Clientes:");
		System.out.println("=============================");

		/* Mapa Padre Son los CEO de la Empresa */
		globo.put("Luis Colorado",100000);
		globo.put("Laura Pausini", 1000);
		globo.put("Enrique Iglesias",null);
		globo.put("Antonio Nicolau Batle", 40000);

		a("Luis Colorado");
		a("Laura Pausini");
		a("Enrique Iglesias");
		a("Antonio Nicolau Batle");
		a("=============================");
		a("Cartera Hijo");
		a("=============================");
		a("Carla Conquistadora");
		a("Rebeca Rodriguez");
		a("=============================");

		/* Introducimos en La Cartera hijo */
		globo_hijo.put("Carla Conquistadora",0);
		globo_hijo.put("Rebeca Rodriguez",0);
 /*Consultamos en la cartera a ver que saldo tienen nuestros clientes */
		a("El saldo Actual del Cliente (\"Antonio Nicolau Batle\") Es de ==> " + globo.get("Antonio Nicolau Batle") + "$");
		a("El saldo Actual del Cliente (\"Rebeca Rodriguez\")      Es de ==> " + globo_hijo.get("Rebeca Rodriguez") + "$");

		/*globo.put("Agua",2);
		globo.put("Carne",1);
		globo.put("Galletas",3);
		globo.put("pescado",2);
		globo.put("",1);
		globo.put("Cafe",1);
		globo.put("Fideos_Transparentes",3);
		globo.put("Papel Higienico",1);
		globo.put("Refrescos",6); */
		/* Ahora la lista de soporte o mapa hico para los productos de antojo */
		/*globo_hijo.put("Smirnof",2);
		globo_hijo.put("Danones_De_Copa",3);
		globo_hijo.put("Platanos",3);
		a("globo.get(\"Leche\")      <== " + globo.get("Leche"));
		a("globo.get(\"Danones_De_Copa\")      <== " + globo.get("")+ "===>" + globo_hijo.get("Danones_De_Copa"));
		a("globo.get(\"Agua\")      <== " + globo.get("Agua")); */
	/*

		
		map.put("pepe", 26);
		map.put("juan", 23);
		map.put("pio", 12);
		map.put("pablo", 6);
		map_hijo.put("pepito", 1);
		map_hijo.put("juanito", 2);
		map_hijo.put("pio", 8);
		a("map.get(\"pio\")         <== " + map.get("pio")+ map.get(Value));
		a("map.get(\"pablo\")       <== " + map.get("pablo"));
		a("map.get(\"pepito\")      <== " + map.get("pepito"));
		a("map_hijo.get(\"pepito\") <== " + map_hijo.get("pepito"));
		a("map_hijo.get(\"pio\")    <== " + map_hijo.get("pio"));
		a("map_hijo.get(\"alfonso\")<== " + map_hijo.get("alfonso"));
		
		*/

	/* Llamadas al Mapa Productos y Introducción de datos en el mismo */
		ProductsMap<String, Number> productos = new ProductsMap<String, Number>(null);
		ProductsMap<String, Number> productos_hijo = new ProductsMap<String, Number>(null);
		System.out.println("=============================");
		System.out.println("Lista de Productos y Su Precio");
		System.out.println("=============================");

		productos.put("Nike Air",190);
		productos.put("Gorra Nike Blanca",30);
		productos.put("Sudadera Nike Edition Criminal Crew",75);
		productos_hijo.put("Bandolera Nike",15);
		productos_hijo.put("",0);

		System.out.println("PRODUCTOS");
		System.out.println("");
		p("\"Nike Air\")                            ====================>" + productos.get("Nike Air")+"$");
		p("\"Sudadera Nike Edition Criminal Crew\") ====================>" + productos.get("Sudadera Nike Edition Criminal Crew")+"$");
		p("\"Gorra Nike Blanca\")                   ====================>" + productos.get("Gorra Nike Blanca")+"$");
		p("\"Nike Air\")                            ====================>" + productos.get("Nike Air")+"$");
		p("\"Bandolera Nike\")                      ====================>" + productos.get("Bandolera Nike")+"$");
		p("\"Bandolera Nike\")                      ====================>" + productos_hijo.get("Bandolera Nike")+"$");
		System.out.println("============================");



		
	} /* EnD Of tHe Main2 */
}
