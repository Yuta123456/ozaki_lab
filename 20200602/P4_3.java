import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class P4_3 {
    public static void main( String[]args ) throws Exception {
        Map<String, String[]> map = new HashMap<String, String[]>(); //地図
        map.put( "a", new String[]{ "e" } );
        map.put( "b", new String[]{ "c", "f" } );
        map.put( "c", new String[]{ "b", "d" } );
        map.put( "d", new String[]{ "c", "h" } );
        map.put( "e", new String[]{ "a", "f" } );
        map.put( "f", new String[]{ "b", "e", "j" } );
        map.put( "g", new String[]{ "h" } );
        map.put( "h", new String[]{ "d", "g", "l"} );
        map.put( "i", new String[]{ "j" } );
        map.put( "j", new String[]{ "f", "i" } );
        map.put( "k", new String[]{ "l" } );
        map.put( "l", new String[]{ "h", "k" } );

        String cur = "a";
        String goal = "l";
        ArrayList < String > path = new ArrayList<>();
        path.add(cur);
        if (dfs(cur, goal, path, map)){
            System.out.println(path);
        }

    }
    private static boolean dfs(String cur, String goal, ArrayList<String> path, Map<String, String[]> map){
        if (cur.equals(goal)) {
            return true;
        }
        //System.out.println(path);
        String [] next_array = map.get(cur);
        for(String next : next_array){
            if (path.contains(next)){
                continue;
            }else{
                path.add(next);
                if (dfs(next, goal, path, map)){
                    return true;
                }
                path.remove(next);
            }
        }
        return false;
    }
}