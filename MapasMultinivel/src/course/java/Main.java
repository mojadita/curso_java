package course.java;

public class Main {
	public static void main(String[] args) {
		MultilevelTreeMap<String, Integer> map = new MultilevelTreeMap<String, Integer>();
		for (int i = 0; i < args.length; i++) {
			Integer contador = map.get(args[i]);
			if (contador == null) 
				contador = new Integer(0);
			map.put(args[i], contador + 1);
		}
		System.out.println(map);
	}
}
