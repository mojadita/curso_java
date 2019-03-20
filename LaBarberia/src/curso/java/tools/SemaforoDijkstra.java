/*
 * Name: SemaforoDijkstra.java
 * Author: Luis Colorado <luiscoloradourcola@gmail.com>
 * Date: 20 mar. 2019 15:43:54
 * Project: LaBarberia
 * Package: curso.java.tools
 * Copyright: (C) 2019 LUIS COLORADO. All rights reserved.
 */

package curso.java.tools;


/**
 * @author lcu
 *
 */
public class SemaforoDijkstra {

    private int m_value;

    public SemaforoDijkstra( int initial ) {

        if ( initial < 0 )
            throw new IllegalArgumentException( "valor ilegal: " + initial );
        m_value = initial;
    }

    public synchronized int down()
            throws InterruptedException {

        if ( m_value == 0 )
            wait();
        return --m_value;
    }

    public synchronized int up() {

        int result = ++m_value ;
        notify();
        return result;
    }

    
    /**
     * @return the {@code int} {@code value}.
     */
    public synchronized int getValue() { return m_value; }
    
}
