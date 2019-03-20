/*
 * Name: Main.java
 * Author: Luis Colorado <luiscoloradourcola@gmail.com>
 * Date: 20 mar. 2019 17:38:28
 * Project: LaBarberia
 * Package: curso.java.barberia
 * Copyright: (C) 2019 LUIS COLORADO. All rights reserved.
 */

package curso.java.barberia;


/**
 * @author lcu
 *
 */
public class Main {

    public static String[] nombres = new String[] {
        "D. Torcuato",
        "D. Ginés",
        "D. Alfredo",
        "D. Jacinto",
        "D. Felipe",
        "D. Ambrosio",
        "D. Zenón",
        "D. Matías",
        "Pepe",
    };

    /**
     * @param args
     */
    public static void main( String[] args ) {

        Barberia barberia = new Barberia( "La Perla", 5 );
        Barbero barbero = new Barbero( "Manolo", barberia );
        barbero.start();
        for ( String name: nombres ) {
            Cliente cl = new Cliente( name, barberia );
            cl.start();
        }
    }
}
