package main.java.org.BackJoon.DP.Palindrome.Problem10942;

import java.io.*;
import java.util.*;

class Main{
    static int arr[];
	static int N,M,S,E;
    static Set<Integer>[] set = new HashSet[2001];
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        arr = new int[2001];
        for(int i=0;i<=2000;i++) set[i] = new HashSet<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) arr[i] = Integer.parseInt(st.nextToken());
        
        calculatePalindrome();
        M = Integer.parseInt(br.readLine());
        
        int from, to;
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            
            if(set[from-1].contains(to-1)) sb.append("1").append("\n");
            else sb.append("0").append("\n");
        }
        System.out.println(sb);
    }
    public static void calculatePalindrome(){
    	set[0].add(0);
    	set[2000].add(2000);
       for(int i=1;i<=1999;i++){
    	   set[i].add(i);
           int from=i-1, to = i+1;
           boolean condition1Done=false ;
           while(from>=0 && to <=2000){
        	   if(condition1Done) break;
        	   
               if(!condition1Done && arr[from]==arr[to]){
                   set[from].add(to);
               } else if(!condition1Done && arr[from] != arr[to]){
                   condition1Done=true;
               }
               
               
               from--; to++;
           }
           
           int from2=i-1, to2 = i;
           boolean condition2Done=false;
           
           while(from2>=0 && to2 <=2000){
        	   if(condition2Done) break;

               if(!condition2Done && arr[from2] == arr[to2]){
                   set[from2].add(to2);
               } else if(!condition2Done && arr[from2] != arr[to2  ]){
                   condition2Done=true;
               }
               from2--; to2++;
           }
       }
    }
}