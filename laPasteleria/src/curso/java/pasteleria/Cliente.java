package curso.java.pasteleria;

public class Cliente extends Persona {

    private Pasteleria m_myPasteleria;
    String nombre;

    public Cliente(String name/*Pasteleria where*/) {

        /* cant do super */
        nombre = name;
        //m_myPasteleria = where;

    }

    /* Conviene heredar estos métodos ya que Persona los tiene definidos
    public entra() {}

    public sale() {}
    */

}
