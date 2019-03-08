package chaterz.java.course;

public class Bombilla {
	
	boolean encendido = false;
	String m_name;
	
	public Bombilla(String name) {
		System.out.println(super.toString() + ": acabo de ser creada");
		m_name = name;
	}
	
	public void encender() {
		System.out.println(this + ": me han encendido");
		encendido = true;
	}
	public void apagar() {
		System.out.println(this + ": me han apagado");
		encendido = false;
	}
	public boolean isEncendido() { return encendido; }
	
	public String toString() {
		return "[" + m_name + "]";
	}
}
