
import java.util.ArrayList;

public class p5_2 {
    static int board_size = 5;
    public static void main(String args[]){
        int board [][] = new int [board_size][board_size];
        for (int i = 0; i < board_size ; i++) {
            for (int j = 0 ; j < board_size ; j++) {
                board[i][j] = 0; // init number
            }
        }
        if (KnightTour(0,0,1,board)) {
            printBoard(board);
        }
    }
    static boolean KnightTour(int x, int y, int n, int [][] board) {
        // is inside board and not visited (x, y)
        if (x < 0 || x >= board_size || y < 0 || y >= board_size || board[y][x] != 0 ){
            return false;
        }else if (n == 25) {
            board[y][x] = n;
            return true;
        }else{
            board[y][x] = n;
            ArrayList <ArrayList<Integer>> next = new ArrayList<ArrayList<Integer>>();
            for (int i = x - 2; i < x + 3;i++) {
                for (int j = y - 2; j < y + 3;j++) {
                    if (Math.abs(x - i) + Math.abs(y - j) == 3) {
                        ArrayList <Integer> tmp = new ArrayList<Integer>();
                        tmp.add(i);
                        tmp.add(j);
                        next.add(tmp);
                    }
                }
            }
            for (ArrayList<Integer> point : next) {
                if ( KnightTour(point.get(0), point.get(1), n + 1, board) ) {
                    return true;
                }
            }
            board[y][x] = 0;
            return false;
        }
    }
    static void printBoard(int [][] board){
        for (int i = 0;i < board_size ; i++) {
            for (int j = 0 ; j < board_size ; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        } 
    }
}