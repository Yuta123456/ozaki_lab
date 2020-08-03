void setup(){
  main_1();
}
void main_1(){
  int inf = 1000000;
  int n = 10;
  int[] a = {1,2,3,4,5,6,7,8,9,10};
  int[] b = {5,7,3,4,0,9,1,2,3,51};
  int[][] dp = new int [n+1][n+1];
  for(int i = 0;i < n+1;i++){
    for(int j = 0;j < n+1;j++){
      dp[i][j] = inf;
    }
  }
  dp[0][0] = 0;
  for(int i = 1;i < n+1;i++){
    for(int j = 1;j < n+1;j++){
      dp[i][j] = abs(a[i-1] - b[j-1]) + min(dp[i][j-1], dp[i-1][j-1], dp[i-1][j]);
    }
  }
  for(int i = 1;i < n+1;i++){
    for(int j = 1;j < n+1;j++){
      print(" ", dp[i][j]);
    }
    println();
  }
  
}
