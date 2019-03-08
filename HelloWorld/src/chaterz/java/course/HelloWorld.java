package chaterz.java.course;

public class HelloWorld {
	
	public static void print_state(Bombilla b) {
		System.out.println(b + ".isEncendido() == " + b.isEncendido());
	}

	public static void print_state(Bombilla... bombillas) {
		for (Bombilla b : bombillas)
			print_state(b);
		System.out.println();
	}

	public static void main(String[] args) {
		System.out.println("Buenas!!");
		Bombilla b1 = new Bombilla("b1");
		Bombilla b2 = new Bombilla("b2");
		print_state(b1, b2);
		b1.encender();
		print_state(b1, b2);
	}
}
