package main.java.org.BackJoon.Problem2179;


import java.util.*;
import java.io.*;

class Strings{
    String s;
    int num;
    public Strings(String s, int num){
        this.s=s;
        this.num=num;
    }
}
public class Main {
    static List<String> arr = new ArrayList<>();
    static int N,ans=0;
    static String ansA, ansB;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            arr.add(br.readLine());
        }

        Collections.sort(arr);

        for(int i=0;i<N;i++){
            String first = arr.get(i);
            for(int j=i+1;j<N;j++){
                String second = arr.get(j);

                int size = Math.min(first.length(), second.length());
                int current =0;
                for(int k=0;k<size;k++){
                    if(first.charAt(k) == second.charAt(k))
                        current++;
                    else break;
                }

                if(current> ans){
                    ans=current;
                    ansA = first;
                    ansB = second;
                }
            }
        }
        System.out.println(ansA);
        System.out.println(ansB);
    }
}
