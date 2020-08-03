public abstract class P09_Exp {
    String label; //ノードが持つラベル
    abstract void traverse(int depth); //木を辿って出力する抽象メソッド
    abstract double getValue(); //評価値
    public P09_Exp(String _label) {//コンストラクタ
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
        P09_Exp c = new P09_Leaf("C", 1.2); 
        P09_Exp d = new P09_Leaf("D", -3.8);
        P09_Exp g = new P09_Leaf("G", 2.3);
        P09_Exp h = new P09_Leaf("H", 0.8);
        P09_Exp i = new P09_Leaf("I", 4.2);
        P09_Exp f = new P09_Plus("F",g,h);
        P09_Exp b = new P09_Max("B",c,d);
        P09_Exp e = new P09_Min("E",f,i);
        P09_Exp a = new P09_Mul("A", b,e);
        a.traverse(); 
        System.out.println("Value = " + a.getValue());
    }
}
    /* 「葉ノード」を表すクラス */
class P09_Leaf extends P09_Exp {
    double value;
    public P09_Leaf(String _label, double v) {
        super(_label);
        value = v;
    }
    @Override
    void traverse(int depth){
        toString(depth);
    }
    @Override
    double getValue(){
        return value;
    }
}
/* 「中間ノード」を表す抽象クラス */
abstract class P09_Node extends P09_Exp {
    P09_Exp left; //左の子
    P09_Exp right; //右の子
    //コンストラクタ
    public P09_Node(String _label, P09_Exp _left, P09_Exp _right) {
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
class P09_Plus extends P09_Node{
    public P09_Plus(String _label, P09_Exp _left, P09_Exp _right){
        super(_label, _left, _right);
    }
    @Override
    double getValue(){
        return left.getValue() + right.getValue();
    } 
}

    /* AND ノードを表すクラス */
class P09_Mul extends P09_Node {
    public P09_Mul(String _label, P09_Exp _left, P09_Exp _right) {
        super(_label, _left, _right);
    }
    @Override
    double getValue() {
        return left.getValue() * right.getValue();
    }
}

class P09_Max extends P09_Node {
    public P09_Max(String _label, P09_Exp _left, P09_Exp _right) {
        super(_label, _left, _right);
    }
    @Override
    double getValue() {
        return Math.max(left.getValue(), right.getValue());
    }
}
class P09_Min extends P09_Node {
    public P09_Min(String _label, P09_Exp _left, P09_Exp _right) {
        super(_label, _left, _right);
    }
    @Override
    double getValue() {
        return Math.min(left.getValue(), right.getValue());
    }
}