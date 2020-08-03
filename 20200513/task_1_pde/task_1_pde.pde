void setup(){
  main_1();
}


void main_1(){
  int n = 3;
  //dp[i] := how many path to i-floor
  int[] dp = new int[n+1];
  for(int i = 0;i < n+1;i++){
    dp[i] = 0;
  }
  //dp[0] = 1 , because there is certainly 1-path to 0-floor
  dp[0] = 1;
  for(int i = 0;i < n+1;i++){
    if (i + 2 <= n){
      dp[i+2] += dp[i];
    }
    if(i+1 <= n){
      dp[i+1] += dp[i];
    }
  }
  //print how many path to n-floor
  println(dp[n]);
}
