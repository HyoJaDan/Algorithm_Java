package main.java.org.swea.Problem1225;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		for(int test_case = 1; test_case <= T; test_case++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int q=Integer.parseInt(st.nextToken());st = new StringTokenizer(br.readLine());
			int[] arr = new int[8];
			for(int i=0;i<8;i++)
				arr[i] = Integer.parseInt(st.nextToken());
			
			while(true) {
				boolean isDone = false;
				for(int i=1;i<=5;i++) {
					int temp = arr[0]-i;
					if(temp<0)  temp=0;
					
					for(int j=1;j<8;j++)
						arr[j-1]=arr[j];
					arr[7]=temp;
					
					if(temp==0) {
						isDone=true;
						break;
					}
				}
				if(isDone)break;
			}
			System.out.printf("#"+test_case+" ");
			for(int i=0;i<8;i++)
				System.out.printf(arr[i]+" ");
			System.out.println();
			
		}
	}
}
