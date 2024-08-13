package main.java.org.swea.Problem1210;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static int map[][] = new int[100][100];
	
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int test_case = 1; test_case <= 10; test_case++)
		{
			int temp = Integer.parseInt(br.readLine());
			
			int y = 0, x=0;
			for(int i=0;i<100;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<100;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
					if(map[i][j]==2) {
						y=i;x=j;
					}
				}
			}
			int last=0;//x=1,y=2
			while(y>=1) {

				if(x!=99 && map[y][x+1] ==1 && last != 2)
				{
					x=x+1;
					last=1;
				}	
				else if(x != 0 && map[y][x-1]==1 && last !=1) {
					x=x-1;
					last=2;
				}
				else {
					y--;
					last=0;
				}
			}
			System.out.println("#"+temp+" "+x);
		}
	}

}
