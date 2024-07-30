package org.BackJoon.dataStructure;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
<<<<<<< HEAD
import java.util.PriorityQueue;
import java.util.StringTokenizer;
=======
import java.util.*;
>>>>>>> 58b1bc4cd18edba57da3abd199e6f21604810e53

public class Problem2461 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int arr[][] = new int[n][m];
        int[] currentPosition = new int[n];
        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0;i<n;i++)
        {
            Arrays.sort(arr[i]);
        }

        /**
         * currentPosition[n]으로 현재 n번째 배열의 위치가 담긴 위치에서
         * 모든 열을 탐색하며 min과 max를 갱신 후전체 최소값과 비교
         * O(n) = 1000*1000 = 백만
         */
        int ans=Integer.MAX_VALUE;
        while(true) {
            int minValue = arr[0][currentPosition[0]];
            int maxValue = arr[0][currentPosition[0]];
            int minPosition=0;
            for(int i=0;i<n;i++)
            {
                if(arr[i][currentPosition[i]] < minValue){
                    minValue = arr[i][currentPosition[i]];
                    minPosition=i;
                }

                if(arr[i][currentPosition[i]] > maxValue){
                    maxValue = arr[i][currentPosition[i]];
                }
            }
            if(maxValue - minValue < ans) ans = maxValue-minValue;

            if(++currentPosition[minPosition] >=m )break;


        }
        System.out.println(ans);
    }
}