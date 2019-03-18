/*
 * Name: Main.java
 * Author: Luis Colorado <luiscoloradourcola@gmail.com>
 * Date: 18 mar. 2019 16:31:47
 * Project: Declarations
 * Package: curso.java
 * Copyright: (C) 2019 LUIS COLORADO. All rights reserved.
 */

package curso.java;


/**
 * @author lcu
 *
 */
public class Main {

    static int var1    = 3;
    static int next_id = 0;

    static {
        System.out.println( "Inicializando la clase " + Main.class );
        System.out.println( "var1 = " + var1 );
        System.out.println( "var1 = " + next_id );
    }

    {
        next_id++ ;
        System.out.println( this + ": me están construyendo" );
    }

    int m_id   = next_id;
    int m_var1 = 5;

    public Main( int initial ) {

        System.out.println(
                this + ": Constructor con parametro initial=" + initial );
        m_var1 = initial;
    }

    public Main() {

        System.out.println( this + ": Constructo sin parámetro" );
    }

    protected void finalize() {

        System.out.println( this + ": me estan jodiendo la marrana!!!" );
    }

    @Override
    public String toString() {

        return "Main [ m_id=" + m_id + ", "
                + "m_var1=" + m_var1 + ", "
                + "var1 = " + var1 + "]";
    }

    public static void p( String name, Object s ) {

        System.out.println( name + " <<< " + s );
    }

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main( String[] args ) throws InterruptedException {

        Main v1 = new Main( 3 ),
                v2 = new Main();

        v1 = new Main( 16 );
        System.gc();
        Thread.sleep( 1000 );
        p( "v1", v1 );
        p( "v2", v2 );
        Main.var1 = 21;
        p( "v1", v1 );
        p( "v2", v2 );
        v1.m_var1 = 15;
        v2.m_var1 = 17;
        p( "v1", v1 );
        p( "v2", v2 );
        v1.var1 = 7; // esto da un warning porque accedemos al campo estático a
                     // través de una instancia (v1).
        p( "v1", v1 );
        p( "v2", v2 );
        v1 = null;
        v2 = null;
        System.gc();
    }

}
