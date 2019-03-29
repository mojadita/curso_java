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
    Cliente            cliente, cliente2;


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
        cliente2    = createMock( "cliente2", Cliente.class );

        barbero = new Barbero( "Ceferino", barberia, 1, sem_barbero );
    }

    @Test( timeout = 10_000 )
    public void testBarberoOneService() throws InterruptedException {

        barberia.abrir( barbero );
        expect( sem_barbero.down( eq( barbero ), anyString(), anyString() ) )
                .andReturn( 0 );
        expect( barberia.getCliente() ).andReturn( cliente );
        expect( cliente.getServicioRequerido() )
                .andReturn( "Limpieza de alerones" );
        cliente.despierta( eq( barbero ), anyString() );
        barberia.cerrar( barbero );

        replay( barberia, sem_barbero, cliente );

        barbero.run();

        verify( barberia, sem_barbero, cliente );
    }

    @Test( timeout = 10_000 )
    public void testBarberoTwoServices() throws InterruptedException {

        barberia.abrir( barbero );

        expect( sem_barbero.down( eq( barbero ), anyString(), anyString() ) )
                .andReturn( 0 );
        expect( barberia.getCliente() ).andReturn( cliente );
        expect( cliente.getServicioRequerido() )
                .andReturn( "Limpieza de alerones" );
        cliente.despierta( eq( barbero ), anyString() );

        expect( sem_barbero.down( eq( barbero ), anyString(), anyString() ) )
                .andReturn( 0 );
        expect( barberia.getCliente() ).andReturn( cliente2 );
        expect( cliente2.getServicioRequerido() )
                .andReturn( "corte de uñas de los piés" );
        cliente2.despierta( eq( barbero ), anyString() );
        barberia.cerrar( barbero );

        replay( barberia, sem_barbero, cliente, cliente2 );

        barbero.run();

        verify( barberia, sem_barbero, cliente );
    }


}
