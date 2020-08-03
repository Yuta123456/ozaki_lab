
/**
 * Circle オブジェクトを生成するサンプルプログラム
 * ※エラー処理は，一切行っていない．
 * @author tozaki
 *
 */

public class ComCircle2 {

	//プログラムは main から始まる
	public static void main(String [] args){
		
		//生成する円(=Circl)の数を得る
		int num = KeyIn.readInt("生成する円の数を指定してください"); 
		
		/*
		 * Circleクラスのオブジェクトを保持するための配列の準備
		 * 配列の生成は
		 * クラス名 [ ] 配列名 = new クラス名[ 数 ];
		 */
		Circle [ ] data = new Circle[ num ];
		//この時点では，「配列」が生成されただけで，配列の要素はnull
		
		/*
		 * 配列の要素を生成
		 * 配列のオブジェクトのフィールド変数 length にアクセスすることで，
		 * 配列の長さを得ることが可能
		 */
		for(int i=0; i<data.length; i++){
			//一つずつ，x, y, r を獲得する
			int x = KeyIn.readInt(i+"番目の円のX座標");			
			int y = KeyIn.readInt(i+"番目の円のY座標");			
			int r = KeyIn.readInt(i+"番目の円の半径");
			
			//x,y,r を使ってオブジェクトを生成し，配列へ 
			//オブジェクトの生成は new により行われる．
			Circle c = new Circle(x,y,r);
			data[i] = c;
		}
		
		/*
		 * 入力情報の表示
		 */
		for(int i = 0; i < data.length; i++){
			Circle c = data[i]; // dataのi番目のオブジェクトに対して
			c.show();	// show()により情報を表示させる．
			
			// 上記の2行は， 以下の1行と同じ．
			//data[i].show();
		}
		
		/*
		 * 以下に課題を追加
		 * 
		 */
		int overrap_circles = 0;
		for(int i = 0;i < data.length;i++){
			boolean overrap_flag = false;
			for(int j = 0;j < data.length;j++){
				if (i != j && data[i].isOverlap(data[j])) {
					overrap_flag = true;
				}
			}
			if (overrap_flag) {
				overrap_circles += 1;
			}
		}
		System.out.println("count of circle which doesn't overrap other circles : " + (data.length - overrap_circles));
	}
	
}
