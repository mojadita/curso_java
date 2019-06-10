/**
 * 
 */
package curso.java.recursive_data.client;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import curso.java.recursive_data.Node;

/**
 * @author lcu
 *
 */
public class MainRecursiveData {

    public static void main( String[] args ) throws IllegalArgumentException,
            IOException, FileNotFoundException {
        if ( args.length < 1 ) throw new IllegalArgumentException(
                "You must specify a filename as argument" );
        BufferedReader in = new BufferedReader( new FileReader( args[ 0 ] ),
                1024 );
        String         line;
        Node<String>   collar = null;
        while ( (line = in.readLine()) != null ) {
            Node<String> cuenta = new Node<String>();
            cuenta.setData( line );
            cuenta.setNext( collar );
            collar = cuenta;
        }
        for(Node<String> n = collar; n != null; n = n.getNext()) {
            System.out.println(n.getData());
        }
    }
}
