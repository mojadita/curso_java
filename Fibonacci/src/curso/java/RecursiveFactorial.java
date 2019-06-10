/**
 * 
 */
package curso.java;


/**
 * @author lcu
 *
 */
public class RecursiveFactorial {
    
    public static int fact(int n) {
        if (n <= 0) return 1;
        return n * fact(n-1); 
                              // n * fact(n-1)
                              //     (n-1) * fact((n-1)-1)
                              //             fact(n - 2)
                              //             (n-2) * fact((n-2)-1)
                              //                     fact(n-3)
                              //                     (n-3) * fact(n-4)
                              //                             ....
                              //                                fact(n-n)
                              //                                fact(0)
                              //                                1
    }
    
    public static int fact2(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) 
            result *= i;
        return result;
    }

    /**
     * @param args
     */
    public static void main( String[] args ) {
        final int n = 6;
        System.out.println(String.format( "fact(%d) = %d", n, fact(n) ));
        System.out.println(String.format( "fact2(%d) = %d", n, fact2(n) ));
    }

}
