
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.util.*;
import java.util.List;

/**
 * クリックされた場所に円を表示する
 * @author tozaki
 *
 */
public class GuiCircle2 extends JFrame{
	private SamplePanel sp;
	
	public static void main(String args[]){
		GuiCircle2 sm = new GuiCircle2();
	}
	
	public GuiCircle2(){
		super("サンプル"); //親クラス(=JFrame)のコンストラクタを呼び出す (継承については次週)
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300,300); 		//サイズを300x300にして
		sp = new SamplePanel(); //SamplePanelクラスのオブジェクト sp を生成し，
		add(sp, BorderLayout.CENTER); //それを貼り付ける
		setVisible(true); //自身を表示
	}
	
	
	public class SamplePanel extends JPanel {
		
		//List / ArrayList については，第4回で解説する
		//ArrayListクラスのオブジェクトcListを生成している．
		//左側がList<Circle>で良いのは，ArraylistはListの一種だから
		//詳しくは，第5,6回で解説
		private List<Circle> cList = new ArrayList<Circle>();
		
		//コンストラクタ
		public SamplePanel(){
			addMouseListener(new SampleMouseListener());
		}
		
		public class SampleMouseListener extends MouseAdapter {
			/**
			 * マウスをクリックしたときの処理
			 */
			public void mousePressed(MouseEvent e){
				int x = e.getX(); //マウスの x 座標
				int y = e.getY(); //マウスの y 座標
				
				boolean circle_exist = false;
				for(int i = 0;i < cList.size();i++){
					if (cList.get(i).isIn(x,y)){
						circle_exist = true;
						cList.get(i).show();
					}
				}
				//cirleが存在しない場合
				if ( !circle_exist ) {
					Circle c = new Circle(x, y, 10); //x,y,半径10で円を生成
					cList.add( c ); //生成した円をリストへ追加
				}
				repaint(); //paintを呼び出す
			}
		}
		//描画処理
		public void paint(Graphics g){
			super.paint(g);//親のpaintを呼び出す(superに関しては次週解説)
	
			/*
			 * 詳しくは後日解説する
			 * Iteratorを使った繰り返しの例
			 * cに順番にオブジェクトが代入されていく
			 */
			Iterator<Circle> it = cList.iterator();
			while(it.hasNext()){
				//この処理で，変数cに順番にオブジェクトが代入される
				Circle c = it.next();			
				c.draw(g); //オブジェクトのメソッド draw(g) を呼び出す
			}
		}
		
	}
	
}
