package curso.java;


/**
 * Archivo Creado por Chaterz,este es Similar a Main.
 */
public class Main_Chaterz {

    /* lo siguiente se ejecuta y se carga al Inicializar Main_Chaterz*/
    //String chaterz = new String("Archivo Creador por Chaterz");
    static {
        System.out.println("En Primera posición se ejecuta STATIC ");
    }
    String x = "Hello";
    int var1 = 5;
    int next_contador= 0;
    int c_id = next_contador;
    int c_valor1 = 6;
    int c_valor2 = 0;

    {
        next_contador++;
        System.out.println("Inicio del Archivo:"+","+ Main_Chaterz.class); //ok
        System.out.println(next_contador);
        System.out.println("Los Valores cargados al Inicio de este archivo son"+":" +"En la Posición"+":"+next_contador+":"+ x + "," + var1 + ">>>>>" + x);
    }





    @Override
    public String toString() {

        return "Main_Chaterz [ c_id=" + c_id + ", "
                + "var1=" + var1 + "]";
    }

    /* Primer Constructor */
    public Main_Chaterz(int valor) {
        System.out.println( this +"Constructor Nº1"+ valor);
        c_valor1 = valor;
    }

    public Main_Chaterz() {
        //Constructor vacio

    }

    public static void main(String[] args) throws InterruptedException {

        Main_Chaterz v1 = new Main_Chaterz(0);
        Main_Chaterz v2 = new Main_Chaterz(6);

        Thread.sleep( 5000 );
        //pasarán 5 sec antes de que se cree el nuevo objeto v3
        Main_Chaterz v3 = new Main_Chaterz(9);

    }

/**
 * Voy a crear una interrupción antes de los últimos mensajes con un Thread.Sleep(X)
 */

} /*EnD Of File*/
