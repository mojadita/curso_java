import org.jetbrains.annotations.NotNull;

public class Main {
    public static void main(@NotNull String[] args) {
        MultilevelTreeMap<String, Integer> map = new MultilevelTreeMap<String, Integer>();
        for (int i = 0; i < args.length; i++) {
             //al = "ad";
           map.put("a",1);
            map.put("b",2);
            map.put("c",1);


            Integer contador = map.get(args[i]);
            if (contador == null)
                contador = new Integer(0);
            map.put(args[i], contador + 1);
        }
        System.out.println(map);

     /*   MultilevelTreeMap<String, Integer> map2 = new MultilevelTreeMap<String, Integer>();
        for (int a = 0; a< args.length; a++){

           // String a = "paco","alvaro","tomas";
            Integer contador2 = map2.get(args[a]);
            if (contador2 == null)
                contador2 == new Integer(0);
            map2.put(args[a], contador2 +1);


        }
        System.out.println(map2);

*/


    } /* EnD Of tHe Main */
}

