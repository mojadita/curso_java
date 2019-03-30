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

    Barbero            barbero, barbero2;
    Barberia           barberia;
    VerboseSemDijkstra sem_barbero, sem_barbero2;
    Cliente            cliente, cliente2;


    /**
     * Instance Under Test
     * 
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        barberia     = createMock( "barberia", Barberia.class );
        sem_barbero  = createMock( "sem_barbero", VerboseSemDijkstra.class );
        sem_barbero2 = createMock( "sem_barbero2", VerboseSemDijkstra.class );
        cliente      = createMock( "cliente", Cliente.class );
        cliente2     = createMock( "cliente2", Cliente.class );

        barbero  = new Barbero( "barbero", barberia, 1, sem_barbero );
        barbero2 = new Barbero( "barbero2", barberia, 2, sem_barbero2 );
    }

    @Test( timeout = 2_000 )
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

    @Test( timeout = 4_000 )
    public void testBarberoTwoServices() throws InterruptedException {

        barberia.abrir( barbero2 );

        expect( sem_barbero2.down( eq( barbero2 ), anyString(), anyString() ) )
                .andReturn( 0 );
        expect( barberia.getCliente() ).andReturn( cliente );
        expect( cliente.getServicioRequerido() )
                .andReturn( "Limpieza de alerones" );
        cliente.despierta( eq( barbero2 ), anyString() );

        expect( sem_barbero2.down( eq( barbero2 ), anyString(), anyString() ) )
                .andReturn( 0 );
        expect( barberia.getCliente() ).andReturn( cliente2 );
        expect( cliente2.getServicioRequerido() )
                .andReturn( "corte de uñas de los piés" );
        cliente2.despierta( eq( barbero2 ), anyString() );
        barberia.cerrar( barbero2 );

        replay( barberia, sem_barbero2, cliente, cliente2 );

        barbero2.run();

        verify( barberia, sem_barbero2, cliente, cliente2 );
    }


}
