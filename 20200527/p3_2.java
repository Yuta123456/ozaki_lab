import java.util.Scanner;
public class p3_2 {
    
    public static void main(String[]args){
        String[] strArray = new String[5];
        Scanner scan = new Scanner(System.in);
        System.out.println("英単語を入力してください");
        strArray[0] = scan.nextLine();
        scan.close();
        strArray[1] = strArray[0].substring(2);
        int x_index = strArray[0].length();
        for(int i = 0; i < strArray[0].length();i++){
            if (strArray[0].charAt(i) == 'x'){
                x_index = i;
            }
        }
        strArray[2] = strArray[0].substring(0,x_index+1);
        strArray[3] = strArray[0].substring(0, 1).toUpperCase() + strArray[0].substring(1).toLowerCase();
        strArray[4] = (strArray[0] + " ").repeat(5);
        for(String str : strArray){
            System.out.println(str);
        }
    }
    
}