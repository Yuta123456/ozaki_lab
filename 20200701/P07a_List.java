 //各自の環境に合わせて変更する

/*
 * メインクラス
 */
public abstract class P07a_List {
	abstract int getValue();	//値を返す
	abstract P07a_List getCar();	//先頭要素を返す
	abstract P07a_List getCdr();	//残りのリストを返す
	
	abstract int length();			//リストの長さを返す
	abstract int numElements(); 	//リストに含まれる 整数の数を返す
	
	abstract P07a_List append(P07a_List list); //自身の後ろにlistを連結した新たなリストを返す
	abstract P07a_List flatten();	//リストに含まれる整数要素からなる，新たなリストを返す
	
	abstract P07a_List reverse();    //リストを反転させる
	abstract P07a_List reverseAll(); //要素こみでリストを反転させる
	
	//Utility (エラーメッセージを表示し，強制終了)
	static void error(String msg){
		System.err.println(msg);
		System.exit(1);
	}
	
	//Utility 整数配列からリストを作る
	static P07a_List genList(int[]value){
		P07a_List list = P07a_Empty.getInstance();
		for(int i = value.length-1; i >= 0; i--){
			list = new P07a_Internal( new P07a_Value(value[i]), list);
		}
		return list;
	}
	
	//Utility Listを表示する
	static String toString(P07a_List list){
		if( list instanceof P07a_Value){
			return list.getValue()+"";
		}
		if( list instanceof P07a_Empty ){
			return "";
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append("[ ");
		for(P07a_List cur = list; !(cur instanceof P07a_Empty); cur = cur.getCdr() ){
			sb.append(toString(cur.getCar())+", ");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.deleteCharAt(sb.length()-1);
		sb.append(" ]");
		return sb.toString();	
	}
	
	public static void main(String[]args){
		P07a_List list[] = new P07a_List[6];
		list[0] = P07a_List.genList( new int[]{1,2,3} );
		list[1] = P07a_List.genList( new int[]{4,5,6} );
		list[2] = P07a_List.genList( new int[]{7,8,9} );

		list[3] = new P07a_Internal( list[0], list[1]);
		list[4] = new P07a_Internal( list[2], list[3]);
		list[5] = new P07a_Internal( list[3], list[2]);
		
		for(int i = 0; i < list.length; i++){
			System.out.println( P07a_List.toString(list[i]) );
			System.out.println( list[i].length() );
			System.out.println( list[i].numElements());
			System.out.println( P07a_List.toString( list[i].append(list[i]) ) );
			System.out.println( P07a_List.toString( list[i].flatten() ) );
			System.out.println( P07a_List.toString( list[i].reverse() ) );
			System.out.println( P07a_List.toString( list[i].reverseAll() ) );
		}
	}
}

/*
 * 整数ノード
 */
class P07a_Value extends P07a_List {
	
	private int value;
	public P07a_Value(int _value){
		value = _value;
	}
	
	@Override
	public int getValue() {
		return value;
	}
	
	@Override
	public P07a_List getCar() {
        P07a_List.error("Illegal");
		return null;
	}
	
	@Override
	public P07a_List getCdr() {
        P07a_List.error("Illegal");
		return null;
	}
	
	@Override
	public String toString(){
        return getValue()+"";
	}
    
	@Override
	public int length() {
		P07a_List.error("Illegal");
		return 0;
	}
	
	@Override
	public int numElements() {
		return 1;
	}
	
	@Override
	public P07a_List append(P07a_List list) {
		P07a_List.error("Illegal");
		return null;
	}
	
	@Override
	public P07a_List flatten() {
		return new P07a_Internal(this, P07a_Empty.getInstance() );
	}
	
	@Override
	public P07a_List reverse() {
		return this;
	}
	
	@Override
	public P07a_List reverseAll() {
		return this;
	}
}
/*
 * リストの終端ノード
 */
class P07a_Empty extends P07a_List {
	static private P07a_Empty obj = new P07a_Empty();
	private P07a_Empty(){ ;}
	static public P07a_Empty getInstance(){
		return obj;
	}
	
	@Override
	public int getValue() {
		P07a_List.error("error");
		return (int)Double.NaN;
	}
	
	@Override
	public P07a_List getCar() {
		P07a_List.error("error");		
		return null;
	}
	
	@Override
	public P07a_List getCdr() {
		P07a_List.error("error");		
		return null;
	}
	
	@Override
	public String toString(){
		return "[]";
	}
	
	@Override
	public int length() {
		return 0;
	}
	
	@Override
	public int numElements() {
		return 0;
	}
	
	@Override
	public P07a_List append(P07a_List list) {
		return list;
	}
	
	@Override
	public P07a_List flatten() {
		return this;
	}
	
	@Override
	public P07a_List reverse() {
		return this;
	}
	
	@Override
	public P07a_List reverseAll() {
		return this;
	}
}

/*
 * リストの中間ノード(宿題の対象)
 */
class P07a_Internal extends P07a_List {
	private P07a_List car;
	private P07a_List cdr;
	
	public P07a_Internal(P07a_List _car, P07a_List _cdr){
		
		if( _car instanceof P07a_Empty ){
			P07a_List.error("null");			
		}
		car = _car;

		if( !(_cdr instanceof P07a_Internal || _cdr instanceof P07a_Empty) ){ 
			P07a_List.error("null");
		}
		cdr = _cdr;
	}
	
	@Override
	public int getValue() {
		P07a_List.error("Illegal");
		return (int)Double.NaN;
	}
	
	@Override
	public P07a_List getCar() {
		return car;
	}
	
	@Override
	public P07a_List getCdr() {
		return cdr;
	}
	
	@Override
	public String toString(){
		return "[" + getCar() +"|" + getCdr() +"]";
	}
	

	//以降を作成する
	@Override
	public int length() {
		return 1 + getCdr().length();
	}

	@Override
	public int numElements() {
		return getCar().numElements() + getCdr().numElements();
	}

	@Override
	public P07a_List append(P07a_List list) {
		return new P07a_Internal(getCar(), getCdr().append(list));
	}

	@Override
	public P07a_List flatten() {
		return getCar().flatten().append(getCdr().flatten());
	}

	@Override
	public P07a_List reverse() {
		return getCdr().reverse().append(new P07a_Internal(getCar(), P07a_Empty.getInstance()));
	}	
	@Override
	public P07a_List reverseAll() {
		return getCdr().reverseAll().append(new P07a_Internal(getCar().reverseAll(), P07a_Empty.getInstance()));
	}
}