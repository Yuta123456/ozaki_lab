import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class P6_Gui extends JFrame {
    public static void main(String[] args) {// 実行のメイン
        new P6_Gui();
    }
    public P6_Gui() { // コンストラクタ
        super("サンプル"); // 親クラス JFrame のコンストラクタ
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 300);
        add(new SamplePanel(), BorderLayout.CENTER);
        setVisible(true);
    }
    private class SamplePanel extends JPanel implements MouseListener {
        private Random rnd = new Random(); // 乱数生成器
        private List<P6_MCircle> cList = new LinkedList<P6_MCircle>();// 移動する円の集合
        SamplePanel() { // マウスによる動作の設定
            addMouseListener(this);
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON3) {// 右クリック
                rightClick(e); // 右クリックされたら,rightClick() メソッドを呼び出す
            } else if (e.getButton() == MouseEvent.BUTTON1) {// 左クリック
                leftClick(e); // 左クリックされたら,leftClick() メソッドを呼び出す
            }
        }
        // 左クリック -> 生成 or 情報の表示
        void leftClick(MouseEvent e) {
            int x = e.getX(); // クリックされた X 座標
            int y = e.getY(); // クリックされた Y 座標
            boolean clickCircle = false;
            for (P6_MCircle circle : cList) {
            // (1) 円 circle がクリックされた場合,その円の情報を表示し,clickCircle=true とする
                if (circle.contains(x, y)) {
                    clickCircle = true;
                    System.out.println(circle);
                }
            }
            if (!clickCircle) { // 何もないところをクリックした場合
            // (2) クリックした位置を中心とした移動する円を生成し,"cList"へ追加
            // 半径は,5-10 の間でランダムに決める
            // x 方向への移動速度は,-2 or 2 をランダムに決める
            // y 方向への移動速度は,-2 or 2 をランダムに決める
                P6_MCircle c = new P6_MCircle();
                Random rand = new Random();
                c.setR(rand.nextInt(5) + 5);
                c.setDx(rand.nextInt(4) - 2);
                c.setDy(rand.nextInt(4) - 2);
                cList.add(c);
            repaint(); // 表示のために呼びだす
        }
    }
    // 右クリック -> 移動
    void rightClick(MouseEvent e) {
        int width = getSize().width; // 画面の横幅
        int height = getSize().height; // 画面の縦幅
        // ※画面の左上の座標が (0,0),右下の座標が (width,height) となります
        for (P6_MCircle mcircle : cList) {
            mcircle.move(); // 円を移動させる
            // 跳ね返り処理
            int cx = mcircle.getCX();
            int cy = mcircle.getCY();
            int r = mcircle.getR();
            // (3) 中心座標と半径から,上下左右の座標を計算し,必要に応じて跳ね返り処理(=移動方向の反転)を行う
            if (cx + r > width || cx - r < 0) {
                mcircle.setDx(mcircle.getDx() * -1);
            }
            if (cy + r > height || cy - r < 0) {
                mcircle.setDy(mcircle.getDy() * -1);
            }

            }
            repaint();// 描画
        }
        // 描画
        public void paint(Graphics g) {
            super.paint(g);
            for (P6_MCircle mcircle : cList) {
                // 左上の x 座標,左上の y 座標,幅,高さで楕円を書く
                int leftX = mcircle.getCX() - mcircle.getR(); // 左上の X 座標
                int leftY = mcircle.getCY() - mcircle.getR(); // 左上の Y 座標
                int d = mcircle.getR() * 2; // 直径 = 高さ = 幅
                g.drawOval(leftX, leftY, d, d); // 実際の描画
            }
        }
        @Override
        public void mouseEntered(MouseEvent e) {
        }
        @Override
        public void mouseExited(MouseEvent e) {
        }
        @Override
        public void mousePressed(MouseEvent e) {
        }
        @Override
        public void mouseReleased(MouseEvent e) {
        }
    }
}