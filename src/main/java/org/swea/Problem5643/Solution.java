package main.java.org.swea.Problem5643;
import java.io.*;
import java.util.*;

public class Solution {
    static int n, m;
    static ArrayList<Integer>[] taller, shorter; // taller: 자신보다 큰 학생, shorter: 자신보다 작은 학생
    static int[] tallerCount, shorterCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            // 입력 초기화
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

            // 키 비교 정보 입력 받기
            for (int i = 0; i < m; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                
                // a < b -> a는 b보다 작다
                taller[a].add(b); // a보다 큰 학생 b
                shorter[b].add(a); // b보다 작은 학생 a
            }

            // 모든 학생에 대해 BFS 수행
            for (int i = 1; i <= n; i++) {
                bfs(i, true); // 자신보다 큰 학생들 탐색
                bfs(i, false); // 자신보다 작은 학생들 탐색
            }

            // 자신이 몇 번째인지 알 수 있는 학생 수 구하기
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

    // BFS 함수
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
