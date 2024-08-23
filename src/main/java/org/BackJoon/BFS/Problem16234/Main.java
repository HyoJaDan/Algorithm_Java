package main.java.org.BackJoon.BFS.Problem16234;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point{
    int y;
    int x;
    public Point(int y,int x){
        this.y=y;
        this.x=x;
    }
}
public class Main {
    private static int N,L,R;
    private static int[][] map;
    private static int direct[][] = {{-1,0},{1,0},{0,1},{0,-1}};
    private static int ans=0;
    public static void main(String[] args) throws IOException{
        init();

        while(true) {
            boolean[][] visited = new boolean[N][N];
            boolean flag=false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(visited[i][j]) continue;
                    int q = BFS(i,j,visited);
                    if(q>1){
                        flag=true;
                    }

                }
            }
            if(!flag) break;
            ans++;
        }
        System.out.println(ans);
    }
    public static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++)
                map[i][j]=Integer.parseInt(st.nextToken());
        }
    }
    public static int  BFS(int y,int x, boolean[][] visited){
        ArrayList<Point> path = new ArrayList<>();
        Queue<Point> q = new LinkedList<>();
        int total = map[y][x];

        q.add(new Point(y,x));
        path.add(new Point(y,x));
        visited[y][x]=true;

        while(!q.isEmpty()){
            Point now = q.poll();

            for(int i=0;i<4;i++){
                int dy = now.y+direct[i][0];
                int dx = now.x+direct[i][1];

                if(dy<0 || dx<0 || dy>=N || dx>=N) continue;
                if(visited[dy][dx]) continue;

                int temp1 = Math.abs(map[now.y][now.x] - map[dy][dx]);
                if ( !(temp1 >= L && temp1 <= R) ) continue;


                visited[dy][dx]=true;
                q.add(new Point(dy,dx));
                path.add(new Point(dy,dx));
                total += map[dy][dx];
            }
        }
        total = total / path.size();
        for(Point now : path){
            map[now.y][now.x]=total;
        }

        return path.size()  ;
    }

}
