package chaterz.java.course;

public class Bombilla {
	
	boolean encendido = false;
	
	public void encender() {
		System.out.println(this + ": me han encendido");
		encendido = true;
	}
	public void apagar() {
		System.out.println(this + ": me han apagado");
		encendido = false;
	}
	public boolean isEncendido() { return encendido; }

}
