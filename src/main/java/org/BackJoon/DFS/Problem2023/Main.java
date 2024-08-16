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
			// ù°�ڸ��� 2,3,5,7�̾߸� �����ϴ�.
			run(1,arr[i]);
		}
	}
	static void run(int level, int now) {
		if(level == MaxLevel) {
			System.out.println(now);
			return;
		}
		
		// ������ 1,2,3,5,7,9 ����� �Ҽ��� �ɼ� ����.
		for(int i=0;i<6;i++) {
			int nowData = now*10+arr[i];
			boolean flag=isPrime(nowData);
			if(!flag)	run(level+1, nowData);
		}
	}
	static boolean isPrime(int now) {
		// �Ҽ� ���ϱ�. 1,2,3,5,7,9���� ���� ���� �Ҽ��� �Ǻ��Ǵ� ��� ��츦 ���ϱ� �ؾ��Ѵ�.
		for(int j=2;j*j<=now;j++) {
			if(now%j==0) {
				return true;
			}
		}
		return false;
	}
}
