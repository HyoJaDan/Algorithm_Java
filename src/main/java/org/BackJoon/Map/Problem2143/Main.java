package main.java.org.BackJoon.Map.Problem2143;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int arrA[], arrB[];
	static Map<Long, Long>mapA, mapB; 
	static int T,aSize,bSize;
	public static void main(String[] args) throws IOException{
		init();
		
		for(int i=0;i<bSize;i++) {
			Long now = 0L;
			for(int j=i;j<bSize;j++) {
				now+=arrB[j];
				mapB.put(now, mapB.getOrDefault(now, 0L)+1);
			}
		}
		
		Long ans=0L;
		for(int i=0;i<aSize;i++) {
			Long now = 0L;
			for(int j=i;j<aSize;j++) {
				now+=arrA[j];
				ans+=mapB.getOrDefault(T-now, 0L);
			}
		}

		System.out.println(ans);
	}
	public static void init() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		aSize = Integer.parseInt(br.readLine());
		arrA = new int[aSize+1];
		mapA = new HashMap<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<aSize; i++) {
			arrA[i] = Integer.parseInt(st.nextToken());
		}
		
		bSize = Integer.parseInt(br.readLine());
		arrB = new int[bSize+1];
		mapB = new HashMap<>();
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<bSize;i++) {
			arrB[i] = Integer.parseInt(st.nextToken());
		}
	}
}
