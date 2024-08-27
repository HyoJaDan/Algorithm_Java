package main.java.org;

import java.util.*;

public class Study {

	static int N = 5; // 원소의 개수
	static boolean[] visited = new boolean[N]; // 원소가 부분집합에 포함되는지 여부를 저장하는 배열
	static char[] ans = new char[5];
	public static void run(int level, int flag){
		if( level == 3){
			System.out.println(ans);
			return;
		}

		for(int i=1;i<=5;i++){
			if((flag & 1 << i) !=0) continue;
			ans[level]= (char) (i+'0');
			run(level+1, flag | 1<< i);
		}
	}

	public static void main(String[] args){
		run(0,0);
	}
}
