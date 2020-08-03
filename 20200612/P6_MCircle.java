public class P6_MCircle extends P5_Circle {
    private int dx; // x 方向の速度 (+の場合は画面の右へ,-の場合は画面の左へ移動する)
    private int dy; // y 方向の速度 (+の場合は画面の下へ,-の場合は画面の上へ移動する)
    // 移動速度の設定&獲得
    public void setDx(int value) {
    // (1)x 方向の速度を value にする
        dx = value;
    }
    public void setDy(int value) {
    // (2)y 方向の速度を value にする
        dy = value;
    }
    public int getDx() {
    // (3)x 方向の速度を獲得
        return dx;
    }
    public int getDy() {
    // (4)y 方向の速度を獲得
        return dy;
    }
    // 移動 -> 中心の (X,Y) 座標を,それぞれ (dx,dy) 分移動させる
    public void move() {
    // (5) 作成しなさい
        setDx(getCX() + getDx());
        setDy(getCY() + getDy());
    }
    public String toString() {
    return super.toString() + "/[" + getDx() + "," + getDy() + "]";
    }
}