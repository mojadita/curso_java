package chaterz.java.course;

public class Bombilla {

	public void bombilla(){}

	public void setEncendido(boolean encendido) {
		this.encendido = encendido;
	}

	/* boolean no tiene get, al generar getter an setter solo genera setter ? */

	boolean encendido = false;
	
	public void encender() {
		System.out.println(this + ": me han encendido");
		encendido = true;
	}
	public void apagar() {
		System.out.println(this + ": me han apagado");
		encendido = false;
	}
	public boolean isEncendido() { return encendido; } /* <-- esto no lo piyo */

	public boolean isAfluorescente() { return tipo; }



	/* Solo se hacer construcctores a partir de Strings o instancias */
	public String getPotencia() {
		return potencia;
	}



	public void setPotencia(String potencia) {
		this.potencia = potencia;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	/**
	 * My Mombilla
	 */
	/* public String luz;
	public String vatios;
	public String forma;
	public String tipo;
	public String precio;
	*/

	public String potencia;
	public int precio;

}
