/* Name: SortWithReader.java
 * Author: Luis Colorado <luiscoloradourcola@gmail.com>
 * Date: 14 mar. 2019 18:28:55
 * Project: Sort
 * Package: curso.java
 * Copyright: (C) 2019 LUIS COLORADO.  All rights reserved.
 */
package curso.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeSet;

/**
 * @author lcu
 *
 */
public class SortWithReader {

    /**
     * @param args
     */
    public static void main( String[] args ) {
        BufferedReader in = new BufferedReader( new InputStreamReader(System.in), 4096 );
        TreeSet<String>set = new TreeSet<String>();
        in.lines().forEach( s ->  set.add( s ));
        set.forEach( System.out::println );
    }

}
