/*
 * Name: Barberia.java
 * Author: Luis Colorado <luiscoloradourcola@gmail.com>
 * Date: 20 mar. 2019 15:58:42
 * Project: LaBarberia
 * Package: curso.java.barberia
 * Copyright: (C) 2019 LUIS COLORADO. All rights reserved.
 */

package curso.java.barberia;


import curso.java.tools.SemaforoDijkstra;



/**
 * @author lcu
 *
 */
public class Barberia {

    private final String           m_nombre;
    private final int              m_aforo;
    private final SemaforoDijkstra m_semAforo;
    private final SemaforoDijkstra m_semSillon;
    private Barbero                m_barbero;
    private Cliente                m_enElSillon = null;

    public Barberia( String nombre, int aforo ) {

        m_nombre = nombre;
        m_aforo = aforo;
        m_semAforo = new SemaforoDijkstra( 0 );
        m_semSillon = new SemaforoDijkstra( 1 );
    }

    /**
     * @return the {@code int} {@code aforo}.
     */
    public int getAforo() { return m_aforo; }

    public void abrir( Barbero who ) {

        m_barbero = who;
        m_barbero.say( "Abriendo la barberia" );
        for ( int i = 0; i < m_aforo; i++ ) {
            m_semAforo.up();
        }
        m_barbero.say( "Barberia abierta" );
    }

    public void entrar( Cliente who ) throws InterruptedException {

        who.say( "Entrando en la barbería, espero que me atiendan pronto.");
        int aforo = m_semAforo.down();
        who.say( "Ya he entrado. El aforo es de " + aforo );
    }

    public void ocuparSillon( Cliente who ) throws InterruptedException {

        who.say( "Voy a sentarme en el Sillon, para que me atiendan" );
        m_semSillon.down();
        who.say( "Ya me he sentado, que rollo de espera." );
        m_enElSillon = who;
    }

    public void desocuparSillon( Cliente who ) {

        who.say( "Gracias barbero " + m_barbero
                + ", me ha dejado como una patena" );
        m_enElSillon = null;
        int aforo = m_semSillon.up();
        who.say( "El aforo ha quedado en " + aforo );
    }

    public void salir( Cliente who ) {

        who.say( "Salgo de la barberia y me voy para casa, que ya está bien las horas que son" );
        m_semAforo.up();
    }

    /**
     * @return the {@code Barbero} {@code dueño}.
     */
    public Barbero getDueño() { return m_barbero; }

    public Cliente getCliente() { return m_enElSillon; }

    @Override
    public String toString() {
        return m_nombre;
    }
}
