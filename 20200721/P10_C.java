import java.util.*;
import java.io.*;
class P10_Data extends HashSet<String>{
    String id;
    public P10_Data(String id){
        super();
        this.id = id;
    }
}
interface P10_LocalDistance{//データ間の距離を計算するインタフェース
    double local_dist(P10_Data x, P10_Data y);
}
class P10_LocalDistanceJaccard implements P10_LocalDistance{
    @Override
    public double local_dist(P10_Data x, P10_Data y) {
        // x, y の和集合を求める。
        Set<String> setXorY = new HashSet<>(x);
        setXorY.addAll(y);
        Set<String> setXandY = new HashSet<>(x);
        setXandY.retainAll(y);
        return 1 - ( setXandY.size() * 1.0 / setXorY.size()); //Jaccard 距離(完成させなさい)
    }
}
class P10_LocalDistanceDice implements P10_LocalDistance{
    @Override
    public double local_dist(P10_Data x, P10_Data y) {
        Set<String> setXandY = new HashSet<>(x);
        setXandY.retainAll(y);
        return 1 - (( setXandY.size() * 2.0 ) / ( x.size() + y.size() )) ; // Dice 距離 = 1-Dice Similarity(完成させなさい)
    }
}
//クラスタ (データのリスト)X と クラスタ (データのリスト)Y の距離を計算する.局所距離には lDist を用いる
interface P10_InterClusterDistance{
    double dist(List<P10_Data> X, List<P10_Data> Y, P10_LocalDistance lDist);
}
class P10_InterClusterDistanceShortest implements P10_InterClusterDistance{
    @Override
    public double dist(List<P10_Data> X, List<P10_Data> Y, P10_LocalDistance lDist) {
        //単位元で初期化
        double min_distance = Double.POSITIVE_INFINITY;
        for (P10_Data x : X) {
            for (P10_Data y: Y) {
                min_distance = Math.min(lDist.local_dist(x, y), min_distance);
            }
        }
        return min_distance; //最短距離(完成させなさい)
    }
}
class P10_InterClusterDistanceAvg implements P10_InterClusterDistance{
    @Override
    public double dist(List<P10_Data> X, List<P10_Data> Y, P10_LocalDistance lDist) {
        double averageDistance = 0.0;
        for (P10_Data x : X) {
            for (P10_Data y : Y) {
                averageDistance += lDist.local_dist(x, y);
            }
        }
        return averageDistance / (X.size() * Y.size()); //平均距離(完成させなさい)
    }
}
abstract class P10_D{
    double distance; //デンドログラムの高さ
    //クラスタに含まれるデータをリストにして返す(P0602_InterClusterDistance で利用)
    abstract List<P10_Data> getData();
    P10_D(double d){
        distance = d;
    }
    abstract P10_D getLeft();
    abstract P10_D getRight();
}
//デンドログラムの葉を表すクラス
class P10_DLeaf extends P10_D {
    P10_Data data;
    P10_DLeaf(P10_Data data) {
        super(0.0); //葉なので,高さは 0.0 とする
        this.data = data;
    }
    @Override
    List<P10_Data> getData() {
        List<P10_Data> result = new LinkedList<P10_Data>();
        result.add( data );
        return result;
    }
    
}
    //デンドログラムの中間ノードを表すクラス
class P10_DNode extends P10_D{
    private P10_D left; //左の子
    private P10_D right; //右の子
    P10_DNode(double d, P10_D leftCluster, P10_D rightCluster) {
        super(d);
        left = leftCluster;
        right = rightCluster;
    }
    @Override
    List<P10_Data> getData() {
        List<P10_Data> result = new LinkedList<P10_Data>();
        result.addAll( left.getData() );
        result.addAll( right.getData() );
        return result;
    }
    P10_D getLeft(){
        return left;
    }
    P10_D getRight(){
        return right;
    }
} 
public class P10_C {
    //database 中のデータを,データ間距離 lDist,クラスタ間距離 gDist で階層的クラスタリング
    //得られるデンドログラムの根(ルート)を返す
    public P10_D hclust(List<P10_Data> database, P10_LocalDistance lDist, P10_InterClusterDistance gDist){
        //(1)1 データを 1 クラスタとし,それらをリスト C へ格納
        List< P10_D >clusters = new ArrayList<P10_D>(database.size());
        for(P10_Data data : database){
            //作成しなさい
            clusters.add(new P10_DLeaf(data));
        }
        //すべてのデータが併合されるまで繰り返し(クラスタが 1 つになったら終了)
        while( clusters.size() > 1 ){
            //(2-1) クラスタ間距離が最小となるクラスタの組み合わせ(2 つのクラスタ)を発見
            //単位元で初期化
            double min_distance = Double.POSITIVE_INFINITY;
            int best_cluster_i = -1;
            int best_cluster_j = -1;
            for (int i = 0 ; i < clusters.size(); i++) {
                for (int j = i + 1 ; j < clusters.size();j++) {
                    if ( min_distance > gDist.dist(clusters.get(i).getData(), clusters.get(j).getData(), lDist)){
                        min_distance = gDist.dist(clusters.get(i).getData(), clusters.get(j).getData(), lDist);
                        best_cluster_i = i;
                        best_cluster_j = j;
                    }
                }
            }
            //(2-2) 見つけた組み合わせを併合し,新たなクラスタを作成.それを clusters へ追加
            P10_D remove_i = clusters.get(best_cluster_i);
            P10_D remove_j = clusters.get(best_cluster_j);
            clusters.add(new P10_DNode(min_distance, remove_i, remove_j));
            //(2-3)  併合されてしまった 2 つのクラスタを clusters から削除
            clusters.remove(remove_i);
            clusters.remove(remove_j);
        }
        return clusters.get(0); //clusters の先頭(0 番目)に根が入っている
    }
    public static void main(String[]args){
        List <P10_Data> data  = P10_SampleData.getDatabase("./20200721/alcohol.dat");
        P10_C execute = new P10_C();
        execute.printD(execute.hclust(data, new P10_LocalDistanceJaccard(), new P10_InterClusterDistanceAvg()), 0);
        //execute.printD(execute.hclust(data, new P10_LocalDistanceJaccard(), new P10_InterClusterDistanceShortest()));
        //execute.printD(execute.hclust(data, new P10_LocalDistanceDice(), new P10_InterClusterDistanceAvg()));
        //execute.printD(execute.hclust(data, new P10_LocalDistanceDice(), new P10_InterClusterDistanceShortest()));
    }
    public void printD(P10_D root, int level) {
        for (int i = 0;i < level;i++) {
                System.out.print("  ");
            }
        System.out.println(root.getData());
        if ( root instanceof P10_DNode){
            printD(root.getLeft(), level + 1);
            printD(root.getRight(), level + 1);
        }

    }

}
class P10_SampleData {
    //1 行が 1 つのデータで,各アイテムは,カンマ(",")区切り
    // inFile にファイル名に指定
    static List<P10_Data> getDatabase(String inFile){
        List<P10_Data>database = new ArrayList<P10_Data>();
        try{
            int id = 0;
            String buf;
            BufferedReader br = new BufferedReader(new FileReader(inFile));
            while( (buf = br.readLine()) != null){
                buf = buf.trim(); if(buf.length() == 0){continue;}
                P10_Data data = new P10_Data( "id"+(id++) );
                for(String item : buf.split(",")){
                    data.add(item.trim());
                }
                database.add(data);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        return database;
    }
}