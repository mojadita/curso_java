/* Name: VerboseSEmDijkstra.java
 * Author: Luis Colorado <luiscoloradourcola@gmail.com>
 * Date: 20 mar. 2019 16:24:29
 * Project: LaBarberia
 * Package: curso.java.barberia
 * Copyright: (C) 2019 LUIS COLORADO. All rights reserved. */
package curso.java.barberia;

import curso.java.tools.SemaforoDijkstra;

/**
 * Semáforo derivado del {@link SemaforoDijkstra}. La sobreimplementación
 * consiste en permitir la emisión de mensajes arbitrarios así como calcular el
 * tiempo de espera por la liberación de un recurso.
 * 
 * @author lcu
 *
 */
public class VerboseSemDijkstra extends SemaforoDijkstra {

	/**
	 * @param initial valor inicial para asignar al semáforo.
	 * @see SemaforoDijkstra#SemaforoDijkstra(int)
	 */
	public VerboseSemDijkstra(int initial) {
		super(initial);
	}

	/**
	 * Realiza una operación {@link SemaforoDijkstra#down()} ilustrándola con un
	 * mensaje al comienzo de la misma, realizado por una {@link Persona} y con un
	 * mensaje al finalizar la espera. Se indicará también el tiempo requerido en la
	 * espera.
	 * 
	 * @param who            que {@link Persona} realiza la espera.
	 * @param before_waiting mensaje dicho al empezar la espera.
	 * @param after_waiting  mensaje dicho cuando la espera termina.
	 * @return el valor del {@link SemaforoDijkstra}, una vez la espera ha
	 *         terminado.
	 * @throws InterruptedException Si durante la espera se produce una interrupción
	 *                              por una señal de otro {@link Thread}.
	 */
	public int down(Persona who, String before_waiting, String after_waiting) throws InterruptedException {
		who.say(before_waiting);
		long ts = System.currentTimeMillis();
		int res = super.down();
		long elapsed = System.currentTimeMillis() - ts;
		who.say(after_waiting + String.format("  (Duración/espera: %d.%03ds)", elapsed / 1000, elapsed % 1000));
		return res;
	}

	/**
	 * Realiza una operación {@link SemaforoDijkstra#up()} ilustrándola con mensajes
	 * originados por la {@link Persona} que realiza la llamada.
	 * 
	 * @param who     que {@link Persona} realiza la acción.
	 * @param whom    a qué {@link Persona} va dirigida la acción. Lo que se dice va
	 *                dirigido a {@code whom}
	 * @param message el mensaje que se le dice a {@code whom}
	 * @return el valor del {@link SemaforoDijkstra} una vez realizada la acción.
	 */
	public int up(Persona who, Persona whom, String message) {
		who.say(whom, message);
		return super.up();
	}

	/**
	 * Acción de {@link SemaforoDijkstra#up} ilustrada con mensajes.
	 * 
	 * @param who     qué {@link Persona} realiza la acción.
	 * @param message lo que dice la persona cuando realiza la acción. No hay
	 *                destinatario del mensaje, por tanto no es un mensaje dirigido
	 *                a nadie en concreto.
	 * @return el valor ajustado en e l {@link SemaforoDijkstra} tras la ejecución
	 *         de este método.
	 */
	public int up(Persona who, String message) {
		who.say(message);
		return super.up();
	}
}
