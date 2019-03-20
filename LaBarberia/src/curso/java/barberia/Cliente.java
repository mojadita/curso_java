/* Name: Cliente.java
 * Author: Luis Colorado <luiscoloradourcola@gmail.com>
 * Date: 20 mar. 2019 16:38:41
 * Project: LaBarberia
 * Package: curso.java.barberia
 * Copyright: (C) 2019 LUIS COLORADO.  All rights reserved.
 */
package curso.java.barberia;

import java.util.Date;

/**
 * @author lcu
 *
 */
public class Cliente extends Persona {
    
    private Barberia m_myBarberia;

    public Cliente( String name, Barberia a_donde ) {
        super( name );
        m_myBarberia = a_donde;
    }
    
    @Override
    public void run() {
        try {
            say("Hola, buenos días, me despierto hoy " + new Date() + " para hacer cosas interesantes");
            delay( 5000 );
            say("Ayyyy que largo tengo el pelo... tengo que ir a la barbería " + m_myBarberia);
            delay( 1000 );
            m_myBarberia.entrar( this );
            m_myBarberia.ocuparSillon( this );
            Barbero b = m_myBarberia.getDueño();
            say("Eh barbero " + b + ", ya estoy listo para que me corte el pelo");
            despierta( b );
            aDormir();
            say("Bueno, gracias Sr. " + b + ", ha hecho un trabajo extraordinario");
            m_myBarberia.desocuparSillon( this );
            m_myBarberia.salir( this );
            say("Que barbaridad, lo que hemos tardado, hasta otro día.  Toca morir. Fin");
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
    }
}
