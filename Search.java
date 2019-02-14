import java.util.Scanner;
import java.io.*;

/*
* Name: Sidharth Naik
* ID: 1647945
* Class: 12B/M
* Date: January 18,2018
* Description: This is the code that contains all the methods for the search and sort.
* File Name: Search.java
* Instructions: After compiling by using the make command in the command line
* type: {Search (filename) Target1, Target2} in the command line to run the code
*/

class Search {

   static int binarySearch(String[] A, int p, int r, String t){
      int midPoint;
      if(p > r) {
         return -1;
      }else{
         midPoint = (p + r)/2;
         if(t.equals(A[midPoint])){
            return midPoint;
         }else if(t.compareTo(A[midPoint]) < 0){
            return binarySearch(A, p, midPoint-1, t);
         }else{ // t > A[midPoint]
            return binarySearch(A, midPoint+1, r, t);
         }
      }
   }

   public static void mergeSort(String[] word, int[] lineNumber, int p, int r){
      int q;
      if(p < r) {
         q = (p+r)/2;
         mergeSort(word, lineNumber, p, q);
         mergeSort(word, lineNumber, q+1, r);
         merge(word, lineNumber, p, q, r);
      }
   }

   public static void merge(String[] word, int[] lineNumber, int p, int q, int r){
      int n1 = q-p+1; //middle - start +1
      int n2 = r-q; // end - middle
      String[] L = new String[n1];
      String[] R = new String[n2];
      int[] L2 = new int[n1];
      int[] R2 = new int[n2];
      int i, j, k;

      for(i=0; i<n1; i++){
         L[i] = word[p+i];
         L2[i] = lineNumber[p+i];
      }
      for(j=0; j<n2; j++){
         R[j] = word[q+j+1];
         R2[j] = lineNumber[q+j+1];
      }

      i = 0; j = 0;
      for(k=p; k<=r; k++){
         if( i<n1 && j<n2 ){
            if( L[i].compareTo(R[j]) < 0 ){
               word[k] = L[i];
               lineNumber[k] = L2[i];
               i++;
            }else{
               word[k] = R[j];
               lineNumber[k] = R2[j];
               j++;
            }
         }else if( i<n1 ){
            word[k] = L[i];
            lineNumber[k] = L2[i];
            i++;
         }else{ // j<n2
            word[k] = R[j];
            lineNumber[k] = R2[j];
            j++;
         }
      }
   }

   public static void main(String[] args) throws IOException {

     if(args.length >= 1){
       try {
         Scanner fileList = new Scanner(new File(args[0]));
       }
       catch(FileNotFoundException e){
         System.err.println(args[0] + " (No such file or directory)");
         System.err.println("Usage: Search file target1 [target2 ..]");
         System.exit(1);
       }
     }

     if(args.length <= 1){
       System.err.println("Usage: Search file target1 [target2 ..]");
       System.exit(1);
     }

     Scanner fileList = new Scanner(new File(args[0]));
     fileList.useDelimiter("\\Z");
     String userFile = fileList.next();
     fileList.close();
     String[] newArray = userFile.split("\n");
     int[] lineNumber = new int[newArray.length];

     for(int i = 0; i < lineNumber.length; i++){
       lineNumber[i] = i+1;
     }

     mergeSort(newArray, lineNumber, 0, newArray.length-1);

     for(int i =1; i < args.length; i++){
       if(binarySearch(newArray, 0, newArray.length-1, args[i]) == -1){
         System.out.println(args[i] + " not found");
       }
       else{
         System.out.println(args[i] + " found on line " + lineNumber[binarySearch(newArray, 0, newArray.length-1, args[i])]);
       }
     }
    }
  }
