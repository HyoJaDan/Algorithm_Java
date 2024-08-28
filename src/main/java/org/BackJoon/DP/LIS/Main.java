package main.java.org.BackJoon.DP.LIS;

import java.io.*;
import java.util.*;

public class Main {
	static int[] arr,index;
	static ArrayList<Integer> LIS = new ArrayList<>();
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1]; index = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) arr[i] = Integer.parseInt(st.nextToken());
		
		LIS.add(arr[0]);
		// 원본 데이터에서 A번째 있는 숫자는 LIS 배열에서 B번째 위치 
		index[0]=0;
		
		for(int i=1;i<N;i++) {
			// 지금 값이 더 커서 뒤에 넣어도 된다면
			if(arr[i] > LIS.get(LIS.size()-1)) {
				LIS.add(arr[i]);
				index[i]=LIS.size()-1;	
			} 
			// 지금 값이 더 작아서 이미 있는 값에 갱신해야 한다면
			else {
				// upperBound를 통해 넣어야할 위치를 찾는다.
				int idx = BS(arr[i]);
				LIS.set(idx, arr[i]); // 값을 업데이트한다..
				index[i] = idx; // 위치를 저장한다
			}
		}
		sb.append(LIS.size()).append("\n");
		
		int lisSize = LIS.size();
		int[] result = new int[lisSize];
		for (int i = N - 1; i >= 0; i--) {
			if (index[i] == lisSize - 1) {
				result[lisSize - 1] = arr[i];
				lisSize--;
			}
		}
		
		for (int i = 0; i < result.length; i++) {
			sb.append(result[i]).append(" ");
		}
		System.out.println(sb);
		
	}
	public static int BS( int target) {
		int left=0, right =LIS.size()-1;
		
		while(left < right) {
			int mid = (left+right)/2;
			
			if( target <= LIS.get(mid)) {
				right = mid;
			}
			else left = mid+1;
		}
		
		
		return right;
	}
}
