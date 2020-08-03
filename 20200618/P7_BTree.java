public class P7_BTree {
    int value; // ノードが持つ整数値
    P7_BTree leftTree; // 左部分木(左の子)
    P7_BTree rightTree; // 右部分木(右の子)
    /* コンストラクタ */
    P7_BTree(int v, P7_BTree left, P7_BTree right) {
        value = v;
        leftTree = left;
        rightTree = right;
    }
    /* 表示用メソッド ( Java の決まりです ) */
    public String toString() {
        return "[" + value + "]";
    }
    public static void main(String[] args) {
    // 図 (a) に示す2分探索木の生成 ( root が根ノード )
        P7_BTree n5 = new P7_BTree(5, null, null);
        P7_BTree n7 = new P7_BTree(7, null, null);
        P7_BTree n6 = new P7_BTree(6, null, null);
        P7_BTree n4 = new P7_BTree(4, null, n6);
        P7_BTree n1 = new P7_BTree(1, n5, null);
        P7_BTree n2 = new P7_BTree(2, n7, n4);
        P7_BTree n3 = new P7_BTree(3, n1, n2);
        P7_BTree root = n3;
        root.traverse();
        System.out.println("NumNodes = " + root.getNumNodes());
        System.out.println("Sum = " + root.getSumValues());
        System.out.println("MaxValue = " + root.getMaxValues());
        System.out.println("height of tree = " + root.height());

        System.out.println("MinValue = " + root.getMinNodes());
        System.out.println("Tree has " + 9 + " = " + root.contains( 9 ));
        root.reverse();
        root.traverse();
        P7_BTree root2 = root.getReversedTree();
        root2.traverse();
    }
    public void traverse(){
        System.out.print(toString());
        if (rightTree != null){
            System.out.print(" Right:");
            rightTree.traverse();
        }
        if (leftTree != null){
            System.out.print(" Left:");
            leftTree.traverse();
        }
        System.out.println();
    }
    public int getNumNodes(){
        int res = 1;
        if (leftTree != null){
            //なんか冗長的
            res += leftTree.getNumNodes();
        }
        if (rightTree != null){
            //なんか冗長的
            res += rightTree.getNumNodes();
        }
        return res;
    }
    public int getSumValues(){
        int res = value;
        if (leftTree != null){
            //なんか冗長的
            res += leftTree.getSumValues();
        }
        if (rightTree != null){
            //なんか冗長的
            res += rightTree.getSumValues();
        }
        return res;
    }
    public int getMaxValues(){
        int res = value;
        if (leftTree != null){
            //なんか冗長的
            res = Math.max(res,leftTree.getMaxValues());
        }
        if (rightTree != null){
            //なんか冗長的
            res = Math.max(res,rightTree.getMaxValues());
        }
        return res;
    }
    public int getNumLeaves(){
        int res = 0;
        if (leftTree == null && rightTree == null){
            return 1;
        }
        if (leftTree != null){
            //なんか冗長的
            res += leftTree.getNumLeaves();
        }
        if (rightTree != null){
            //なんか冗長的
            res += rightTree.getNumLeaves();
        }
        return res;
    }
    public int height(){
        int res = 0;
        if (rightTree != null){
            res = Math.max(res, rightTree.height());
        }
        if (leftTree != null){
            res = Math.max(res, leftTree.height());
        }
        return res + 1;
    }
    public int getMinNodes(){
        int res = value;
        if (leftTree != null){
            //なんか冗長的
            res = Math.min(res,leftTree.getMinNodes());
        }
        if (rightTree != null){
            //なんか冗長的
            res = Math.min(res,rightTree.getMinNodes());
        }
        return res;
    }
    public boolean contains(int target ){
        boolean res = (value == target);
        if (leftTree != null){
            //なんか冗長的
            res = res || leftTree.contains(target);
        }
        if (rightTree != null){
            //なんか冗長的
            res = res || rightTree.contains(target);
        }
        return res;
    }
    public void reverse(){
        P7_BTree tmp;
        tmp = rightTree;
        rightTree = leftTree;
        leftTree = tmp;
        if (rightTree != null){
            rightTree.reverse();
        }
        if (leftTree != null){
            leftTree.reverse();
        }
    }
    public P7_BTree getReversedTree(){
        P7_BTree node = new P7_BTree(0, null, null);
        node.value = value;
        if (rightTree != null){
            node.leftTree = rightTree.getReversedTree();
        }
        if (leftTree != null){
            node.rightTree = leftTree.getReversedTree();
        }
        return node;
    }
}