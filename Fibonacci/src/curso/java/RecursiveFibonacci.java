/**
 * 
 */
package curso.java;


/**
 * @author lcu
 *
 */
public class RecursiveFibonacci {
    
    
    public static int fibo(int n)
    {
        switch(n) {
        case 0: return 0;
        case 1: return 1;
        default: return fibo(n-1) + fibo(n - 2);
        }
    }
    
    /**
     * TODO
     * @param args
     */
    public static void main( String[] args ) {
        int n = fibo(5);
        System.out.println("Fibo(5) = " + n);
    }

}
