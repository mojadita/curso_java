/* Name: Barberia.java
 * Author: Luis Colorado <luiscoloradourcola@gmail.com>
 * Date: 20 mar. 2019 15:58:42
 * Project: LaBarberia
 * Package: curso.java.barberia
 * Copyright: (C) 2019 LUIS COLORADO. All rights reserved. */
package curso.java.barberia;

/**
 * La {@link Barberia} es el lugar donde van los {@link Cliente}s a ser
 * atendidos por el {@link Barbero}. La {@link Barberia} tiene un
 * {@link Barberia#m_nombre}, que es aquel con el que es conocida, a ella se
 * asocia un {@link Barbero}, que es quien realiza servicios en ella, y acuden
 * {@link Cliente}s para realizarse esos servicios. La {@link Barberia} tiene un
 * {@link Barberia#getAforo()} que es el número maximo de {@link Cliente}s que
 * pueden entrar, incluyeno al que está en el sillón del {@link Barbero}.
 * 
 * Los {@link Cliente}s que exceden el aforo tienen que esperar fuera a que
 * salgan {@link Cliente}s para poder entrar.
 * 
 * Del mismo modo, solo un {@link Cliente} puede ser atendido por el
 * {@link Barbero}, de forma que solamente un {@link Cliente} puede ocupar el
 * sillon. Cuando esto ocurre, el método {@link Barberia#getCliente()} nos
 * permite averiguar qué {@link Cliente} es el que está sentado en el sillón del
 * {@link Barbero}.
 * 
 * La {@link Barberia} inicialmente está cerrada, hecho que se indica porque el
 * aforo permitido está fijado en cero. Cuando el barbero
 * {@link Barberia#abrir(Barbero)} la barbería, se aumenta el aforo hasta el
 * maximo indicado en el campo {@link Barberia#m_aforo}. Cuando el
 * {@link Barbero} se va de la {@link Barberia} debe dejarla cerrada, para lo
 * cual debe ir reduciendo el aforo (para esto debe esperar que este pueda
 * reducirse, con la marcha de los {@link Cliente}s.)
 * 
 * @author lcu
 *
 */
public class Barberia {

	private final String m_nombre;
	private final int m_aforo;
	private final VerboseSemDijkstra m_semAforo;
	private final VerboseSemDijkstra m_semSillon;
	private Barbero m_barbero;
	private Cliente m_enElSillon = null;

    /**
     * Constructor único. Se asigna {@code nombre} y {@code aforo} a la misma.
     * 
     * @param nombre es el nombre ({@link String}) que tomará la
     *               {@link Barberia}.
     * @param aforo  es el número de {@link Cliente}s que pueden estar en la
     *               {@link Barberia}, incluyendo
     *               el que está siendo atendido en el sillón del
     *               {@link Barbero}.
     */
    public Barberia( String nombre, int aforo ) {
        m_nombre    = nombre;
        m_aforo     = aforo;
        m_semAforo  = new VerboseSemDijkstra( 0 );
        m_semSillon = new VerboseSemDijkstra( 1 );
    }

    /**
     * @return the {@code int} {@code aforo}.
     */
    public int getAforo() {
        return m_aforo;
    }

	/**
	 * procedimiento de abrir la {@link Barberia}. Este procedimiento es llamado por
	 * el {@link Barbero} para realizar la apertura de la misma.
	 * 
	 * @param who referencia al {@link Barbero} que realiza la acción.
	 */
	public void abrir(Barbero who) {

		m_barbero = who;
		who.say("Abriendo la barbería");
		m_semAforo.up(who, m_aforo, //
				"Barbería " + this + " abierta. Aforo fijado en " + m_aforo + ".");
	}

	/**
	 * Acción de cerrar la {@link Barberia}. Este procedimiento es llamado por el
	 * {@link Persona} que cierra la {@link Barberia}. El parámetro {@code who} no
	 * debería ser necesario, ya que el {@link Barbero} que abre la barbería
	 * normalmente es el mismo que la cierra después y está almacenado en la misma.
	 * Por flexibilidad se incluye (permitir que otro {@link Barbero} sea quien
	 * cierre la {@link Barberia})
	 * 
	 * @param who {@link Barbero} que realiza la acción de cerrar la
	 *            {@link Barberia}
	 * @throws InterruptedException Si es interrumpido mientras espera la bajada del
	 *                              aforo.
	 */
	public void cerrar(Persona who) throws InterruptedException {

		who.say("Cerrando la barbería " + this);
		m_semAforo.down(getAforo());
		m_barbero.say(this + " cerrada.  Vamos pa cacha.");
		m_barbero = null;
	}

	/**
	 * Acción de entrar en la {@link Barberia}.
	 * 
	 * @param who Quien realiza la acción de entrar en la {@link Barberia}. Debe ser
	 *            un {@link Cliente}.
	 * @throws InterruptedException Si mientras espera para entrar, el
	 *                              {@link Thread} es interrumpido.
	 */
	public void entrar(Cliente who) throws InterruptedException {

		int aforo = m_semAforo.down(who, //
				m_semAforo.getValue() == 0 //
						? "Aforo completo, nos tocará esperar" //
						: "Entrando en la barbería", //
				"¡¡¡Dentro!!!");
		who.say("El aforo ha quedado en " + aforo);
	}

    /**
     * Acción de ir a sentarse en el sillón del {@link Barbero}.
     * 
     * @param  who                  Quién realiza la acción. Debe ser un
     *                              {@link Cliente}.
     * @throws InterruptedException Si mientras espera para ocupar el sillón del
     *                              {@link Barbero} el proceso es interrumpido.
     */
    public void ocuparSillon( Cliente who ) throws InterruptedException {

		m_semSillon.down(who, "Voy a sentarme en el sillón para que me atiendan.", "Sentado.  ¡Qué rollo de espera!");
		m_enElSillon = who;
	}

	/**
	 * Acción de levantarse del sillón del {@link Barbero} y que resulta en la
	 * liberación de éste.
	 * 
	 * @param who Quién realiza la acción. Este parámetro no sería necesario, pues
	 *            el {@link Cliente} que realiza la acción está registrado en la
	 *            {@link Barberia}, mientras ocupa el sillón del {@link Barbero}.
	 */
	public void desocuparSillon(Cliente who) {

		m_semSillon.up(who, m_barbero, "¡¡Gracias!!  Me ha dejado como una patena.");
		m_enElSillon = null;
	}

	/**
	 * Acción de salir de la {@link Barberia}. El {@link Cliente} que sale es el
	 * especificado como parámetro.
	 * 
	 * @param who El {@link Cliente} que sale de la {@link Barberia}.
	 */
	public void salir(Cliente who) {

		int aforo = m_semAforo.up(who, "Salgo de la barberia y me voy para casa, que ya está bien las horas que son");
		who.say("El aforo ha quedado en " + aforo);
	}

    /**
     * @return el {@code Barbero} {@code dueño}, que opera esta
     *         {@link Barberia}.
     */
    public Barbero getDueño() {
        return m_barbero;
    }

    /**
     * @return el {@link Cliente} que está actualmente sentado en el sillón del
     *         {@link Barbero}, o {@code null} en caso de que no haya nadie
     *         sentado.
     */
    public Cliente getCliente() {
        return m_enElSillon;
    }

	@Override
	public String toString() {

		return "Barbería \"" + m_nombre + "\"";
	}
}
