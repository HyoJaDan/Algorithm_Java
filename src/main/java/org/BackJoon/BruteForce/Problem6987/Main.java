package main.java.org.BackJoon.BruteForce.Problem6987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//A=0 B=1 C=2 D=3 E=4 F=5
// win 0 BIgin 1 lose 2
public class Main {
	private static int[][] scores, answers =new int[6][3];;
	private static int[] path = new int[15];
	private static boolean isDone=false;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int tc=0;tc<4;tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			answers = new int[6][3];
			isDone=false;
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
			        answers[i][j] = Integer.parseInt(st.nextToken());
			    }
			}
			
			bruteForce(0);
			if(isDone)System.out.print("1 ");
			else System.out.print("0 ");
		}
	}
	public static void bruteForce(int level) {
		if(level==5) {
			int now =0;
			int temp[] = new int[3];
			while(now<5) {
				
				if(path[now]==0) {
					temp[0]++;
				}
				else if(path[now]==1) {
					temp[1]++;
				}
				else if(path[now]==2) {
					temp[2]++;
				}
				now++;
			}
			for(int j=0;j<3;j++) {
				if(answers[0][j]!=temp[j])
					return;
			}
		}
		if(level==9) {
			scores = new int[6][3];
			int now=0;
			for(int i=0;i<2;i++) {
				for(int j=i+1;j<6;j++) {
					if(path[now]==0) {
						scores[i][0]++;
						scores[j][2]++;
					}
					else if(path[now]==1) {
						scores[i][1]++;
						scores[j][1]++;
					}
					else if(path[now]==2) {
						scores[i][2]++;
						scores[j][0]++;
					}
					now++;
				}
			}
			for(int i=1;i<2;i++	) {
				for(int j=0;j<3;j++) {
					if(answers[i][j]!=scores[i][j])
						return;
				}
			}
			
			
			
		}
		if(level==15) {
			scores = new int[6][3];
			int now=0;
			for(int i=0;i<6;i++) {
				for(int j=i+1;j<6;j++) {
					if(path[now]==0) {
						scores[i][0]++;
						scores[j][2]++;
					}
					else if(path[now]==1) {
						scores[i][1]++;
						scores[j][1]++;
					}
					else if(path[now]==2) {
						scores[i][2]++;
						scores[j][0]++;
					}
					now++;
				}
			}
			for(int i=1;i<6;i++	) {
				for(int j=0;j<3;j++) {
					if(answers[i][j]!=scores[i][j])
						return;
				}
			}
			isDone=true;
			return;
		}
		
		for(int i=0;i<3;i++) {
			path[level]=i;
			bruteForce(level+1);
			path[level]=0;
			
			if(isDone) return;
		}
	}
}
