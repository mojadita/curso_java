/*
 * Name: Persona.java
 * Author: Luis Colorado <luiscoloradourcola@gmail.com>
 * Date: 20 mar. 2019 16:24:29
 * Project: LaBarberia
 * Package: curso.java.barberia
 * Copyright: (C) 2019 LUIS COLORADO. All rights reserved.
 */

package curso.java.barberia;


import java.util.Random;

import curso.java.tools.SemaforoDijkstra;



/**
 * @author lcu
 *
 */
public class Persona extends Thread {

    protected static final Random  randomizer  = new Random();
    private final String           m_name;
    private final SemaforoDijkstra m_durmiendo = new SemaforoDijkstra( 0 );

    protected Persona( String name ) {

        m_name = name;
    }

    public void aDormir() throws InterruptedException {

        say( "Aaaayyyy que sueño, me voy a dormir un rato. zzzzZZZZZzzzzzZZZZ" );
        m_durmiendo.down();
        say( "Ainnssss... que bien he dormido." );
    }

    public void despiertaA( Persona a_quien ) {

        say( a_quien
                + ", despierta ya, leche, que ya se han pasado las burras de la idem" );
        a_quien.m_durmiendo.up();
    }

    public void say( String what ) {

        System.out.println( "--<" + this + ">: " + what );
    }

    public String toString() {

        return "D. " + m_name;
    }

    protected void delay( int max_msec ) {

        try {
            Thread.sleep( randomizer.nextInt() & 0x7fffffff % max_msec );
        } catch ( InterruptedException e ) {
        }
    }
}
