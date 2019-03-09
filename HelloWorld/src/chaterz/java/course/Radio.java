package chaterz.java.course;

public class Radio {


    /*Nivel Radio Frequencias  RF  */

    /**
     * Bienvenido a Radio Show Time
     */
    /* Aqui defino la radio por Instancias */

    /**
     * Id
     * Nombre
     * Frequencia
     * Onda
     */
    public Radio(String name) {
        System.out.println("Creando la radio.....");

    }

        boolean On = true;

    public boolean isOnline(){
        return On;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getFrequencia() {
        return Frequencia;
    }

    public void setFrequencia(double frequencia) {
        Frequencia = frequencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    int id = 0;
    double Frequencia = 106.1;
    String nombre ;
    boolean fm = true;

}
