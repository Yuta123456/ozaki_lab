int n = 7;
void setup(){
  main_1();
}
void main_1(){
  //init all variable
  //firld[i][j] = 1 means there is queen at (i,j)
  //can_locate[i][j] = true means queen can locate at (i,j)
  int firld [][] = new int [n][n];
  boolean can_locate [][] = new boolean [n][n];
  for(int i = 0;i < n;i++){
    for(int j = 0;j < n;j++){
      firld[i][j] = 0;
      can_locate[i][j] = true;
    }
  }
  calc(firld, can_locate, 0);
}
void calc(int [][] firld, boolean [][] can_locate, int num){
  println();
  if(num == n){
    print_array(firld);
  }else{
    for(int i = 0;i < n;i++){
      println();
      if(can_locate[i][num]){
        firld[i][num] = 1;
        //copy array
        boolean [][] tmp_1 = copy_boolean_array(can_locate);
        int [][]tmp_2 = copy_int_array(firld);
        calc(tmp_2, change_can_locate(tmp_1, i, num), num+1);
        firld[i][num] = 0;
      }
    }
  }
}
boolean [][] change_can_locate(boolean [][] array, int y, int x){
  for(int i = 0;i < n;i++){
    array[y][i] = false;
    array[i][x] = false;
    if(0 <= x - i){
      if (y + i < n){
        array[y+i][x-i] = false;
      }
      if(y - i >= 0){
        array[y-i][x-i] = false;
      }
    }
    if(x + i < n){
      if (y + i < n){
        array[y+i][x+i] = false;
      }
      if (y - i >= 0){
        array[y-i][x+i] = false;
      }
    }
  }
  return array;
}
void print_array(int[][]array){
    for(int i = 0;i < n;i++){
      for(int j = 0;j < n;j++){
        print(" ", array[i][j]);
      }
    println();
  }
}
int[][] copy_int_array(int[][] src) {
  int[][] a = src.clone();
  for (int i = 0; i < src.length; i++) {
    a[i] = src[i].clone();
  }
  return a;
}
boolean[][] copy_boolean_array(boolean[][] src) {
  boolean[][] a = src.clone();
  for (int i = 0; i < src.length; i++) {
    a[i] = src[i].clone();
  }
  return a;
}
