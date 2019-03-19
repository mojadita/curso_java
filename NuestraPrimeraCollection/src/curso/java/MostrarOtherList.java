package curso.java;

public class MostrarOtherList {
    public static void main(String[] args) {
        //Contenedores[1] de [3] Genérica
        OtherList<String> moda = new OtherList<String>(args.length);
        OtherList<String> deportes = new OtherList<String>(args.length);
        OtherList<String> deco = new OtherList<String>(args.length);
        //he creado 3 contenedores para rellenar e iterar.
        // inicializamos la lista
        for (int i = 0; i < args.length; i++)
            moda.set(i, args[i]);
        // la imprimimos
        moda.set(0,"Alfombra");
        moda.set(1,"Lámpara");
        moda.set(2,"Mesa");
        moda.set(3,"Estante");
        moda.get(1);
        moda.get(2);
        System.out.println(moda);
        for (String el : moda)
            System.out.println("[" + el + "]");




    }
}
