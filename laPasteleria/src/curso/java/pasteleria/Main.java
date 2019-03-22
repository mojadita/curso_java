package curso.java.pasteleria;


/**
 * author Chaterz
 */

public class Main {

    public static String[] pasteles = new String[]{
      "Palmera", "Borracho","Duquesa","Ensimada de Crema", "Brazo de Gitano", "Palo de Nata","Bolas de Trufa",
      "Tarta de Queso", "Pastel de Fresas"
    };


    /**
     * @param args
     */

    public static void main( String[] args ) {

       Pasteleria pasteleria = new Pasteleria("La Nata",10,true);
       Cliente cliente1 = new Cliente("Cliente_Miguel");
       //que hace el cliente Miguel ? - Seguir haciendo rutinas para el Cliente
System.out.println("Se ha Creado la Pasteleria llamada:"+pasteleria.m_name);
        System.out.println("Nº de Clientes en la Tienda ahora mismo:"+pasteleria.m_name);
        }
    }
