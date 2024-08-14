package main.java.org.swea.Problem5215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        int T = Integer.parseInt(br.readLine());  // 테스트 케이스 수
	        
	        for (int test_case = 1; test_case <= T; test_case++) {
	            StringTokenizer st = new StringTokenizer(br.readLine());
	            int N = Integer.parseInt(st.nextToken());  // 재료의 수
	            int L = Integer.parseInt(st.nextToken());  // 칼로리 제한
	            
	            int[] dp = new int[L + 1];  // 칼로리 제한에 따른 최대 점수 저장 배열
	            
	            for (int i = 0; i < N; i++) {
	                st = new StringTokenizer(br.readLine());
	                int Ti = Integer.parseInt(st.nextToken());  // i번째 재료의 맛 점수
	                int Ki = Integer.parseInt(st.nextToken());  // i번째 재료의 칼로리
	                
	                // 뒤에서부터 순회하여 현재 재료를 사용하는 경우를 고려
	                for (int j = L; j >= Ki; j--) {
	                    dp[j] = Math.max(dp[j], dp[j - Ki] + Ti);
	                }
	            }
	            
	            System.out.println("#" + test_case + " " + dp[L]);
	        }
	}

}
