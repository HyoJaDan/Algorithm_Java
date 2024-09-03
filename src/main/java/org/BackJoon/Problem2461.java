package main.java.org.BackJoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.*;


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
         * currentPosition[n]�쑝濡� �쁽�옱 n踰덉㎏ 諛곗뿴�쓽 �쐞移섍� �떞湲� �쐞移섏뿉�꽌
         * 紐⑤뱺 �뿴�쓣 �깘�깋�븯硫� min怨� max瑜� 媛깆떊 �썑�쟾泥� 理쒖냼媛믨낵 鍮꾧탳
         * O(n) = 1000*1000 = 諛깅쭔
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