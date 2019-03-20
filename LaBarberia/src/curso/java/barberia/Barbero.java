/* Name: Barbero.java
 * Author: Luis Colorado <luiscoloradourcola@gmail.com>
 * Date: 20 mar. 2019 17:03:16
 * Project: LaBarberia
 * Package: curso.java.barberia
 * Copyright: (C) 2019 LUIS COLORADO.  All rights reserved.
 */
package curso.java.barberia;


/**
 * @author lcu
 *
 */
public class Barbero extends Persona {
    
    private final Barberia m_barberia;
    private int m_servicios;
    
    /**
     * @param name
     * @param servicios TODO
     */
    public Barbero( String name, Barberia barberia, int servicios ) {
        super( name );
        m_barberia = barberia;
        m_servicios = servicios;
    }
    
    public void run() {
        try {
            say("Hola amigos, soy el barbero, voy a abrir la barberia " + m_barberia);
            m_barberia.abrir( this );
            while(m_servicios-- > 0) {
                aDormir();
                Cliente elCliente = m_barberia.getCliente();
                say("Vamos a atender al cliente " + elCliente + ", que quiere un corte de pelo");
                delay(2000);
                say("Señor " + elCliente + ", hermos acabado con usted, ya puede irse");
                despierta( elCliente );
            }
            m_barberia.cerrar( this );
        } catch ( InterruptedException e ) {
        }
    }
}
