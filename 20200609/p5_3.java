import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class p5_3 {
    public static void main(String[] args) {
        LinkedList<Map<String, String>> database = getDatabase(); // データの読み込み
        // 属性の獲得 (最初のトランザクションに含まれる属性を利用)何となく,リストにしました
        ArrayList<String> attributes = new ArrayList<String>(database.get(0).keySet());
        for(Map<String, String> transaction : database) {// データの表示
            for(String attName : attributes) {
                System.out.print(attName + ":" + transaction.get(attName) + " ");
            }
            System.out.println();
        }
        double play_entropy = entroy(database, "play");
        for(String attName : attributes) {
            Map<String, LinkedList<Map<String, String>>> att_list = new HashMap<String, LinkedList<Map<String, String>>>();
            for(Map<String, String> transaction : database){
                if ( ! att_list.containsKey(transaction.get(attName))){
                    att_list.put(transaction.get(attName), new LinkedList<Map<String,String>>());
                }
                att_list.get(transaction.get(attName)).add(transaction);
            }
            double sum = 0.0;
            int num = att_list.keySet().size();
            for (String value : att_list.keySet()) {
                sum += entroy(att_list.get(value), "play") * num / database.size();
            }
            System.out.println(attName + " : " +  (play_entropy - sum));
        }
    }
    // データベースを返すメソッド
    static LinkedList<Map<String, String>> getDatabase() {
        String[] attributes = new String[] { "outlook", "temperature", "humidity", "windy", "play" };
        String[][] table = new String[][] {
        { "sunny", "hot", "high", "no", "no" },
        { "sunny", "hot", "high", "yes", "no" },
        { "overcast", "hot", "high", "no", "yes" },
        { "rainy", "mild", "high", "no", "yes" },
        { "rainy", "cool", "normal", "no", "yes" },
        { "rainy", "cool", "normal", "yes", "no" },
        { "overcast", "cool", "normal", "yes", "yes" },
        { "sunny", "mild", "high", "no", "no" },
        { "sunny", "cool", "normal", "no", "yes" },
        { "rainy", "mild", "normal", "no", "yes" },
        { "sunny", "mild", "normal", "yes", "yes" },
        { "overcast", "mild", "high", "yes", "yes" },
        { "overcast", "hot", "normal", "no", "yes" },
        { "rainy", "mild", "high", "yes", "no" }, };
        LinkedList<Map<String, String>> database = new LinkedList<Map<String, String>>();
        for(String[] row : table) {
            HashMap<String, String> transaction = new HashMap<String, String>();
            for(int i = 0; i < attributes.length; i++) {
                transaction.put(attributes[i], row[i]);
            }
            database.add(transaction);
        }
        return database;
    }
    // 平均情報量(エントロピー)を計算するメソッド
    static double entroy(LinkedList<Map<String, String>> database, String attName) {
    Map<String, Integer> val2cnt = new HashMap<String, Integer>();
    //attName の属性値ごとに,データがいくつあるかを集計
    for(Map<String, String> transaction : database) {
        if(!transaction.containsKey(attName)) {
            continue;
        }
        String val = transaction.get(attName);
        val2cnt.put(val, val2cnt.containsKey(val) ? val2cnt.get(val) + 1: 1);
    }
    double result = 0.0;
    for(Integer cnt : val2cnt.values()) {
        double p = cnt / (double) database.size(); // 確率を求める
        result += -p * Math.log(p) / Math.log(2); // 底は 2 にしました
    }
        return result;
    }
}