package chaterz.java.course;

public class Puerta {

    boolean abierta = false;
    String p_barras;
    String tipo;

    public String getP_barras() {
        return p_barras;
    }

    public void setP_barras(String p_barras) {
        this.p_barras = p_barras;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }




    public Puerta(String name) {
        System.out.println(super.toString()+":¿abres esta Puerta?");
        p_barras = name;

    }

    public void abrir(){
        System.out.println(this + ":La Puerta está Abierta");
        abierta = true;

    }

    public void cerrar() {
      System.out.println(this +":LaPuerta está Cerrada.");
      abierta = false;
    }

 /* método bautizado como general */

    public boolean isOpen() {
        return abierta;
    }

    public String toString() {
        return "[" + p_barras + "]";
    }

}
