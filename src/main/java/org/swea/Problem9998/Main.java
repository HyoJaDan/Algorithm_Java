package main.java.org.swea.Problem9998;

public class Main {
	public static void main(String[] args) {
		int dp[] = new int[10];
		dp[1]=2;
		dp[2]=5;
		for(int i=3;i<=6;i++) {
			dp[i] = dp[i-1]*2 + dp[i-2];
			
			System.out.println("f("+i+")번째 경우의수 : "+dp[i]);
		}
	}
}
