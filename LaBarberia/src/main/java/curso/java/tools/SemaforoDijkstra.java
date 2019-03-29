/* Name: SemaforoDijkstra.java
 * Author: Luis Colorado <luiscoloradourcola@gmail.com>
 * Date: 20 mar. 2019 15:43:54
 * Project: LaBarberia
 * Package: curso.java.tools
 * Copyright: (C) 2019 LUIS COLORADO. All rights reserved. */

package curso.java.tools;

/**
 * Implementación de un Semáforo de Dijkstra en Java.
 * 
 * La intención de esta clase es realizar semáforos con espera bloqueada al
 * estilo de los utilizados por Edsger W. Dijkstra en su trabajos sobre
 * computación distribuida.
 * 
 * @author lcu
 *
 */
public class SemaforoDijkstra {

    private int m_value;

    /**
     * Constructor único con un valor inicial.
     * 
     * @param initial valor inicial que toma el semáforo al comienzo.
     */
    public SemaforoDijkstra( int initial ) {
        if ( initial < 0 )
            throw new IllegalArgumentException( "valor ilegal: " + initial );
        m_value = initial;
    }

    /**
     * Acción de esperar sobre el {@link SemaforoDijkstra} para conseguir acceso
     * al recurso compartido. El valor del semáforo debe ser positivo ( &gt; 0 )
     * para que el acceso sea permitido. En caso de que el semáforo sea cero, el
     * proceso (y los que lleguen después) serán bloqueados esperando a que el
     * semáforo obtenga un valor positivo.
     * 
     * @param  n                    número de veces que se quiere hacer
     *                              {@link #down(int)}.
     * @return                      el valor del semáforo que queda una vez
     *                              obtenido el recurso.
     * @throws InterruptedException si durante la espera se produce una
     *                              interrupción
     *                              debido a una señal.
     */
    public synchronized int down( final int n ) throws InterruptedException {
        int quedan = n;
        try {
            while ( m_value < quedan ) {
                quedan  -= m_value;
                m_value  = 0;
                wait();
            }
            // m_value >= quedan
            m_value -= quedan;
            return m_value;
        } catch ( InterruptedException e ) {
            // ajustamos el valor del semaforo añadiendo todo lo que hemos
            // quitado
            // (n - quedan) de manera que el semáforo no se vea afectado por
            // nuestra
            // interrupción.
            m_value += n - quedan;
            throw e;
        }
    }

    /**
     * Realiza una llamada a {@link #down(int)} con un valor de 1.
     * 
     * @see                         #down(int)
     * @return                      el valor del semáforo que queda una vez
     *                              obtenido el recurso.
     * @throws InterruptedException si durante la espera se produce una
     *                              interrupción
     *                              debido a una señal.
     */
    public int down() throws InterruptedException {
        return down( 1 );
    }

    /**
     * Acción de liberar el semáforo, se produce un incremento del mismo y se
     * despierta a todos los procesos que esperan sobre el mismo a fin de que
     * tengan oportunidad de acceder.
     * 
     * @param  n número de recursos a liberar.
     * 
     * @return   El valor resultante de la liberación de {@code n} recursos.
     */
    public synchronized int up( int n ) {
        int result = m_value += n;
        notifyAll();
        return result;
    }

    /**
     * @see    #up(int)
     * @return El valor resultante de la liberación de un recurso.
     */
    public int up() {
        return up( 1 );
    }

    /**
     * @return el valor instantáneo actual del semáforo.
     */
    public synchronized int getValue() {
        return m_value;
    }
}
