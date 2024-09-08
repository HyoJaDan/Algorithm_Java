package main.java.org.BackJoon.Problem2839;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int start=n;
		int ans=n/5;
		n%=5;
		
		while(n!=0) {
			if(n%3==0) {
				ans += n/3;
				n%=3;
			} else {
				n+=5;
				ans-=1;
			}
			if(n>start) {
				System.out.println("-1");
				System.exit(0);
			}
		}
		System.out.println(ans);
	}

}
