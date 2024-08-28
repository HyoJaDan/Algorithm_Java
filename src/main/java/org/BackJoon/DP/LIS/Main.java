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
		// ���� �����Ϳ��� A��° �ִ� ���ڴ� LIS �迭���� B��° ��ġ 
		index[0]=0;
		
		for(int i=1;i<N;i++) {
			// ���� ���� �� Ŀ�� �ڿ� �־ �ȴٸ�
			if(arr[i] > LIS.get(LIS.size()-1)) {
				LIS.add(arr[i]);
				index[i]=LIS.size()-1;	
			} 
			// ���� ���� �� �۾Ƽ� �̹� �ִ� ���� �����ؾ� �Ѵٸ�
			else {
				// upperBound�� ���� �־���� ��ġ�� ã�´�.
				int idx = BS(arr[i]);
				LIS.set(idx, arr[i]); // ���� ������Ʈ�Ѵ�..
				index[i] = idx; // ��ġ�� �����Ѵ�
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
