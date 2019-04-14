/* Name: LinkedListClientMain.java
 * Author: Luis Colorado <luiscoloradourcola@gmail.com>
 * Date: 14 abr. 2019 10:47:38
 * Project: recursive-data
 * Package: curso.java.recursive_data.client
 * Copyright: (C) 2019 Luis Colorado. All rights reserved. */
package curso.java.recursive_data.client;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import curso.java.recursive_data.Node;

/**
 * @author Luis Colorado {@code <luiscoloradourcola@gmail.com>}
 *
 */
public class LinkedListClientMain {

    public static void main( String args[] ) throws FileNotFoundException,
            IllegalArgumentException, IOException {
        if ( args.length < 1 )
            throw new IllegalArgumentException( "Must specify a file name" );
        BufferedReader in     = new BufferedReader(
                new InputStreamReader( new FileInputStream( args[ 0 ] ) ) );
        String         line;
        Node<String>   collar = null;
        while ( (line = in.readLine()) != null ) {
            Node<String> cuenta = new Node<String>();
            cuenta.setData( line );
            cuenta.setNext( collar );
            collar = cuenta;
        }

        for ( Node<String> n = collar ; n != null ; n = n.getNext() ) {
            System.out.println( n.getData() );
        }
    }
}
