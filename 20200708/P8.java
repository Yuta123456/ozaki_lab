import java.util.List;
import java.util.Map;

class P8_Data extends HashMap<String,String> {
    String id; //単に ID をつけてみました
    P8_Data(String str){
        id = str;
    }
    public String getId(){
        return id;
    }
    public String toString(){
        return id+":"+super.toString();
    }
}
class P8_DefaultData {//今回利用するデータを保持するクラス
    static String [] attributes = new String[]{"outlook", "temperature", "humidity", "windy" , "play"};
    static List<P8_Data> getDatabase(){
        String[][] table = new String[][]{
            {"sunny" , "hot" , "high" , "no" , "no"},
            {"sunny" , "hot" , "high" , "yes" , "no"},
            {"overcast" , "hot" , "high" , "no" , "yes"},
            {"rainy" , "mild" , "high" , "no" , "yes"},
            {"rainy" , "cool" , "normal" , "no" , "yes"},
            {"rainy" , "cool" , "normal" , "yes" , "no"},
            {"overcast" , "cool" , "normal" , "yes" , "yes"},
            {"sunny" , "mild" , "high" , "no" , "no"},
            {"sunny" , "cool" , "normal" , "no" , "yes"},
            {"rainy" , "mild" , "normal" , "no" , "yes"},
            {"sunny" , "mild" , "normal" , "yes" , "yes"},
            {"overcast" , "mild" , "high" , "yes" , "yes"},
            {"overcast" , "hot" , "normal" , "no" , "yes"},
            {"rainy" , "mild" , "high" , "yes" , "no"},
        };
        List<P8_Data> database = new LinkedList<P8_Data>();
        int n = 0;
        for(String[] row : table){
            P8_Data transaction = new P8_Data( "d"+n ); n++; //ID の付与
            for(int i = 0; i < attributes.length; i++){
                transaction.put(attributes[i], row[i] );
            }
            database.add( transaction );
        }
        return database;
    }
    static List<String> getAttributes(){
        List<String>result = new ArrayList<String>(attributes.length);
        for(String att : attributes){
            result.add(att);
        }
        return result;
    }
    static String getClassAttribute(){
        return "play";
    }
}





class P8_Node {
    static public P8_Node build(List<P8_Data>database, List<String>attributes, String classAttribute){
        List<String>atts = new ArrayList<String>(attributes);
        atts.remove(classAttribute);
        return new P8_Node(database, atts, classAttribute); //実際に木を作るトップ
    }
    String classLabel; //割り当てられたクラスラベル {sunny, rainly, overcast}など
    String attName; //属性 humidity など この属性が分割テスト
    Map<String, P8_Node> value2child; //「分割テストの結果＝属性値」ごとに，連結されている子ノードを格納
    //新しいデータの予測クラスを返す
    public String predict(P8_Data data){
    }
    /* コンストラクタ 兼 木の生成 */
    private P8_Node(List<P8_Data> database, List<String>attributes, String classAttribute){
        classLabel = getMajorityClassLabel(database, classAttribute); //現時点での Majority をクラスラベルにする
        if( 分割の必要がない ) {
            return;
        }
        attName = bestSplit(database, attributes, classAttribute); //最良属性を選択
        Map<String, List<P8_Data>>val2data = divide(database, attName); //属性値でデータ集合を分割
        List<String>newAttributes = new ArrayList<String>(attributes);
        newAttributes.remove(attName); //今後利用可能な属性
        value2child = new HashMap<String, P8_Node>(); //子ノードを準備
        for(String attValue : val2data.keySet()){
        //各"属性値"ごとに，再帰呼び出しを行う & 子供へ登録
        }
    }
    //最頻クラスラベルの獲得
    private String getMajorityClassLabel(List<P8_Data> database, String classAttribute){
        Map<String, Integer>count = new HashMap<String, Integer>();
        for (P8_Data data : database) {
            String className = data.get(classAttribute);
            if ( count.containsKey(className) ) {
                count.put(className, count.get(className));
            }else{
                count.put(className, 1);
            }
        }
        String maxString = null;
        for (String className : count.keySet()) {
            if (maxString == null){
                maxString = className;
            }else{
                if (count.get(maxString) < count.get(className)){
                    maxString = className;
                }
            }
        }
        return maxString;

    }
    //最良の分割属性の獲得
    private String bestSplit(List<P8_Data>database, List<String>attributes, String classAttribute){
    }
    //属性「値」毎にデータを分割
    private Map<String, List<P8_Data>> divide(List<P8_Data> database, String attName){
        Map<String, List<P8_Data>>split = new Map<String, List<P8_Data>>();
        for (P8_Data data : database){
            String className = data.get(attName);
            if ( split.containsKey(className) ) {
                split.get(className).push(database);
            }else{
                split.put(new LinkedList<P8_Data>());
                split.get(className).push(database);
            }
        }

    }
    //情報利得の計算
    private double infoGain2(List<P8_Data>database, String attName, String className){
    }
    //エントロピー計算
    private double entropy(List<P8_Data> database, String attName){

    }
    public void display(){//表示
        display(0);
    }
    private void display(int n){
    if(value2child == null){
        for(int i = 0; i < n; i++){ System.out.print(" "); }
        System.out.println(classLabel);
        return;
    }
    for(String value : value2child.keySet() ){
        for(int i = 0; i < n; i++){ System.out.print(" "); }
            System.out.println(attName+"="+value);
            P8_Node child = value2child.get(value);
            child.display(n+1);
        }
    }
    //※その他必要なメソッドは，適宜作成しなさい．
}



public class P8 {
    //決定木の構築＆予測
    public static void main(String[]args){
        List<P8_Data> database = P8_DefaultData.getDatabase(); //データの獲得
        List<String>attributes = P8_DefaultData.getAttributes(); //属性リストの獲得
        String classAttribute = P8_DefaultData.getClassAttribute(); //クラス属性の獲得
        P8_Node tree = P8_Node.build(database, attributes, classAttribute); //決定木の構築
        tree.display(); //決定木の表示
        for(P8_Data data : database){ //木を使った，予測値の表示
            System.out.println(data + " -> " + tree.predict(data));
        }
    }
}