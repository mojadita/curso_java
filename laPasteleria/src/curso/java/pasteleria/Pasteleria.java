package curso.java.pasteleria;


/**
 * @author Chaterz
 */
public class Pasteleria {

    public int m_aforopasteleria = 10;
    public String m_name = "la Nata";
    public String m_direccion = "AV Gran Via, 33";
    public boolean m_estaAbierto = false;



    /* Constructor, que nos ayudará a generar cosas en la pasteleria */

    public Pasteleria( String nombre, int aforo, boolean estado) {
        m_aforopasteleria = aforo;
        m_name = nombre;
        m_estaAbierto = estado;

    }
    /*

    public abrir(){
        if (m_estaAbierto == true) {
cliente++;
        }
    }
*/

} /* End Of the file */
