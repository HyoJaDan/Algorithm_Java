package main.java.org.BackJoon.DFS.Problem15649.copy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int n,m;
	private static boolean visited[] = new boolean[10];
	private static int arr[] = new int[10];

	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		run(0);
		System.out.println(sb);
//		sb.append(1).append('\n');
//		sb.append(2).append(3).append(4);
//		System.out.println(sb);
	}
	private static void run(int level) {
		if(level==m) {
			for(int i=0;i<m;i++) {
				sb.append(arr[i]);
				sb.append(" ");
			}
			sb.append('\n');
			
		}
		for(int i=1;i<=n;i++) {
			if(visited[i]==true) continue;
			
			visited[i]=true;
			arr[level] = i;
			run(level+1);
			visited[i]=false;
			arr[level]=0;
		}
	}

}
