/* Name: Persona.java
 * Author: Luis Colorado <luiscoloradourcola@gmail.com>
 * Date: 20 mar. 2019 16:24:29
 * Project: LaBarberia
 * Package: curso.java.barberia
 * Copyright: (C) 2019 LUIS COLORADO. All rights reserved. */
package curso.java.barberia;

import java.util.Random;

/**
 * Clase que provee de funcionalidad básica a {@link Cliente}s y
 * {@link Barbero}s. La clase se declara {@code abstract} a fin de que no pueda
 * ser instanciada. El constructor es {@code protected} a fin de qeu solo pueda
 * ser llamado desde subclases de esta clase.
 * 
 * @author lcu
 *
 */
public abstract class Persona extends Thread {

    protected final Random           randomizer = new Random();
    private final String             m_name;
    private final VerboseSemDijkstra m_durmiendo;

    /**
     * Constructor único para {@link Persona}.
     * 
     * @param name el nombre de la persona.
     */
    protected Persona( String name ) {
        m_name      = name;
        m_durmiendo = new VerboseSemDijkstra( 0 );
    }

    /**
     * Constructor para realizar tests de unidad.
     * 
     * @param name el nombre de la {@link Persona}.
     * @param sem  el semáforo que emplea cuando espera que le despierten.
     */
    Persona( String name, VerboseSemDijkstra sem ) {
        m_name      = name;
        m_durmiendo = sem;
    }

    /**
     * Acción realizada por una {@link Persona} cuando se dispone a esperar un
     * evento que debe ser realizado por otra {@link Persona}.
     * 
     * @param  onEntry              Mensaje que dice la {@link Persona} al
     *                              commienzo de la
     *                              espera.
     * @param  onExit               Mensaje que dirá la {@link Persona} cuando
     *                              sea avisada de
     *                              que
     *                              la tarea realizada terminó. También se
     *                              indicará el tiempo
     *                              esperado.
     * @throws InterruptedException Si la {@link Persona} es interrumpida
     *                              mientras
     *                              espera que la tarea acabe.
     */
    public void aDormir( String onEntry, String onExit )
            throws InterruptedException {
        m_durmiendo.down( this, onEntry, onExit );
    }

    /**
     * Acción de despertar a alguien.
     * 
     * @param a_quien {@link Persona} a la que hay que despertar.
     * @param mensaje Mensaje que dice la {@link Persona} que despierta a la
     *                {@link Persona} que es despertada.
     */
    public void despierta( Persona who, String mensaje ) {
        m_durmiendo.up( who, this, mensaje );
    }

    /**
     * Hablar. Mensaje que una {@link Persona} dice para sí misma. No hay
     * interlocutor.
     * 
     * @param what Lo que se dice.
     */
    public void say( String what ) {
        System.out.println( "--<" + this + ">: " + what );
    }

    /**
     * Hablar. Mensaje que una {@link Persona} le dice a otra.
     * 
     * @param whom {@link Persona} a la que se dirige el mensaje.
     * @param what lo que se dice.
     */
    public void say( Persona whom, String what ) {
        say( "(a " + whom + "): " + what );
    }

    @Override
    public String toString() {
        return "D." + m_name;
    }

    /**
     * Acción de realizar una Tarea. La taréa durará un tiempo máximo de
     * {@code max_msec} y al comienzo se emitirá el mensaje {@code mensaje}. Al
     * finalizar se emite un mensaje fijo indicando la tarea terminada y el
     * tiempo
     * que ha llevado la realizazión de la misma.
     * 
     * @param max_msec Duración máxima de la tarea. Milisegundos.
     * @param mensaje  Mensaje indicando el comienzo de la tarea.
     * @param tarea    Tarea a realizar.
     */
    protected void tarea( int max_msec, String mensaje, String tarea ) {
        try {
            say( String.format( "%s  Comienza Tarea \"%s\".", mensaje,
                    tarea ) );
            long now = System.currentTimeMillis();
            Thread.sleep( randomizer.nextInt( max_msec ) );
            long elapsed = System.currentTimeMillis() - now;
            say( String.format(
                    "Finaliza Tarea \"%s\".  (Duración/espera: %d.%03ds)",
                    tarea, elapsed / 1000, elapsed % 1000 ) );
        } catch ( InterruptedException e ) {
            say( "¡¡¡Vaya engorro!!!  Nos han interrumpido." );
        }
    }
}
