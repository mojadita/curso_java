
package chaterz.java.course;


public class Bombilla {

	/* boolean no tiene get, al generar getter an setter solo genera setter ? */

	boolean encendido = false;
	String m_name;
	public String potencia;
	public int precio;

	public Bombilla(String name) {
		System.out.println(super.toString() + ": acabo de ser creada");
		m_name = name;
	}

	/* métodos Staticos */

	public void encender() {
		System.out.println(this + ": me han encendido");
		encendido = true;
	}

	public void apagar() {
		System.out.println(this + ": me han apagado");
		encendido = false;
	}

	public boolean isEncendido() {
		return encendido;
	}

	public void setEncendido(boolean encendido) {
		this.encendido = encendido;
	}



	/* Getter and Setters */
	public String getPotencia() {
		return potencia;
	}

	public void setPotencia(String potencia) {
		this.potencia = potencia;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) { /* chaterz */
		this.precio = precio;
	}

    @Override
    protected void finalize() {

        System.out.println( this + ": ooohhhhh, me están destruyendo!!!" );
    }

    @Override
    public String toString() {

        return "[" + m_name + "]";
    }
}
