package main.java.org.BackJoon.Problem12891;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int s,p;
	static String DNA;
	static char[] arrChar= {'A','C','G','T'}; //A C G T
	static int[] arrInt= new int[4]; 
	static int[] ansInt= new int[4]; 
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		p = Integer.parseInt(st.nextToken());
		
		DNA = br.readLine();
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<4;i++) {
			arrInt[i] = Integer.parseInt(st.nextToken());
		}
		int left=0,right=p;
		
		for(int i=left; i< right; i++) { 
			for(int j=0;j<4;j++) {
				if(DNA.charAt(i) == arrChar[j]) 
					ansInt[j]++;
			}
		}
		int ans=0; // 출력할 답
		
		boolean isAns=true;
		for(int i=0;i<4;i++) {
			if(ansInt[i] < arrInt[i])
				isAns=false;
		}
		if(isAns)
			ans++;

		while(right < s) {
 			for(int i=0;i<4;i++) {
  				if(DNA.charAt(right) == arrChar[i]) {
					ansInt[i]++;
  				} 
  				if(DNA.charAt(left) == arrChar[i]) {
					ansInt[i]--;
				}
			}
			isAns=true;
			for(int i=0;i<4;i++) {
				if(ansInt[i] < arrInt[i])
					isAns=false;
			}
			if(isAns)
				ans++;
			
			
			
			right++;
			left++;
		}
		System.out.println(ans);
	}

}
