int n = 7;
boolean ans_found = false;
void setup(){
  main_1();
}
void main_1(){
  int [] permutation = new int [n];
  for(int i = 1;i < n+1;i++){
    permutation[i-1] = i;
  }
  calc(permutation, n);
}
void calc(int [] array, int count){
  if (ans_found){
    return;
  }
  if(count == 0){
    check(array);
  }else{
    for(int i = 0;i < count;i++){
      int tmp = array[i];
      array[i] = array[count-1];
      array[count-1] = tmp;
      calc(array, count-1);
      tmp = array[i];
      array[i] = array[count-1];
      array[count-1] = tmp;
    }
  }
}
void check(int [] array){
  for(int i = 1;i < n;i = i+2){
    if(array[i-1] + array[i+1] != array[i]){
      return ;
    }
  }
  for(int i = 0;i < n;i++){
    print(" ", array[i]);
  }
  println();
  // I want to exit this program, but the method is not found...
  //So the process became redundancy, sorry
  ans_found = true;
}
