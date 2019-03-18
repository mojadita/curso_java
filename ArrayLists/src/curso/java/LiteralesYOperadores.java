
package curso.java;


// import java.course.Bombilla;

public class LiteralesYOperadores {

    @SuppressWarnings( "unused" )
    public static void main( String[] args ) {

        int x0 = 0x1234_5678; // hexadecimal
        int x1 = 02215053170; // octal
        int x2 = 0b0001_0010_0011_0100_0101_0110_0111_1000;
        int x3 = 305_419_896; // decimal.
        byte x4 = -128; // byte (signed)
        byte x5 = +127;
        char x6 = 'A'; // 65 -> A unicode. (los caracteres son unsigned)
        char x7 = 0xfffe; // unknown character UNICODE.
        char x8 = '\u2234'; // unicode
        char x9 = '\n';
        char x10 = '\u00a9'; // unicode (C) copyright char.
        String x11 = "Arriba Cachipurriana, ñ á \u00a9\033\b\f\n\r\t";
        String x11bis = new String( "Arriba Cachipurriana" );
        long x12 = 0x1234_5678_9abc_def0L; // long (signed)
        short x13 = -32768; // short (signed)
        short x14 = 32767;
        float x15 = 0.456F; // float (la F obligatoria)
        double x16 = 0.456D; // double (la D opcional)
        boolean x17 = true; // boolean.
        int[] x18 = new int[100];
        int[] x19 = new int[] {
            3,
            4,
            5,
        };
        Integer[] x20 = new Integer[] {
            3, 4, 5,
        };
        Integer[] x21 = new Integer[] {
            new Integer( 3 ),
            new Integer( 4 ),
            new Integer( 5 ),
        };
        /*
         * Bombilla[] x22 = new Bombilla[100];
         * Bombilla[] x23 = new Bombilla[]{
         * new Bombilla("b1"),
         * new Bombilla("b2"),
         * new Bombilla("b3"),
         * };
         */
        String[] x24 = new String[] {
            "Cadena 1",
            "Cadena dial",
            "Cadena 100",
        };
        boolean x25, x26, x27;
        /* public void x222() { */
        // ver
        // https://docs.oracle.com/javase/tutorial/java/nutsandbolts/operators.html
        // para obtener una lista de operadores y precedencia.
        x0 = x0 + x1; // suma entera.
        x0 = x0 - x1; // resta.
        x0 = -x0; // menos unario.
        x0 = +x0; // mas unario.
        x0 = x0 * x1; // producto.
        x0 = x0 / x1; // division.
        x0 = x0 % x1; // resto de la división por x1.
        x0 = x0 << 3; // desplaza los bits de x0 3 lugares a la izquierda.
        x0 = x0 >> 3; // desplaza los bits de x0 3 lugares a la derecha
                      // manteniendo el bit de signo.
        x0 = x0 >>> 3; // desplaza los bits de x0 3 lugares a la derecha
                       // insertando ceros por la izquierda.
        x0 = x0 & 0xff00; // and de bits. 0b1101 & 0b0110 ==> 0b0100;
        x0 = x0 | 0xff00; // or de bits. 0b1101 | 0b0110 ==> 0b1111;
        x0 = x0 ^ 0xff00; // or exclusivo de bits. 0x1101 ^ 0x0110 ==> 0b1011;
        x0 = ~0xff00; // negacion de bits. ~0b1101 => 0b0010
        // x17 = x25 && x26; // and logico.
        // x17 = x25 || x26; // or logico.
        // x17 = x25 ^ x26; // or exclusivo logico.
        // x17 = !x25; // not lógico.
        x0 += x1;
        x0 -= x1;
        x0 *= x1;
        x0 /= x1;
        x0 %= x1;
        x0 <<= x1;
        x0 >>= x1;
        x0 >>>= x1;
        x0 &= x1;
        x0 |= x1;
        x0 ^= x1;
        // x17 ^= x25; // cambia el valor de verdad de x17 si x25 es cierto.
        x0 = x17
                ? 23 // si x17 cierto, entonces va esta expresion.
                : 15; // si x17 falso, entonces va esta expresión.
        x0 = 3 + -25 * 17; // 3 + ((-25) * 17)
        // x0 = (new Bombilla("h")).getPrecio(); // operador . para referenciar
        // campos o métodos a ejecutar.

        // autoincremento/autodecremento
        x0 = x1-- ; // se asigna el valor de x1 a x0 y despues se decrementa x1
        x0 = --x1; // se asigna a x0 el valor de x1 despues de decrementado.
        x0 = x1++ ; // autoincremento.
        x0 = ++x1;

        // relacionales
        x17 = x0 < x1; // menor que.
        x17 = x0 <= x1; // menor o igual.
        x17 = x0 > x1; // mayor
        x17 = x0 >= x1; // mayor o igual.
        x11.compareTo( x11bis ); // Strings (o Comparable<T>). < 0 (x11 <
                                 // x11bis); == 0 (x11 == x11bis); > 0 (x11 >
                                 // x11bis);

        // igualdad
        x17 = x0 == x1; // igual
        x17 = x0 != x1; // distinto.
        x17 = x11.equals( x11bis ); // igualdad de cadenas/objetos. Cualquier
                                    // objeto.
        x17 = x11.equalsIgnoreCase( x11bis ); // ignorando
                                              // mayúsculas/minusculas.
        x17 = "Arriba Cachipurriana".equalsIgnoreCase( "ARRIBA CACHIPURRIANA" ); // posible
                                                                                 // notación
                                                                                 // para
                                                                                 // string
                                                                                 // literals.
    }
}

