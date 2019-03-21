/* Name: Cliente.java
 * Author: Luis Colorado <luiscoloradourcola@gmail.com>
 * Date: 20 mar. 2019 16:38:41
 * Project: LaBarberia
 * Package: curso.java.barberia
 * Copyright: (C) 2019 LUIS COLORADO.  All rights reserved.
 */
package curso.java.barberia;

/**
 * Clase representando a un {@link Cliente} de la {@link Barberia}.
 * 
 * @author lcu
 *
 */
public class Cliente extends Persona {
    
    private Barberia m_myBarberia;

    /**
     * Constructor único para un {@link Cliente}.
     * 
     * @param name  Nombre del cliente.
     * @param a_donde Referencia a la {@link Barberia} a donde debe dirigirse el {@link Cliente}.
     */
    public Cliente( String name, Barberia a_donde ) {
        super( name );
        m_myBarberia = a_donde;
    }
    
    @Override
    public void run() {
        try {
            tarea( 5000, 
            		"Hola amigos, buenos días.  Salgo de casa a comprar.", 
            		"Compra" );
            tarea( 1000, 
            		"Ayyyy que largo tengo el pelo... tengo que ir a la barbería " + m_myBarberia + ".", 
            		"Viaje" );
            m_myBarberia.entrar( this );
            m_myBarberia.ocuparSillon( this );
            Barbero b = m_myBarberia.getDueño();
            despiertaA( b, 
            		"¡¡¡Eh barbero " + b + "!!!, ya estoy listo para que me corte el pelo." );
            aDormir("Mientras me cortan el pelo leeré algunas revistas.", 
            		"Bueno, gracias Sr. " + b + ", sin duda alguna ha hecho un trabajo extraordinario.");
            m_myBarberia.desocuparSillon( this );
            m_myBarberia.salir( this );
            say("¡¡¡Que barbaridad!!!  Lo que hemos tardado.  Hasta otro día.  Toca morir.  Fin.");
        } catch ( InterruptedException e ) {
        }
    }
}
