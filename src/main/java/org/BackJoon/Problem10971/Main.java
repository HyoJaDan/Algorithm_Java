package main.java.org.BackJoon.Problem10971;

import java.io.*;
import java.util.*;

class Main{
    static int n;
    static Long outputValue=Long.MAX_VALUE;
    static int[][] map;
    static boolean visited[];
    public static void main(String[] args) throws IOException{
        init();
        
        visited[0]=true;
        BruteForce(0,0,0L);
        System.out.println(outputValue);
    }
    public static void BruteForce(int level, int now, Long ans){
        
    	if(level==n-1){
        	if(map[now][0]==0) return;
        	outputValue = Math.min(outputValue, ans+map[now][0]);
            return;
        }
        for(int i=0;i<n;i++){
        	if(map[now][i]==0)continue;
            if(visited[i]) continue;
            if(outputValue != Long.MAX_VALUE && ans+map[now][i]>outputValue)continue;
            
            visited[i]=true;
            BruteForce(level+1, i, ans+map[now][i]);
            visited[i]=false;
        }
    }
    public static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        map = new int[n][n];
        visited = new boolean[n];
     
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}