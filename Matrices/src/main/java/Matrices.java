public class Matrices {


    public static final int N = 10;
    public static void main(String[] args) {
        System.out.println("Vamos a llevar una " + "\n" +
                "Contabilidad del Stock " + "\n" +
                "de nuestros Materiales "+"\n" +
                "de la Tienda Con Matrices!");
        System.out.println("==========================================================================================");


        /* Definimos la matriz */
        int almacenes[][] = new int[3][2];
        almacenes[0][0] = 25; // en el almacen 0 tenemos 25 productos señalado con el 0 == EnStock
        almacenes[1][1] = 5; // en el amacen 1 tenemos que faltan 5 productos, 1 == faltan, 0 ocupados.
        almacenes[1][0] = 20; // tenemos ocupado 20 productos
        almacenes[2][0] = 10; //Solo hay diez productos
        almacenes[2][1] = 15;
        System.out.println("==========================================================================================");
       System.out.println("¿Cuantos Productos Faltan en el Primer Almacen [0] ?"+"---->"+ almacenes[0][1]+","+ "Productos."); // -->
        System.out.println("¿Cuantos Productos Faltan en el Tercer Almacen [2] ?"+"---->"+ almacenes[2][1]+","+ "Productos.");
        System.out.println("================================================");
                System.out.println("==========================================");
        /*
        0 0 0
        1 0 0
        2 + +
        */
      // Ejemplo:  almacenes [0][1]= 20 --> En el Almacen 0 faltan 5 productos :Ej





        boolean[] array = new boolean[N];
            for ( int i = 2; i < N; i++ ) {
               if (i < N && true ) {
                 // System.out.println("");
                }

                if ( array[i] == true ) {

                    continue;

               }

                // no hay marca, luego marcamos todos los multiplos.
               for (int j = 2*i; j < N; j += i)
                array[j] = true;
               System.out.println(i+","+"Es Número Primo");
            }
        }







    }

