package org.BackJoon.Problem2014;

import java.io.*;
import java.util.*;
public class Main {
    // k개 소수의 곱중에서 N  번째를 구하자.
    static int k,n, visited[];
    static List<Integer> list = new ArrayList<>();
    static int arr[];
    public static void main(String[] args) throws IOException{
        init();

        int now=0;
        while (++now != n) {
            int minValue = Integer.MAX_VALUE;
            int minIndex=0;
            for(int i=0; i<k; i++){
                int current;
                if(visited[i]==0) current= arr[i];
                else current = arr[i] * visited[i];

                if(current < minValue){
                    minValue = current;
                    minIndex = i;
                }
            }
            visited[minIndex]++;
            now = minValue;
            System.out.print(now + " ");
        }
        System.out.println(now);
    }
    public static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        visited = new int[k];
        arr = new int[k];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<k;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int standard = arr[k-1];
        for(int j=0;j<k;j++) {
            int now = arr[j];
            for (int i = 0; i < k; i++) {
                if (Math.pow(now, visited[i]) < standard) {
                    list.add((int) Math.pow(now, visited[i]));
                } else {
                    break;
                }
            }
        }
    }
}
