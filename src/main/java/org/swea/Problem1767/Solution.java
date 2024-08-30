package main.java.org.swea.Problem1767;

import java.util.*;
import java.io.*;
 
class Solution {
    static class Core {
        int x, y;
 
        public Core(int y, int x) {
            this.y = y;
            this.x = x;
        }   
    }
     
    static int N, cell[][], minWireLength, maxCore;
    static int dx[] = {0, 0, -1, 1}; 
    static int dy[] = {-1, 1, 0, 0};
    static List<Core> coreList; 
     
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
         
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
             
            cell = new int[N][N];
            coreList = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int in = Integer.parseInt(st.nextToken());
                    cell[i][j] = in;

                    if (in == 1 && i != 0 && j != 0 && i != N - 1 && j != N - 1) {
                        coreList.add(new Core(i, j));  // ��, ��
                    }
                }
            }
             
            minWireLength = Integer.MAX_VALUE;
            maxCore = Integer.MIN_VALUE;
             
            startConnect(0, 0, 0);
             
            sb.append("#" + t + " " + minWireLength + "\n");
        }
         
        System.out.println(sb.toString());
    }
     
    public static void startConnect(int idx, int coreCnt, int wireCnt) {
        // ��� �ھ �� ó���� ���
        if (idx == coreList.size()) {  
            if (coreCnt > maxCore) { // �ִ��� ���� core�� ����
                maxCore = coreCnt;
                minWireLength = wireCnt;
            } else if (coreCnt == maxCore) { // ���� ������ ���� �ּҰ� �Ǵ� ��
                minWireLength = Math.min(wireCnt, minWireLength);
            }
            return;
        }

        int x = coreList.get(idx).x;
        int y = coreList.get(idx).y;
         
        // �� �� �� �� Ž��
        for (int dir = 0; dir < 4; dir++) {
            int count = 0, nx = x, ny = y;
            boolean canConnect = true;
             
            while (true) {
                nx += dx[dir];
                ny += dy[dir];
                if (ny < 0 || ny >= N || nx < 0 || nx >= N) {
                    break;
                }
                 
                // ���� �濡 �ٸ� �ھ� Ȥ�� ���� ���� -> �ٸ� ��������
                if (cell[ny][nx] != 0) {
                    canConnect = false;
                    break;
                }

                count++;
            }
            if (canConnect) {
                nx = x;
                ny = y;
                for (int i = 0; i < count; i++) {
                    nx += dx[dir];
                    ny += dy[dir];
                    cell[ny][nx] = 1;
                }
                 
                startConnect(idx + 1, coreCnt + 1, wireCnt + count);

                nx = x;
                ny = y;
                for (int i = 0; i < count; i++) {
                    nx += dx[dir];
                    ny += dy[dir];
                    cell[ny][nx] = 0;
                }
            }
        }
        startConnect(idx + 1, coreCnt, wireCnt);
    }
}
