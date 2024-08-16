package main.java.org.BackJoon.DFS.Problem2023;

import java.util.Scanner;

public class Main {
	static int MaxLevel=0;
	static int []path = new int[9];
	private static int[] arr = {1,2,3,5,7,9};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		MaxLevel = sc.nextInt();
		for(int i=1;i<5;i++) {
			// 첫째자리가 2,3,5,7이야만 가능하다.
			run(1,arr[i]);
		}
	}
	static void run(int level, int now) {
		if(level == MaxLevel) {
			System.out.println(now);
			return;
		}
		
		// 어차피 1,2,3,5,7,9 말고는 소수가 될수 없다.
		for(int i=0;i<6;i++) {
			int nowData = now*10+arr[i];
			boolean flag=isPrime(nowData);
			if(!flag)	run(level+1, nowData);
		}
	}
	static boolean isPrime(int now) {
		// 소수 구하기. 1,2,3,5,7,9말고 많은 수로 소수가 판별되니 모든 경우를 구하긴 해야한다.
		for(int j=2;j*j<=now;j++) {
			if(now%j==0) {
				return true;
			}
		}
		return false;
	}
}
