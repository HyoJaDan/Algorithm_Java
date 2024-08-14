package main.java.org.swea.Problem5215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        int T = Integer.parseInt(br.readLine());  // �׽�Ʈ ���̽� ��
	        
	        for (int test_case = 1; test_case <= T; test_case++) {
	            StringTokenizer st = new StringTokenizer(br.readLine());
	            int N = Integer.parseInt(st.nextToken());  // ����� ��
	            int L = Integer.parseInt(st.nextToken());  // Į�θ� ����
	            
	            int[] dp = new int[L + 1];  // Į�θ� ���ѿ� ���� �ִ� ���� ���� �迭
	            
	            for (int i = 0; i < N; i++) {
	                st = new StringTokenizer(br.readLine());
	                int Ti = Integer.parseInt(st.nextToken());  // i��° ����� �� ����
	                int Ki = Integer.parseInt(st.nextToken());  // i��° ����� Į�θ�
	                
	                // �ڿ������� ��ȸ�Ͽ� ���� ��Ḧ ����ϴ� ��츦 ���
	                for (int j = L; j >= Ki; j--) {
	                    dp[j] = Math.max(dp[j], dp[j - Ki] + Ti);
	                }
	            }
	            
	            System.out.println("#" + test_case + " " + dp[L]);
	        }
	}

}
