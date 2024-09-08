package main.java.org.BackJoon.Problem15961;

import java.io.*;
import java.util.*;

public class Main {
	static int n,d,k,c,ans=1, maxValue = Integer.MIN_VALUE;
	static int[] arr, visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        arr = new int[n+k-1];
        visited = new int[d+1];
        visited[c]++;
        
        int left=0,right=0;        
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        int now=n;
        for(int i=0;i<k-1;i++) {
        	arr[now++]=arr[i];
        }
        while(right<n+k-1) {
            if(right-left < k){
	            if(visited[arr[right]]==0){
	            	ans++; 
	            	if(ans>maxValue) maxValue = ans;
	            } 
	            visited[arr[right]]++;
	                
	            right++;
            } 
            if(right-left >= k){
                visited[arr[left]]--;
                if(visited[arr[left]]==0){
                    ans--;
                }
                left++;
            }
        }
        System.out.println(maxValue);
	}
}

