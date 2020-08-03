import java.util.Random;
public class P5_Circle {
    private int cx; //中心の x 座標
    private int cy; //中心の y 座標
    private int r; //半径
    //x 座標の設定
    public void setCX(int value){
        cx = value;
    }
    //y 座標の設定
    public void setCY(int value){
        cy = value;
    }
    //半径の設定
    public void setR(int value){
        r = value;
    }
    //X 座標の獲得
    public int getCX(){
        return cx;
    }
    //Y 座標の獲得
    public int getCY(){
        return cy;
    }
    //半径の獲得
    public int getR(){
        return r;
    }
    //問題 (1) この円が点 (x,y) を含むか否かを返す関数
    public boolean contains(int x, int y){
    //作成しなさい
        return (getCX() - x) * (getCX() - x) + (getCY() - y) * (getCY() - y) < getR() * getR();
    }
    //問題 (2) この円が,他の円 another と重なるか否かを返す関数
    public boolean overlap( P5_Circle another ){
    //作成しなさい
        return dist(another.getCX(), another.getCY()) < another.getR() + getR();
    }
    //この円の中心と、点の距離を返す関数。
    public double dist(int x, int y) {
        return Math.sqrt((x - getCX()) * (x - getCX()) + (y - getCY()) * (y - getCY())) ;

    }

    //オブジェクトを表示する際に利用されるメソッド(Java の決まりごとです)
    public String toString(){
        return "("+getCX()+","+getCY()+","+getR()+")";
    }
    public static void main( String [] args ){
        P5_Circle [] cArray = new P5_Circle[ 10 ]; //要素数 10 の配列を準備
        //問題 (3)
        Random rand = new Random();
        for(int i = 0; i < cArray.length; i++){
        //「円」のインスタンスを生成し,配列 cArray に追加する.
        //各「円」に対し,中心座標 (cx,cy) と 半径 r を設定する.
        //なお,値は以下の範囲でランダムに設定すること.
        // -5 <= cx <= 5, -5 <= cy <= 5, 1 <= r <= 3
            cArray[i] = new P5_Circle();
            cArray[i].setCX(rand.nextInt(10) - 5);
            cArray[i].setCY(rand.nextInt(10) - 5);
            cArray[i].setR(rand.nextInt(2) + 1);
        }
        //問題 (4)
        //配列中の「円」のうち,"他の円と重ならない円"をすべて表示する.
        // 表示は System.out.println(「円」オブジェクト) で,「円」オブジェクトが表示できます.

        for (int i = 0; i < cArray.length;i++) {
            boolean display = true;
            for (int j = 0; j < cArray.length;j++) {
                if (i != j && cArray[i].overlap(cArray[j])){
                    display = false;
                    break;
                }
            }
            if (display) {
                System.out.println(cArray[i]);
            }

        }

    }
}
