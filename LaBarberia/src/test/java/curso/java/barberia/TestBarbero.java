/**
 * 
 */
package curso.java.barberia;

import static org.easymock.EasyMock.*;
import org.junit.Before;
import org.junit.Test;

/**
 * @author lcu
 *
 */
public class TestBarbero {

    Barbero            barbero;
    Barberia           barberia;
    VerboseSemDijkstra sem_barbero;
    Cliente            cliente;


    /**
     * Instance Under Test
     * 
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        barberia    = createMock( "barberia", Barberia.class );
        sem_barbero = createMock( "sem_barbero", VerboseSemDijkstra.class );
        cliente     = createMock( "cliente", Cliente.class );
        barbero     = new Barbero( "Manolo", barberia, 1, sem_barbero );
    }

    @Test( timeout = 10_000 )
    public void testBarberoCallsGetAforo() throws InterruptedException {
        barberia.abrir( barbero );
        expect( sem_barbero.down( eq( barbero ), anyString(), anyString() ) )
                .andReturn( 0 );
        expect( barberia.getCliente() ).andReturn( cliente );
        expect( cliente.getServicioRequerido() ).andReturn( "Limpieza de alerones" );
        

        replay( barberia, sem_barbero, cliente );

        barbero.run();
    }

}
