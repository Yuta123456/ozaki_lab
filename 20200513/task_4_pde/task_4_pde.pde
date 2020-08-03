int n = 3;
int [] ans = new int [1];
void setup(){
  main_1();
}
void main_1(){
  int [] empty = new int [0];
  dfs(0,empty);
}
void dfs(int num , int array[]){
  //println(array);
  if (num <= n){
    // use this number
    dfs(num+1, append(array, num));
    //don`t use this number
    dfs(num+1, array);
  }else{
    for(int i = 0;i < array.length;i++){
      print(" ",array[i]);
    }
    println();
  }
}
