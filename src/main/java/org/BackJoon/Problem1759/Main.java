package main.java.org.BackJoon.Problem1759;
/**
 * 2024-08-28 09:56 ~ 10:18
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static char[] arr,path;
	private static int L,C;
	private static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[C];
		path = new char[C];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<C;i++) {
			arr[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(arr);
		
		dfs(0,0);
		System.out.println(sb);
	}
	private static void dfs(int level, int now) {
		if(level==L) {
			int numOfMoum=0,numOfJaum=0;
			for(int i=0;i<L;i++) {
				if(path[i]=='a' || path[i]=='e' || path[i]=='i' || path[i]=='o'|| path[i]=='u') {
					numOfMoum++;
				}else numOfJaum++;
			}
			if(numOfMoum>=1 && numOfJaum>=2) {
				for(int i=0;i<L;i++)
					sb.append(path[i]);
				sb.append("\n");
			}
				
			
			return;
		}
		
		for(int i=now; i<C;i++) {
			path[level]=arr[i];
			dfs(level+1, i+1);
		}
	}
}
