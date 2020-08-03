
/**
 * キーボードからの入力を受け付けるクラス
 */
import java.io.*;

public class KeyIn {
	
	public static int readInt(String msg) {		
		return Integer.parseInt(readLine(msg));
	}
	public static double readDouble(String msg){
		return Double.parseDouble(readLine(msg));
	}
	public static String readString(String msg){
		return readLine(msg);
	}
	
	
	private static String readLine(String msg){
		String str="";
		try {
			System.out.print(msg);
			BufferedReader br = new BufferedReader(
					new InputStreamReader(System.in));
			str = br.readLine();
		}catch(IOException e){
			e.printStackTrace();
		}
		return str;
	}
}
