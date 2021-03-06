import java.util.*;
import java.util.Map.Entry;



class TreeMapDemo {

    public static void main( String args[] ) {

        // Create a tree map
        TreeMap<String, Number> cartera = new TreeMap<String, Number>();
        TreeMap<String, Double> tm = new TreeMap<String, Double>();
        // Put elements to the map

        tm.put( "John Doe", 3434.34 );
        tm.put( "Tom Smith", 123.22 );
        tm.put( "Jane Baker", 1378.00 );
        tm.put( "Todd Hall", 99.22 );
        tm.put( "Ralph Smith", -19.08 );


        // Put the elements in the NewMap0
        Integer CadenaA = 0;
        CadenaA = CadenaA + 1;
        // for (int i = 0; i < cartera.entrySet()

        cartera.put( "Sergio Dalma", CadenaA++ );
        cartera.put( "Antonio Orozco", CadenaA++ );
        cartera.put( "Alejando Sanz", CadenaA++ );
        cartera.put( "Amaia Montoya", CadenaA++ );
        //// Get a set of the entries
        // Set set = tm.entrySet();
        //// Get an iterator
        // Iterator i = set.iterator();
        //// Display elements
        // while(i.hasNext()) {
        // Map.Entry me = (Map.Entry) i.next();
        // System.out.print(me.getKey() + ": ");
        // System.out.println(me.getValue());
        // }
        // System.out.println();
        System.out.println( "===============Walled Clientes================" );
        for ( Entry<String, Double> entry: tm.entrySet() ) {
            System.out.print( entry.getKey() + ": " );
            System.out.println( entry.getValue() );

        }

        System.out.println(
                "===============Cartera de Cantantes================" );
        for ( Entry<String, Number> saldo: cartera.entrySet() ) {
            System.out.print( saldo.getKey() + ":" );
            System.out.println( saldo.getValue() );
        }



        // Deposit 1000 into John Doe's account
        System.out.println( "===============Transacciones================" );
        double balance = ((Double) tm.get( "Jane Baker" )).doubleValue();
        tm.put( "Jane Baker", new Double( balance + 9000 ) );
        System.out.println( "Jane B. new balance: " +
                tm.get( "Jane Baker" ) + "...." + "Nuevo Saldo............"
                + "...." + tm.get( "Jane Baker" ) );
    }
}
