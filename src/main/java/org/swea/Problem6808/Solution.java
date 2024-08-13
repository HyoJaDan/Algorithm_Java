package main.java.org.swea.Problem6808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static boolean visited[] = new boolean[19];
	static int personA[] = new int[10];
	static int personB[] = new int[10];
	static int personAWin = 0, personBWin=0;
	
	public static void main(String[] args)throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++)
		{
			Arrays.fill(visited, false);
			Arrays.fill(personA, 0);
			Arrays.fill(personB, 0);
			personAWin = 0; personBWin=0;
			

			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=1;i<=9;i++) {
				int inputData = Integer.parseInt(st.nextToken());
				visited[inputData]=true;
				personA[i]=inputData;
			}
			
			run(1);
			System.out.println("#"+test_case+" "+personAWin+" "+personBWin);
		}
	}
	private static void run(int level) 
	{
		if(level==10) 
		{
			int numA=0,numB=0;
			for(int i=1;i<=9;i++) {
				if(personA[i]>personB[i]) {
					numA += personA[i]+personB[i];
				} else if(personA[i]<personB[i])
					numB += personA[i]+personB[i];
			}
			if(numA>numB)	personAWin++;
			else if(numA<numB) personBWin++;
			return;
		}
		
		for(int i=1;i<19;i++) {
			if(visited[i]==true) continue;
			
			visited[i]=true;  
			personB[level]=i;
			run(level+1);
			visited[i]=false;
			personB[level]=0;
		}
	}

}
