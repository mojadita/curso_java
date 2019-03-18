
package chaterz.java.course;


public class Puerta {

    boolean abierta = false;
    String  p_barras;
    String  tipo1   = "madera";
    String  tipo2   = "acero";

    public String getTipo2() { return tipo2; }

    public void setTipo2( String tipo2 ) { this.tipo2 = tipo2; }



    public String getP_barras() { return p_barras; }

    public void setP_barras( String p_barras ) { this.p_barras = p_barras; }

    public String getTipo() { return tipo1; }

    public void setTipo( String tipo ) { this.tipo1 = tipo; }



    public Puerta( String name ) {

        System.out.println( super.toString() + ":¿abres esta Puerta?" );
        p_barras = name;

    }

    public void stadopuertas() {

        System.out.println( "====Estado De Las Puertas=====" );
    }

    public void abrir() {

        System.out.println( this + ":Esta Puerta está Abierta y es de Tipo\n"
                + this.tipo1 );
        abierta = true;

    }

    public void cerrar() {

        System.out.println( this + ":Esta Puerta está Cerrada y es de Tipo \n"
                + this.tipo2 );
        abierta = false;
    }

    /* método bautizado como general */

    public boolean isOpen() { return abierta; }

    public String toString() {

        return "[" + p_barras + "]";
    }

}
