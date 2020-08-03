public abstract class P09_ANode {
    private int value; // 整数値
    P09_ANode(int v) { // コンストラクタ
        value = v;
    }
    public int getValue() {
        return value;
    }
    public abstract List<P09_ANode> getChildren(); // 子のリストを返す抽象メソッド
    public void traverse() {
        // 作成しなさい
    }
    public int getNumNodes() {
        // 作成しなさい
        return getChildren().length;
    }
    public int getMaxValue() {
        // 作成しなさい
        int res = 0;
        for (P09_ANode node : getChildren()) {
            res = Math.max(res, node.getMaxValue());
        }
        return res;
    }
    public int height() {
        // 作成しなさい
        int max_height = 0;
        for (P09_ANode node : getChildren()) {
            max_height = Math.max(max_height, node.height());
        }
        return 1 + max_height;
    }
    public boolean contains(int n) {
        // 作成しなさい
        boolean res = value == n;
        for (P09_ANode node : getChildren()) {
            res |= node.contains(n);
        }
        return res;
    }   
}
class P09_Leaf extends P09_ANode {
    public P09_Leaf(int v) {
    // 完成させなさい.
        super(v);
    }
    @Override
    public List<P09_ANode> getChildren() {
        // 完成させなさい.
        return null;
    }
    // その他必要なメソッドがあれば追加しよう
    @Override
    public void traverse(){
        System.out.println(getValue());
    }
}
//二つ子供を持つノード
class P09_BNode extends P09_ANode {
    private P09_ANode left; // 左の子
    private P09_ANode right; // 右の子
    P09_BNode(int v, P09_ANode leftTree, P09_ANode rightTree) {
    // 完成させなさい
        super(v);
        left = leftTree;
        right = rightTree;
    }
    public void setLeft(P09_ANode leftTree) {
        left = leftTree;
    }
    public void setRight(P09_ANode rightTree) {
        right = rightTree;
    }
    @Override
    public List<P09_ANode> getChildren() {
    // 完成させなさい
        return new List<P09_ANode>(left,right);
    }
    // その他必要なメソッドがあれば追加しよう
    public void traverse(){
        System.out.println(getValue());
        left.traverse();
        right.traverse();
    }
}
//一つ以上の子を持つノード
class P09_Node extends P09_ANode {
    private List<P09_ANode> children;
    public P09_Node(int v) {
        // 完成させなさい     
        super(v);                                                                         
    }
    public void addChild(P09_ANode child) {
        children.add(child);
    }
    @Override
    public List<P09_ANode> getChildren() {
    // 完成させなさい
        return children;
    }
    public void traverse(){
        System.out.println(getValue());
        for (P09_Node node : getChildren()){
            node.traverse();
        }
    }
}