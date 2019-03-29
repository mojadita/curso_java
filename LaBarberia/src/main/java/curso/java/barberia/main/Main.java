/* Name: Main.java
 * Author: Luis Colorado <luiscoloradourcola@gmail.com>
 * Date: 20 mar. 2019 17:38:28
 * Project: LaBarberia
 * Package: curso.java.barberia.main
 * Copyright: (C) 2019 LUIS COLORADO. All rights reserved. */
package curso.java.barberia.main;

import curso.java.barberia.Barberia;
import curso.java.barberia.Barbero;
import curso.java.barberia.Cliente;

/**
 * Programa principal del programa sobre usos y costumbres en las
 * {@link Barberia}s.
 * 
 * @author lcu
 *
 */
public abstract class Main {

    /**
     * Aforo maximo que se permitira en la {@link Barberia} del programa.
     */
    public static final int AFORO_MAX = 5;

    /**
     * Nombre que recibirá la {@link Barberia} del programa.
     */
    public static final String NOMBRE_BARBERIA = "La Perla";

    /**
     * Nombre que recibirá el {@link Barbero} del programa.
     */
    public static final String NOMBRE_BARBERO = "Manolo";

    /**
     * Nombres a usar cuando no se proporciona una lista de nombres como
     * argumentos. El programa debe indicar como
     * se llama cada {@link Cliente} de los que van a pasar por la
     * {@link Barberia}.
     */
    public static final String[] nombres = new String[] { "Abel", "Alberto",
        "Alfredo", "Álvaro", "Ambrosio", "Felipe", "Francisco", "Gancedo",
        "Garcilaso", "Gilberto", "Ginés", "Gonzalo", "Gregorio", "Gumersindo",
        "Jacinto", "Jacobo", "Jeremías", "Joaquín", "José", "Juan", "Judas",
        "Julio", "Macario", "Mariano", "Marino", "Matías", "Nabucodonosor",
        "Narciso", "Nemesio", "Nicolás", "Norberto", "Pedro", "Porfirio",
        "Ramón", "Remigio", "Renato", "Roberto", "Romualdo", "Rosendo", "Rubén",
        "Santiago", "Senén", "Sergio", "Sisebuto", "Tadeo", "Tancredo",
        "Telesforo", "Titulcio", "Tomás", "Torcuato", "Toribio", "Zenón",
    };

    private Main() {
    }

    /**
     * @param args nombres de los {@link Cliente}s. Cada argumento representa el
     *             nombre de un {@link Cliente}
     */
    public static void main( String[] args ) {

        if ( args.length == 0 ) {
            args = nombres;
        }

        Barberia barberia = new Barberia( NOMBRE_BARBERIA, AFORO_MAX );
        Barbero  barbero  = new Barbero( NOMBRE_BARBERO, barberia,
                args.length );
        barbero.start();
        for ( String name : args ) {
            Cliente cl = new Cliente( name, barberia );
            cl.start();
        }
    }
}
