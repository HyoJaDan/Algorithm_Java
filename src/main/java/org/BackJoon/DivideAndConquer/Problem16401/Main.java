package main.java.org.BackJoon.DivideAndConquer.Problem16401;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int NumOfJoka, NumOfSnack;
	static int[] snackLengths;
	public static void main(String[] args) throws IOException{
		init();
		
		int ans = binarySearch();
		System.out.println(ans);
	}
	private static int binarySearch() {

		int answer=0;
		int left=1;
		int right=snackLengths[NumOfSnack-1];
		
		while(left <= right) {
			int mid = (left+right)/2;
			
			int now =0;
			for(int i=0;i<NumOfSnack;i++) {
				now += snackLengths[i]/mid;
			}
			if(now >= NumOfJoka) {
				left = mid+1;
				answer= Math.max(answer, mid);
			} else if(now < NumOfJoka)
				right = mid -1;
		}
		return answer;
	}
	private static void init() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		NumOfJoka = Integer.parseInt(st.nextToken());
		NumOfSnack = Integer.parseInt(st.nextToken());
		snackLengths = new int[NumOfSnack];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<NumOfSnack;i++)
			snackLengths[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(snackLengths);
	}
}
