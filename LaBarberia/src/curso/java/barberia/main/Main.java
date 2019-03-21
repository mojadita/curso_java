/*
 * Name: Main.java
 * Author: Luis Colorado <luiscoloradourcola@gmail.com>
 * Date: 20 mar. 2019 17:38:28
 * Project: LaBarberia
 * Package: curso.java.barberia
 * Copyright: (C) 2019 LUIS COLORADO. All rights reserved.
 */

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
public class Main {

	public static final String[] nombres = new String[] { "Abel", "Alberto", "Alfredo", "Álvaro", "Ambrosio", "Felipe",
			"Francisco", "Gancedo", "Garcilaso", "Gilberto", "Ginés", "Gonzalo", "Gregorio", "Gumersindo", "Jacinto",
			"Jacobo", "Jeremías", "Joaquín", "José", "Juan", "Judas", "Julio", "Macario", "Mariano", "Marino", "Matías",
			"Nabucodonosor", "Narciso", "Nemesio", "Nicolás", "Norberto", "Pedro", "Porfirio", "Ramón", "Remigio",
			"Renato", "Roberto", "Romualdo", "Rosendo", "Rubén", "Santiago", "Senén", "Sergio", "Sisebuto", "Tadeo",
			"Tancredo", "Telesforo", "Titulcio", "Tomás", "Torcuato", "Toribio", "Zenón", 
	};

	/**
	 * @param args no usado de momento.
	 */
	public static void main(String[] args) {

		Barberia barberia = new Barberia("La Perla", 5);
		Barbero barbero = new Barbero("Manolo", barberia, nombres.length);
		barbero.start();
		for (String name : nombres) {
			Cliente cl = new Cliente(name, barberia);
			cl.start();
		}
	}
}
