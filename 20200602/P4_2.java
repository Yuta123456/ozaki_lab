import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
public class P4_2 {
    public static void main(String[]args) throws Exception {//throws Exception は,14 章「例外」で学びます
        String file = "./04-animals.dat"; //animals.dat ファイルを,プログラムの実行ディレクトリに配置すること
        BufferedReader br = new BufferedReader(new FileReader(file));//ファイルを開く
        String line;
        Map< String , Integer > dict = new HashMap<>();
        while( (line = br.readLine() ) != null ){ //「1 行読み込み line へ代入する」をファイルの最後まで繰り返す
            String[]wordArray = line.split(" "); //読み込んだ 1 行分のデータ (line) をスペースで区切り,配列にする
            for(String word : wordArray ){ //拡張 for 文を用いて,一つずつ表示
                if (dict.containsKey(word)) {
                    dict.put(word, dict.get(word) + 1);
                }else{
                    dict.put(word, 1);
                }
            }
        }
        br.close(); //ファイルを閉じる
        for (String key : dict.keySet()) {
            System.out.println(key + ":" + dict.get(key));
        }
    }
}