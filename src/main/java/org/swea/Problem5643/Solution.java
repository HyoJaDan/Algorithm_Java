package main.java.org.swea.Problem5643;
import java.io.*;
import java.util.*;

public class Solution {
    static int n, m;
    static ArrayList<Integer>[] taller, shorter; // taller: �ڽź��� ū �л�, shorter: �ڽź��� ���� �л�
    static int[] tallerCount, shorterCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            // �Է� �ʱ�ȭ
            n = Integer.parseInt(br.readLine());
            m = Integer.parseInt(br.readLine());

            taller = new ArrayList[n + 1];
            shorter = new ArrayList[n + 1];
            tallerCount = new int[n + 1];
            shorterCount = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                taller[i] = new ArrayList<>();
                shorter[i] = new ArrayList<>();
            }

            // Ű �� ���� �Է� �ޱ�
            for (int i = 0; i < m; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                
                // a < b -> a�� b���� �۴�
                taller[a].add(b); // a���� ū �л� b
                shorter[b].add(a); // b���� ���� �л� a
            }

            // ��� �л��� ���� BFS ����
            for (int i = 1; i <= n; i++) {
                bfs(i, true); // �ڽź��� ū �л��� Ž��
                bfs(i, false); // �ڽź��� ���� �л��� Ž��
            }

            // �ڽ��� �� ��°���� �� �� �ִ� �л� �� ���ϱ�
            int result = 0;
            for (int i = 1; i <= n; i++) {
                if (tallerCount[i] + shorterCount[i] == n - 1) {
                    result++;
                }
            }

            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        System.out.print(sb);
    }

    // BFS �Լ�
    public static void bfs(int start, boolean isTaller) {
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            ArrayList<Integer> list = isTaller ? taller[current] : shorter[current];

            for (int next : list) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);

                    if (isTaller) {
                        tallerCount[start]++;
                    } else {
                        shorterCount[start]++;
                    }
                }
            }
        }
    }
}
