
package chaterz.java.course.main;


import chaterz.java.course.Bombilla;
import chaterz.java.course.Puerta;
import chaterz.java.course.Radio;



public class HelloWorld {

    public static void print_state( Bombilla b ) {

        System.out.println( b + ".isEncendido() == " + b.isEncendido() );
    }

    public static void print_state( Bombilla... bombillas ) {

        for ( Bombilla b: bombillas )
            print_state( b );
        System.out.println();

    }


    /* métodos puertas */

    public static void SoyunSeparador() {

        System.out.println( "====== Sección Puertas =======" );
    }


    public static void estado_puerta( Puerta a ) {

        System.out.println( a + "¿Está Abierta?" + a.isOpen() );
    }

    public static void estado_puerta( Puerta... y ) {

        for ( Puerta a: y )
            estado_puerta( a );
        System.out.println( "____" );
    }



    public static void main( String[] args ) {

        System.out.println( "Inicio del Programa." );
        Bombilla b1 = new Bombilla( "b1" );
        Bombilla b2 = new Bombilla( "b2" );
        print_state( b1, b2 );
        b1.encender();
        print_state( b1, b2 );


        /* Level 2 */ /* keys Open Doors */

        Puerta p1 = new Puerta( "p1" );
        Puerta p2 = new Puerta( "p2" );
        estado_puerta( p1, p2 );
        System.out.println( "Voy a Abrir p1" );
        p1.abrir();
        estado_puerta( p1, p2 );
        System.out.println( "Ahora voy a cerrar P1 y abrir P2" );
        p2.abrir();
        p1.cerrar();
        estado_puerta( p1, p2 );
        System.out.println( "Fin de las Instrucciones" );
        System.out.println( "======================" );
        System.out.println( "Siguiente Nivel" );

        b1 = null;
        System.gc();

        /* Level 3 Radio */

        System.out.println( "======================" );
        System.out.println( "Arrancando Radio......" );
        System.out.println( "======================" );
        Radio flaix = new Radio( "flaixFM" );
        Radio ViciusRadio = new Radio( "vicious" );
        System.out.println( "Nombre de las Radios:" + flaix.getNombre() + "&"
                + "." + ViciusRadio.getNombre() );
        System.out.println( "Id " + flaix + ": " + flaix.getId() );
        System.out.println( "Id " + ViciusRadio + ": " + ViciusRadio.getId() );

        System.out.println( "Ruta Absoluta:" + flaix );
        System.out.println( "Número de Frequencia:" + flaix.getFrequencia() );
        System.out.println( "Esta en Marcha:" + flaix.isOnline() );



        System.out.println( "===================" );

    }

}
