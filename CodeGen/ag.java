class ag{
  public static boolean isFirstBigger( int[] list){
    boolean temp;
    temp = true;
    
    
      
      int a;
      int b;
      a = list[0];
      b = list[1];
      
      if(a > b){
        ;
      }else{
        temp = false;
      }
     
    return temp;
    
    
  }
  
  public static void main(String[] args) {
  
    int[] list = {5,1, 2, 3, 4, 1, 2, 3};
    boolean eli;
    eli = isFirstBigger(list);
  }
  
  
  
  
  
}