package main.java.org.BackJoon.Problem15650;


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
		run(0,1);
		System.out.println(sb);
	}
	private static void run(int level, int now) {
		if(level==m) {
			for(int i=0;i<m;i++) {
				sb.append(arr[i]);
				sb.append(" ");
			}
			sb.append('\n');
			
		}
		for(int i=now;i<=n;i++) {
			if(visited[i]==true) continue;
			
			visited[i]=true;
			arr[level] = i;
			run(level+1,i);
			visited[i]=false;
			arr[level]=0;
		}
	}

}
