package org.BackJoon.DP.Problem15681;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static final int MAX = 100005;
    private static ArrayList<Integer>[] Graph;// = new ArrayList[MAX];
    private static int N,R,Q;
    private static int[] problems,DP;
    private static boolean[] visited;
    public static void main(String[] args) throws IOException{
        init();

        visited[R]=true;
        run(R);
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<Q;i++){
            sb.append(DP[problems[i]]).append("\n");
        }
        System.out.println(sb);
    }
    public static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        Graph = new ArrayList[N+1];
        DP = new int[N+1];
        problems = new int[Q+1];
        visited = new boolean[N+1];

        for(int i=0;i<N+1;i++)
            Graph[i] = new ArrayList<>();

        for(int i=0; i<N-1;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            Graph[from].add(to);
            Graph[to].add(from);
        }
        for(int i=0;i<Q;i++)
        {
            st = new StringTokenizer(br.readLine());
            problems[i] = Integer.parseInt(st.nextToken());
        }
    }
    public static int run(int level){

        int now=1;
        for(int i : Graph[level]){
            if(visited[i]) continue;

            visited[i]=true;
            now += run(i);
            visited[i]=false;
        }
        DP[level]=now;
        return now;
    }
}
