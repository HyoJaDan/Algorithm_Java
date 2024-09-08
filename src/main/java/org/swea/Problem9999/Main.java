package main.java.org.swea.Problem9999;

public class Main {
	public static void main(String[] args) {
		int dp[] = new int[10];
		dp[1]=1;
		dp[2]=3;
		int yello=2; int green =1;
		
		for(int i=3;i<=8;i++) {
			dp[i] = yello*2+green;
			yello = yello+green;
			green = dp[i]-yello;
			System.out.println("f("+i+")번째 경우의수 : "+dp[i]);
		}
	
	}
}
