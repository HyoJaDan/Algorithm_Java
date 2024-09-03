package main.java.org.BackJoon.Problem14220;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	private static class From {
	    int to;
	    int value;
	    
	    public From(int to, int value) {
	        this.to = to;
	        this.value = value;
	    }
	}
	
    private static int[][] DP;
    private static int N;
    private static ArrayList<From>[] map;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        if(N==1) {
        	N = Integer.parseInt(br.readLine());
        	System.out.println("0");
        	return;
        }
        
        DP = new int[N][N];
        map = new ArrayList[N];  
        for (int i = 0; i < N; i++) {
            map[i] = new ArrayList<From>();
        }

        int value;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                value = Integer.parseInt(st.nextToken());
                if (value == 0) continue;
                map[j].add(new From(i, value));
            }
        }

        for(int i=0;i<N;i++) {
        	for(int j=0;j<N;j++)
        	{
        		if(i==0) {
        			DP[i][j]=0;
        			continue;
        		}
        		
        		int minValue=Integer.MAX_VALUE;
        		for(From now : map[j]) 
        		{
        			int currentDistance = DP[i-1][now.to];
        			
        			//여태까지의 거리가 0이면 못온다고 간주.
        			if(currentDistance==0 && i != 1)
        				continue;
        			int temp= currentDistance + now.value;
        			if(temp<minValue)
        				minValue=temp;
        		}
        		if(minValue==Integer.MAX_VALUE) DP[i][j]=0;
        		else DP[i][j]=minValue;
        	}
        }
        int ans=Integer.MAX_VALUE;
        for(int i=0;i<N;i++) {
        	if(DP[N-1][i]<ans && DP[N-1][i]!=0)
        		ans=DP[N-1][i];
        }
        
        if (ans == Integer.MAX_VALUE) {
            System.out.println("-1");
        } else {
            System.out.println(ans);
        }
    }
}
