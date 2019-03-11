
package course.java;


public class Main {

    public static void p( String s ) {

        System.out.println( s );
    }

    /* Otra anotación tipo ShortCut */

    public static void a( String r ) {

        System.out.println( r );
    }

    public static void main( String[] args ) {

        MultilevelTreeMap<String, Integer> map
                = new MultilevelTreeMap<String, Integer>( null, "map" );
        MultilevelTreeMap<String, Integer> map_hijo
                = new MultilevelTreeMap<String, Integer>( map, "map_hijo" );

        map.put( "pepe", 26 );
        map.put( "juan", 23 );
        map.put( "pio", 12 );
        map.put( "pablo", 6 );
        map_hijo.put( "pepito", 1 );
        map_hijo.put( "juanito", 2 );
        map_hijo.put( "pio", 8 );
        p( "map.get(\"pio\")         <== " + map.get( "pio" ) );
        p( "map.get(\"pablo\")       <== " + map.get( "pablo" ) );
        p( "map.get(\"pepito\")      <== " + map.get( "pepito" ) );
        p( "map_hijo.get(\"pepito\") <== " + map_hijo.get( "pepito" ) );
        p( "map_hijo.get(\"pio\")    <== " + map_hijo.get( "pio" ) );
        p( "map_hijo.get(\"alfonso\")<== " + map_hijo.get( "alfonso" ) );
        map = null;
        map_hijo = null;
        System.gc();


    } /* EnD Of tHe Main */
}
