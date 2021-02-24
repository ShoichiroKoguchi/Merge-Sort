public class Sort {
    private int array[];
    private int arrayh[];
    private int arrayr[];
    public Sort(int n){
	   array = new int[n];
     arrayh = new int[n/2];
     arrayr = new int[n-n/2];
      for(int i=0;i<n;i++){
	    array[i] = (int)(Math.random()*Integer.MAX_VALUE);
	    }
      long start = System.currentTimeMillis();
	    for(int i=0;i<n/2;i++){
	      arrayh[i] = array[i];
	    }
      int m = n/2;
      for(int i=m;i<n;i++){
	      arrayr[(i-m)] = array[i];
	    }
	  //ここからソート呼び出し
    MergeThread m1=new MergeThread(arrayh);
    MergeThread m2=new MergeThread(arrayr);
    m1.start();
    m2.start();
    try{
     m1.join();
     m2.join();
    }catch(InterruptedException e){
    }
    /** ソート結果を得るメソッド */
	  array=merge(m1.array,m2.array,array);
	  //printArray(array); //配列表示
	  long end = System.currentTimeMillis();
	  System.out.println("sort?: "+sortCheck(array)+
			   ", Processing time: "+(end-start)+"ms");
    }

    /** ソートチェック */
    public static boolean sortCheck(int array[]){
	   for(int i=0;i<array.length-1;i++){
	    if(array[i]>array[i+1])return false;
	   }
	   return true;
     }

    /** 確認用の配列表示メソッド */
    public static void printArray(int array[]){
	   for(int i=0;i<array.length;i++){
	    System.out.print(array[i]+" ");
	   }
	   System.out.println();
    }

    public int[] merge(int[] arrayh, int[] arrayr,int[] array){
     int i=0;
     int j=0;
     while(i<arrayh.length || j<arrayr.length){
      if(j==arrayr.length || (i<arrayh.length && arrayh[i]<arrayr[j])){
       array[i+j]=arrayh[i];
       i++;
      }
      else{
       array[i+j]=arrayr[j];
       j++;
      }
     }
     return array;
    }

    public static void main(String args[]){
	   new Sort(100000);
    }


/**
   マージソート
*/

class MergeSort {
  public int array[];
  MergeSort(int[] n){
  array = n;
  sort();
  }
  private void sort(){
   array=mergeSort(array);
  }

/** ソート コンストラクタから自動で実行される */
 private int[] mergeSort(int[] array){
   int n = array.length;
    if(n>1){
      int[] array1=new int[n/2];
      int[] array2=new int[n-n/2];
      for(int i=0;i<n/2;i++){
        array1[i] = array[i];
      }
      int m = n/2;
      for(int i=m;i<n;i++){
        array2[(i-m)] = array[i];
      }
      mergeSort(array1);
      mergeSort(array2);
      merge(array1,array2,array);
    }
    return array;
  }
}
}

class MergeThread extends Thread{
  public int[] array;
  public MergeThread(int[] n){
  array=n;
  }
  public void run(){
  MergeSort m1=new MergeSort(array);
  array=m1.array;
  }
}
