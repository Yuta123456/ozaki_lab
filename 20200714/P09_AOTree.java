public abstract class P09_AOTree {
    String label; //ノードが持つラベル
    abstract void traverse(int depth); //木を辿って出力する抽象メソッド
    abstract boolean getValue(); //評価値
    public P09_AOTree(String _label) {//コンストラクタ
        label = _label;
    }
    void traverse(){
        traverse(0);
    }
    void toString(int tab){
        for(int i = 0; i < tab; i++){
            System.out.print(" ");
        }
        System.out.println(label);
    }
    public static void main(String[]args){
        P09_AOTree c = new P09_TF("C", true); //例題の木を生成
        P09_AOTree d = new P09_TF("D", false);
        P09_AOTree g = new P09_TF("G", false);
        P09_AOTree h = new P09_TF("H", true);
        P09_AOTree i = new P09_TF("I", true);
        P09_AOTree f = new P09_OR("F",g,h);
        P09_AOTree b = new P09_OR("B",c,d);
        P09_AOTree e = new P09_AND("E",f,i);
        P09_AOTree a = new P09_AND("A", b,e);
        a.traverse(); //木の出力
        System.out.println("Value = " + a.getValue() );//真偽値の計算・出力
    }
}
    /* 「葉ノード」を表すクラス */
class P09_TF extends P09_AOTree {
    boolean TF; //葉ノードが持つ真偽値
    public P09_TF(String _label, boolean _TF) {
        super(_label);
        TF = _TF;
    }
    @Override
    void traverse(int depth){
        toString(depth);
    }
    @Override
    boolean getValue(){
        return TF;
    }
}
/* 「中間ノード」を表す抽象クラス */
abstract class P09_AO extends P09_AOTree {
    P09_AOTree left; //左の子
    P09_AOTree right; //右の子
    //コンストラクタ
    public P09_AO(String _label, P09_AOTree _left, P09_AOTree _right) {
        super(_label);
        left = _left;
        right = _right;
    }
    @Override
    void traverse(int depth){
        toString(depth);
        left.traverse(depth+1);
        right.traverse(depth+1);
    }
}
    /* AND ノードを表すクラス */
class P09_AND extends P09_AO {
    public P09_AND(String _label, P09_AOTree _left, P09_AOTree _right) {
        super(_label, _left, _right);
    }
    @Override
    boolean getValue() {
        return left.getValue() && right.getValue();
    }
}
    /* OR ノードを表すクラス */
class P09_OR extends P09_AO {
    public P09_OR(String _label, P09_AOTree _left, P09_AOTree _right) {
        super(_label, _left, _right);
    }
    @Override
    boolean getValue() {
        return left.getValue() || right.getValue();
    }
}