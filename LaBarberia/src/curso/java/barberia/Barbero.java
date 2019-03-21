/*
 * Name: Barbero.java
 * Author: Luis Colorado <luiscoloradourcola@gmail.com>
 * Date: 20 mar. 2019 17:03:16
 * Project: LaBarberia
 * Package: curso.java.barberia
 * Copyright: (C) 2019 LUIS COLORADO. All rights reserved.
 */

package curso.java.barberia;


/**
 * Esta clase representa al {@link Barbero}. Deriva de la clase {@link Persona},
 * que aporta una funcionalidad básica y ésta a su vez de la clase
 * {@link Thread}, a fin de que se puedan ejecutar instancias y así cobrar vida.
 * 
 * @author lcu
 *
 */
public class Barbero extends Persona {

    private final Barberia m_barberia;
    private int            m_servicios;

    /**
     * Constructor único para el {@link Barbero}.
     * 
     * @param name      Nombre de pila del {@link Barbero}.
     * @param barberia  Referencia a la {@link Barberia} donde actuará este
     *                  {@link Barbero}.
     * @param servicios Número total de servicios que realizará el
     *                  {@link Barbero}
     *                  en la {@link Barberia} indicada. El {@link Barbero} abre
     *                  la
     *                  {@link Barberia}, realiza {@code servicios} servicios, y
     *                  después cierra y se va a su casa.
     */
    public Barbero( String name, Barberia barberia, int servicios ) {

        super( name );
        m_barberia = barberia;
        m_servicios = servicios;
    }

    @Override
    public void run() {

        say( "Hola amigos, soy el barbero, voy a abrir la " + m_barberia );
        m_barberia.abrir( this );
        while ( m_servicios-- > 0 ) {
            try {
                aDormir( "Aaaayyyy que sueño, me voy a dormir un rato. zzzzZZZZZzzzzzZZZZ",
                        "Ainnssss... que bien he dormido." );
            } catch ( InterruptedException e ) {
                say( "¡¡¡Vaya contratiempo!!! nos han interrumpido el sueñecito." );
            }
            Cliente elCliente = m_barberia.getCliente();
            String servicio = elCliente.getServicioRequerido();
            tarea( 2000,
                    "Vamos a atender al cliente " + elCliente
                            + ", que quiere un " + servicio,
                    servicio );
            this.despiertaA( elCliente,
                    "¡¡¡ " + elCliente + ", despierte leche!!! Que su "
                            + servicio + " está listo." );
        }
        try {
            m_barberia.cerrar( this );
        } catch ( InterruptedException e ) {
            say( "Me han interrumpido el cierre. ¡¡¡Cáspita!!!  No puedo terminar de cerrar." );
        }
    }
}
