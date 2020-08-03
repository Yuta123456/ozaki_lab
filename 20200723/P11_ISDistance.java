import java.util.*;

import javax.print.attribute.HashPrintServiceAttributeSet;

import java.io.*;

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
class P11_main{ //動作を確認するためのクラス
    public static void main(String[]args){
        P11_ItemSet p1 = new P11_ItemSet(new String[]{"yuta", "hishinuma","bunta"});
        P11_ItemSet p2 = new P11_ItemSet(new String[]{"yuta", "hishinuma","kuroko"});
        P11_ISDistance d = new P11_ISDistance();
        double distance = d.distance(p1, p2);
        System.out.println(distance);
    }
}
