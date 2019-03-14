/*
 * Name: SortMain.java
 * Author: Luis Colorado <luiscoloradourcola@gmail.com>
 * Date: 14 mar. 2019 17:28:05
 * Project: Sort
 * Package: curso.java
 * Copyright: (C) 2019 LUIS COLORADO. All rights reserved.
 */

package curso.java;


import static java.lang.System.in;
import static java.lang.System.out;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.Vector;



/**
 * @author lcu
 *
 */
public class SortMain3 {

    /**
     * @param args
     * @throws Exception
     */
    public static void main( String[] args ) throws Exception {

        int c;
        String line = "";
        Vector<String> the_lines = new Vector<String>();
        while ( (c = in.read()) != -1 ) {
            switch ( c ) {
            case '\n':
            case '\f': /* new line or form feed */
                the_lines.add( line );
                line = "";
                break;
            default:
                line += (char) c;
            } // switch
        }
        if ( line.length() > 0 ) {
            the_lines.add( line );
        }
        
        Comparator<String> comp = new Comparator<String>() {
            @Override
            public int compare( String o1, String o2 ) {
                return o1.compareTo( o2 );
            }
        };
        
        the_lines.sort( comp );
        for(String l: the_lines)
            out.println(l);
    }
}
