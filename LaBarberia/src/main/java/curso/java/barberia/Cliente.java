/* Name: Cliente.java
 * Author: Luis Colorado <luiscoloradourcola@gmail.com>
 * Date: 20 mar. 2019 16:38:41
 * Project: LaBarberia
 * Package: curso.java.barberia
 * Copyright: (C) 2019 LUIS COLORADO. All rights reserved. */
package curso.java.barberia;

/**
 * Clase representando a un {@link Cliente} de la {@link Barberia}.
 * 
 * @author Luis Colorado {@code <luiscoloradourcola@gmail.com>}
 *
 */
public class Cliente extends Persona {

    private Barberia m_myBarberia;
    private String   m_servicioRequerido;

    private static final String[] servicios = new String[] { "afeitado",
        "corte de pelo", "lavado", "extracción de una muela", "peinado",
        "cuidado de uñas", "cuidado de piés",
    };

    /**
     * Constructor único para un {@link Cliente}.
     * 
     * @param name    Nombre del cliente.
     * @param a_donde Referencia a la {@link Barberia} a donde debe dirigirse el
     *                {@link Cliente}.
     */
    public Cliente( String name, Barberia a_donde ) {
        super( name );
        m_myBarberia        = a_donde;
        m_servicioRequerido = servicios[ randomizer
                .nextInt( servicios.length ) ];
    }

    /**
     * Constructor visible al paquete, solo para tests de unidad. Se incluye una
     * referencia al semaforo a fin de permitir hacer mocking.
     * 
     * @param name    Nombre del {@link Cliente}.
     * @param a_donde {@link Barberia} a la que se dirigirá el {@link Cliente}.
     * @param sem     {@link VerboseSemDijkstra} empleado en la sincronización.
     */
    Cliente( String name, Barberia a_donde, VerboseSemDijkstra sem ) {
        super( name, sem );
        m_myBarberia        = a_donde;
        m_servicioRequerido = servicios[ randomizer
                .nextInt( servicios.length ) ];
    }

    @Override
    public void run() {
        try {
            tarea( 2000, "Hola amigos, buenos días.  Salgo de casa a comprar.",
                    "COMPRAS" );
            String s = getServicioRequerido();
            tarea( 1000,
                    "¡¡¡Ayyyy que cosas!!!... tengo que ir a la barbería "
                            + m_myBarberia + " a que me hagan un " + s + ".",
                    "VIAJE A LA BARBERIA" );
            m_myBarberia.entrar( this );
            m_myBarberia.ocuparSillon( this );
            Barbero b = m_myBarberia.getDueño();
            b.despierta( this, "¡¡¡Eh barbero " + b
                    + "!!!, ya estoy listo para que me haga un " + s + "." );
            duerme( "Mientras me hacen un " + s + " leeré algunas revistas.",
                    "Bueno, gracias Sr. " + b
                            + ", sin duda alguna ha hecho un trabajo extraordinario." );
            m_myBarberia.desocuparSillon( this );
            m_myBarberia.salir( this );
            say( "¡¡¡Que barbaridad!!!  Lo que hemos tardado.  Hasta otro día.  Toca morir.  Fin." );
        } catch ( InterruptedException e ) {
            say( "¡¡¡Mierda!!!  Me han interrumpido.  Me voy a casa." );;
        }
    }

    /**
     * @return el servicio requerido al {@link Barbero}.
     */
    public String getServicioRequerido() {
        return m_servicioRequerido;
    }
}
