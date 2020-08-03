
import java.awt.Graphics;

/**
 * 円(circle)を表すクラス
 * @author tozaki
 *
 */

public class Circle {
	//クラス変数
	static int counter = 0;
	
	//フィールド変数
	int x,y; //中心のx,y座標
	int r; //半径
	int ID; //ID number
	
	//コンストラクタ
	public Circle(int x, int y, int r){
		this.x = x;
		this.y = y;
		this.r= r;
		ID = counter;
		counter++;
	}
	
	//コンストラクタ2 (X,Y)座標のみ指定
	public Circle(int x, int y){
		this(x,y,5);//半径5で生成
	}
	
	
	//情報を表示するメソッド
	public void show(){
		System.out.println(toString());
	}
	
	//出力する情報を獲得するメソッド
	public String toString(){
		return "ID="+ID+" "+"(("+x+","+y+")"+","+r+","+getArea()+","+getCircumference()+")";
	}
	
	//メソッド X座標を獲得
	public int getX(){
		return x;
	}
	
	//Y座標を獲得
	public int getY(){
		return y;
	}
	
	//半径を獲得
	public int getR(){
		return r;
	}
	
	//Graphical Interface用
	public void draw(Graphics g){
		//中心(x,y)から，円の左上のx,y座標を獲得		
		int lx = getX()-getR(); //左上のx座標
		int ly = getY()-getR(); //左上のy座標
		int d = getR()*2; //直径
		
		//Graphicsクラスのオブジェクト g に 
		//(左上のx座標，左上のy座標，幅，高さ)で楕円を書いてもらう
		g.drawOval(lx, ly, d, d); //
	}
	
	/*
	 * 座標(x1,y1)が自分（円）の中にあるか？
	 */
	public boolean isIn(int x1, int y1){
		//自分の(x,y)と与えられた(x1,y1)との距離がr以内なら中にあると判断する
		double diff_X = (double)(getX()-x1);
		double diff_Y = (double)(getY()-y1);
		//Math.sqrt(double) で，doubleの平方根を計算する
		double diff = Math.sqrt( (diff_X*diff_X) + (diff_Y*diff_Y) );
		
		if((double)getR() >= diff){
			return true;
		}else {
			return false;
		}
	}
	
	///// 以下 課題用
	//面積を返すメソッド
	public double getArea(){
		return r * r * Math.PI; //Math.PI はπ（パイ）を表す定数です．
	}
	
	//円周を返すメソッド 
	public double getCircumference(){
		return 2 * r * Math.PI;
	}
	
	//与えられた円と重なるか?
	public boolean isOverlap(Circle c){
		int cx = c.x; //cの中心座標xを得る
		int cy = c.y; //cの中心座標yを得る
		return Math.sqrt(( cx - x )*( cx - x ) + ( cy - y ) *( cy - y )) <= c.r + r;
	}
}
