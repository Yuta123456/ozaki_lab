void setup(){
  main_1();
}
void main_1(){
  int n = 100;
  int inf = 10000000;
  int[] coins = {1,5,12};
  //dp[i] := Minimam coins to pay n
  int[] dp = new int[n+1];
  for(int i = 0;i < n+1;i++){
    //init with big number
    dp[i] = inf;
  }
  //dp[0] = 1 because 0 coins are needed to pay 0
  dp[0] = 0;
  for(int i = 0;i < n+1;i++){
    for(int j = 0;j < 3;j++){
      if (i - coins[j] >= 0){
        dp[i] = min(dp[i - coins[j]]+1, dp[i]);
      }
    }
  }
  println(dp[100]);
  for(int i = 0;i < n+1;i++){
    for(int j = 0;j < 3;j++){
      int next_value = coins[j] + i;
      if (next_value <= n){
        dp[next_value] = min(dp[i]+1, dp[next_value]);
      }
    }
  }
  println(dp[n]);
}
