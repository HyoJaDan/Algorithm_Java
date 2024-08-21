package main.java.org.BackJoon.DivideAndConquer.Problem1074;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N,R,C,ans=0;
	private static int map[][];
	public static void main(String[] args) throws IOException{
		init();
		
		DivideAndConquer(1,N,1,N);
	}
	public static void init() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		N = (int) Math.pow(2, N);

	}
	public static void DivideAndConquer(int left, int right, int top, int bottom) {
		boolean isZ = (right-left!=1) ? false : true;
		if(!isZ) {

			int midCol = (right+left)/2;
			int midRow = (bottom+top)/2;
			
			int temp = (right-left+1)/2;
			temp = temp*temp;
			if(R+1 <= midRow && C+1 <= midCol)
				DivideAndConquer(left,midCol,top,midRow);
			else if( R+1<=midRow && C+1 > midCol) {
				ans += temp-1;
				DivideAndConquer(midCol+1,right,top,midRow);
			}
			else if( R+1>midRow && C+1 <= midCol) {
				ans += temp*2;				
				DivideAndConquer(left,midCol,midRow+1,bottom);
			}
			else if( R+1>midRow && C+1 > midCol) {
				ans += temp*3;				
				DivideAndConquer(midCol+1,right,midRow+1, bottom);
			}
			
		} else {
			zigzegMap(left, right, top, bottom);
		}
	}
	private static void zigzegMap(int left, int right, int top, int bottom) {
		for(int i=top; i<=bottom; i++) {
			for(int j=left; j<=right; j++) {
				if(i==R+1 && j == C+1){
					System.out.println(ans);
					System.exit(0);
				}
				ans++;
			}
		}
	}
}
