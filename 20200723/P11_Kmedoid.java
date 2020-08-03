import java.util.*;
import java.io.*;
import java.util.Random;
interface P11_CalcDist<T> {
    double distance(T x, T y); //o1, o2 間の距離を計算
}
class P11_ItemSet {
    HashSet< String > item = new HashSet();
    P11_ItemSet( String [] itemList){ /* コンストラクタ */
        for (String data : itemList) {
            this.item.add(data);
        }
    }
    HashSet<String > getItem(){
        return this.item;
    }
}
class P11_ISDistance implements P11_CalcDist<P11_ItemSet>{
    @Override
    public double distance(P11_ItemSet X, P11_ItemSet Y) {
        // x, y の和集合を求める。
        HashSet<String> x = X.getItem();
        HashSet<String> y = Y.getItem();
        Set<String> setXorY = new HashSet<>(x);
        setXorY.addAll(y);
        Set<String> setXandY = new HashSet<>(x);
        setXandY.retainAll(y);
        return 1 - ( setXandY.size() * 1.0 / setXorY.size()); //Jaccard 距離(完成させなさい)
    }
}
public class P11_Kmedoid <T> {
    public Set< List<T> > doClustering(int k, List<T> data, P11_CalcDist<T> measure ){
        List<T> prevCentroids = new ArrayList<T>(k); //前のセントロイド集合(初期値は空)
        Map<T, Cluster<T> > clusters = null; //セントロイド -> クラスタのマップ
        List<T> newCentroids = selectInitialCentroids(k, data); //初期セントロイドの選択
        //Centroid が変化している間は繰り返す
        int count = 0;
        while( !prevCentroids.containsAll(newCentroids) || !newCentroids.containsAll(prevCentroids) ){
            prevCentroids = newCentroids;
            clusters = assignCluster(prevCentroids, data, measure);//今のセントロイドからクラスタを形成
            newCentroids = getCentroids(clusters.values(), measure);//各クラスタから,新しいセントロイドを計算
            System.out.println(count);
            count += 1;
        }
        //単なる後処理:形式を Set<List<T>>に合わせているだけ
        Set< List<T> > result = new HashSet<List<T>>();
        for(Cluster<T> c : clusters.values() ){
            result.add( c.elements );
        }
        return result;
    }
    private List<T> selectInitialCentroids(int k, List<T>data){
        /* 作成しなさい */
        List <T> InitialCentroids = new ArrayList <T>(k);
        for(int i = 0;i < k; i++) {
            InitialCentroids.add(data.get(i));
        }
        Random rand = new Random();
        for (int i = k;i < data.size();i++) {
            int j = rand.nextInt(i);
            if ( j < k ) {
                InitialCentroids.add(j, data.get(i));
            }
        }
        return InitialCentroids;
    }
    /* data 中の各要素を,1 番近い centroid に対応付けることで,centroid->Cluster のマップを獲得する
    * key : centroid, value : key に対応するクラスタ (Cluster<T>)
    */
    private Map<T, Cluster<T> > assignCluster(List<T> centroids, List<T>data, P11_CalcDist<T> measure){
        Map<T, Cluster<T>> centroid2cluster = new HashMap<T, Cluster<T>>();
        /* 作成しなさい */
        for (T d : data) {
            double min_distance = Double.MAX_VALUE;
            T nearestData = null;
            for (T cent : centroids) {
                if (min_distance > measure.distance(d, cent)) {
                    nearestData = cent;
                    min_distance = measure.distance(d, cent);
                }
            }
            System.out.println(data.size());
            if (centroid2cluster.get(nearestData) == null) {
                centroid2cluster.put(nearestData, new Cluster<T>());
            }
            centroid2cluster.get(nearestData).addElement(d);
        }
        return centroid2cluster;
    }
    private List<T> getCentroids(Collection<Cluster<T>> cluster, P11_CalcDist<T>measure){
        List<T> centroids = new ArrayList<T>(cluster.size());
        for(Cluster<T> c : cluster){
            centroids.add( c.getCentroid(measure));
        }
        return centroids;
    }
    private class Cluster<T>{
        List<T> elements; //クラスタの要素のリスト
        Cluster(){ //コンストラクタ
            elements = new ArrayList<T>();
        }
        private void addElement(T elm){ //要素の追加
            elements.add(elm);
        }
        /* 局所距離を計算する dist を利用して,セントロイドを計算する */
        T getCentroid(P11_CalcDist<T> measure){
            /* 作成しなさい */
            double minDistance = Double.MAX_VALUE;
            double sumDistance = 0;
            T centroid = null;
            for (T data : elements) {
                for (T target : elements) {
                    // 同じデータの場合は考えないのが定義だが、非類似度が0なはずなので平気（？）
                    sumDistance += measure.distance(data, target);
                }
                if (minDistance > sumDistance) {
                    minDistance = sumDistance;
                    centroid = data;
                }
            }
            return centroid;
        }
    }
    public static void main(String[]args){
        int k = 3; //クラスタ数
        //データセット (データロード部は,前回資料を参考に,各自作成しましょう)
        // データは,retail10K.dat を利用しましょう.
        // このデータは,スペース区切りのデータです
        List<P11_ItemSet> data = new ArrayList<P11_ItemSet>();
        data = P11_SampleData.getDatabase("./20200723/retail10K.dat");
        P11_CalcDist<P11_ItemSet> measure = new P11_ISDistance();//距離計算
        P11_Kmedoid<P11_ItemSet> miner = new P11_Kmedoid<P11_ItemSet>();//Miner の準備
        Set<List<P11_ItemSet>> clusters = miner.doClustering(k, data, measure);
        //クラスタ番号と共に表示する
        int i = 0; //クラスタ番号
        for(List<P11_ItemSet> clust : clusters){
            for(P11_ItemSet itemset : clust){
                System.out.println(i+" : "+itemset);
            }
            i++;
        }
    }
}
class P11_SampleData {
    //1 行が 1 つのデータで,各アイテムは,カンマ(",")区切り
    // inFile にファイル名に指定
    static List<P11_ItemSet> getDatabase(String inFile){
        List<P11_ItemSet>database = new ArrayList<P11_ItemSet>();
        try{
            String buf;
            BufferedReader br = new BufferedReader(new FileReader(inFile));
            int index = 0;
            while( (buf = br.readLine()) != null){
                if ( index > 100 ){
                    break;
                }
                index += 1;
                buf = buf.trim(); if(buf.length() == 0){continue;} 
                String [] addData = new String [10001];
                for(String item : buf.split(" ")){
                    addData[index] = item;
                    
                }
                database.add(new P11_ItemSet(addData));
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        return database;
    }
}