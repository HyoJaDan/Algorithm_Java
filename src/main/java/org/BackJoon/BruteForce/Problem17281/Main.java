package main.java.org.BackJoon.BruteForce.Problem17281;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static boolean[] visited = new boolean[10];
	private static int[] personList = new int[10];
	private static int[][] TaYul;
	private static int N, ans=Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException{
		init();
		bruteForce(1); 
		System.out.println(ans);
	}
	public static void init() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		TaYul = new int[N+1][10];
		StringTokenizer st;
		for(int i=1;i<=N;i++){
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=9;j++) {
				TaYul[i][j]= Integer.parseInt(st.nextToken());
			}
		}
		personList[4]=1;
		visited[1]=true;
	}
	public static void bruteForce(int level) {
		if(level==9){
			int currentAns = getPoint();
			ans = Math.max(currentAns, ans);
			return;
		}
		
 		for(int i=2;i<=9;i++) {
   			if(visited[i]==true)continue;
   			
   			visited[i]=true;
   			
  			if(level >=4) {
				personList[level+1]=i;
				bruteForce(level+1);
				personList[level+1]=0;
			}else {
				personList[level]=i;
				bruteForce(level+1);
				personList[level]=0;
			}
  			
  			visited[i]=false;
		}
	}

    public static int getPoint() {
        int result = 0;
        int[] mount = new int[4];
        int outCount = 0;
        int idx = 1;
        int ini = 1;

        while (ini <= N) {
            switch (TaYul[ini][personList[idx]]) {
                case 4:  // 홈런
                    result++;
                    for (int i = 1; i < 4; i++) {
                        if (mount[i] != 0) {
                            mount[i] = 0;
                            result++;
                        }
                    }
                    break;
                case 3:  // 3루타
                    for (int i = 3; i >= 1; i--) {
                        if (mount[i] != 0) {
                            mount[i] = 0;
                            if (i + 3 > 3) {
                                result++;
                            } else {
                                mount[i + 3] = 1;
                            }
                        }
                    }
                    mount[3] = 1;
                    break;
                case 2:  // 2루타
                    for (int i = 3; i >= 1; i--) {
                        if (mount[i] != 0) {
                            mount[i] = 0;
                            if (i + 2 > 3) {
                                result++;
                            } else {
                                mount[i + 2] = 1;
                            }
                        }
                    }
                    mount[2] = 1;
                    break;
                case 1:  // 1루타
                    for (int i = 3; i >= 1; i--) {
                        if (mount[i] != 0) {
                            mount[i] = 0;
                            if (i + 1 > 3) {
                                result++;
                            } else {
                                mount[i + 1] = 1;
                            }
                        }
                    }
                    mount[1] = 1;
                    break;
                case 0:  // 아웃
                    outCount++;
                    if (outCount == 3) {
                        ini++;
                        outCount = 0;
                        Arrays.fill(mount, 0);
                    }
                    break;
            }
            idx = (idx % 9) + 1;  // 타순 순환
        }
        return result;
    }
}
