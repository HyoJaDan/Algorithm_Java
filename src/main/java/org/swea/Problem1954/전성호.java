package main.java.org.swea.Problem1954;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Àü¼ºÈ£ {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
    	int T = Integer.parseInt(br.readLine());
    	
    	for(int testCase=1; testCase<=T; testCase++) {
    		int n = Integer.parseInt(br.readLine());
    		int[][] map = new int[n][n];
    		int top=0,left=0,right=n-1; int bottom = n-1;
    		int x=n,y=n;
    		int now=1;
    		while(now <= n*n) {
    			for(int i=left;i<=right;i++) 
    				map[top][i]=now++;
    			top++;
    			
    			for(int i=top;i<=bottom;i++)
    				map[i][right]=now++;
    			right--;
    			
    			for(int i=right;i>=left;i--)
    				map[bottom][i]=now++;
    			bottom--;
    			
    			for(int i=bottom;i>=top;i--) 
    				map[i][left]=now++;
    			left++;
    		}
    		System.out.println("#"+testCase);
    		for(int i=0;i<x;i++) {
    			for(int j=0;j<y;j++) {
    				System.out.printf(map[i][j]+" ");
    			}
    			System.out.println();
    		}
    	}
    }
    
}
