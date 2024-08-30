package main.java.org.BackJoon.DP.Palindrome.Problem1509;

import java.io.*;

class Main {
    static char[] arr;
    static boolean[][] isPalindrome;
    static int[] dp;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        arr = br.readLine().toCharArray();
        N = arr.length;

        // 팰린드롬 여부를 저장할 2차원 배열 초기화
        isPalindrome = new boolean[N][N];
        dp = new int[N];

        // dp 배열 초기화 (초기 값 -1로 설정)
        for (int i = 0; i < N; i++) {
            dp[i] = -1;
        }

        // 팰린드롬 미리 계산
        calculatePalindrome();

        // 최소 분할 수 찾기 위해 DFS 시작
        int result = dfs(0);

        // 결과 출력
        System.out.println(result);
    }

    public static void calculatePalindrome() {
        // 모든 길이의 팰린드롬을 미리 계산
        for (int i = 0; i < N; i++) {
            // 길이 1인 경우 (자기 자신은 항상 팰린드롬)
            isPalindrome[i][i] = true;

            // 길이 2인 경우
            if (i < N - 1 && arr[i] == arr[i + 1]) {
                isPalindrome[i][i + 1] = true;
            }
        }

        // 길이 3 이상인 경우
        for (int length = 3; length <= N; length++) {
            for (int start = 0; start <= N - length; start++) {
                int end = start + length - 1;
                if (arr[start] == arr[end] && isPalindrome[start + 1][end - 1]) {
                    isPalindrome[start][end] = true;
                }
            }
        }
    }

    public static int dfs(int start) {
        // 시작 지점이 배열의 끝에 도달하면 더 이상 분할이 필요 없음
        if (start == N) {
            return 0;
        }

        // 이미 계산된 경우
        if (dp[start] != -1) {
            return dp[start];
        }

        // 초기 최소 값 설정
        int minCuts = Integer.MAX_VALUE;

        // 가능한 모든 분할 시도
        for (int end = start; end < N; end++) {
            if (isPalindrome[start][end]) {
                // 현재 부분이 팰린드롬이라면, 다음 부분 탐색
                minCuts = Math.min(minCuts, 1 + dfs(end + 1));
            }
        }

        // 결과 저장
        dp[start] = minCuts;
        return minCuts;
    }
}
