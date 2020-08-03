import java.util.*;
public class p5_1 {
    public static void main(String args[]){
        Map<String, String[]> map = new HashMap<String, String[]>(); //地図
        map.put( "a", new String[]{ "e", "i" } );
        map.put( "b", new String[]{ "c", "f" } );
        map.put( "c", new String[]{ "b", "d" } );
        map.put( "d", new String[]{ "c", "h" } );
        map.put( "e", new String[]{ "a", "f" } );
        map.put( "f", new String[]{ "b", "e", "j" } );
        map.put( "g", new String[]{ "h" } );
        map.put( "h", new String[]{ "d", "g", "l", "j"} );
        map.put( "i", new String[]{ "j", "a"} );
        map.put( "j", new String[]{ "f", "i", "h" } );
        map.put( "k", new String[]{ "l" } );
        map.put( "l", new String[]{ "h", "k" } );
        System.out.println(getNeighbors("h", 1, map));
    }
    private static Set getNeighbors(String x, int n , Map<String, String[]> map){
        Set<String> canRearch = new HashSet<String>();
        canRearch.add(x);
        if (n <= 0){
            return canRearch;
        }
        String [] next_array = map.get(x);
        for (String next : next_array) {
            canRearch.addAll(getNeighbors(next, n-1, map));
        }
        return canRearch;

    }

}