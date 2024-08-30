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

        // �Ӹ���� ���θ� ������ 2���� �迭 �ʱ�ȭ
        isPalindrome = new boolean[N][N];
        dp = new int[N];

        // dp �迭 �ʱ�ȭ (�ʱ� �� -1�� ����)
        for (int i = 0; i < N; i++) {
            dp[i] = -1;
        }

        // �Ӹ���� �̸� ���
        calculatePalindrome();

        // �ּ� ���� �� ã�� ���� DFS ����
        int result = dfs(0);

        // ��� ���
        System.out.println(result);
    }

    public static void calculatePalindrome() {
        // ��� ������ �Ӹ������ �̸� ���
        for (int i = 0; i < N; i++) {
            // ���� 1�� ��� (�ڱ� �ڽ��� �׻� �Ӹ����)
            isPalindrome[i][i] = true;

            // ���� 2�� ���
            if (i < N - 1 && arr[i] == arr[i + 1]) {
                isPalindrome[i][i + 1] = true;
            }
        }

        // ���� 3 �̻��� ���
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
        // ���� ������ �迭�� ���� �����ϸ� �� �̻� ������ �ʿ� ����
        if (start == N) {
            return 0;
        }

        // �̹� ���� ���
        if (dp[start] != -1) {
            return dp[start];
        }

        // �ʱ� �ּ� �� ����
        int minCuts = Integer.MAX_VALUE;

        // ������ ��� ���� �õ�
        for (int end = start; end < N; end++) {
            if (isPalindrome[start][end]) {
                // ���� �κ��� �Ӹ�����̶��, ���� �κ� Ž��
                minCuts = Math.min(minCuts, 1 + dfs(end + 1));
            }
        }

        // ��� ����
        dp[start] = minCuts;
        return minCuts;
    }
}
