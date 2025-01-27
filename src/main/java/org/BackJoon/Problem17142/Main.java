package org.BackJoon.Problem17142;

import java.io.*;
import java.util.*;
class Point{
    int y;int x;
    public Point(int y,int x){
        this.y=y;this.x=x;
    }
}
public class Main {
    static int n,m,ans = Integer.MAX_VALUE;
    static int[][] map,visited;
    static ArrayList<Point> virus = new ArrayList<>();
    static boolean[] visitedVirus;
    static int[] currentVirus;
    static final int[][] direct = {{-1,0},{1,0},{0,1},{0,-1}};
    public static void main(String[] args) throws IOException{
        init();

        run(0, 0);
        if(ans == Integer.MAX_VALUE)
            System.out.println("-1");
        else if(ans ==0 )
            System.out.println(ans);
        else System.out.println(ans-1);
    }
    public static void run(int level, int now){
        if(level == m){
//            System.out.println("=======================================");
//            for(int i=0;i<m;i++){
//                System.out.print(currentVirus[i]+" ");
//            }
//            System.out.println();
            getMinTime();
            return;
        }
        for(int i=now; i<virus.size(); i++){
            if(visitedVirus[i]) continue;

            visitedVirus[i] = true;
            currentVirus[level] = i;
            run(level+1,i+1);
            currentVirus[level]=0;
            visitedVirus[i] = false;
        }
    }
    public static void getMinTime(){
        Queue<Point> q = new ArrayDeque<>();
        visited = new int[n+1][n+1];
        int MinTime = 0;

        for(int i=0;i<m;i++){
            q.add(new Point(virus.get(currentVirus[i]).y, virus.get(currentVirus[i]).x));
            visited[virus.get(currentVirus[i]).y][virus.get(currentVirus[i]).x]=1;
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(map[i][j]==1) {
                    visited[i][j] = Integer.MIN_VALUE;
                }
            }
        }
        while(!q.isEmpty()){
            Point now = q.poll();
            for(int i=0;i<4;i++){
                int dy = now.y+direct[i][0];
                int dx = now.x+direct[i][1];

                if(dy<0 || dx<0 || dy>=n || dx>=n ) continue;
                if(visited[dy][dx]==Integer.MIN_VALUE) continue;
                if(visited[dy][dx] >=1 ) continue;

                visited[dy][dx] = visited[now.y][now.x]+1;
                q.offer(new Point(dy, dx));

                if(visited[dy][dx] > MinTime && map[dy][dx]!=2) MinTime = visited[dy][dx];
            }
        }
        boolean isSpreadable = true;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(visited[i][j]==0 && map[i][j]!=2){
                    isSpreadable = false;
                    break;
                }
            }
            if(!isSpreadable) break;
        }
        if(isSpreadable && MinTime < ans) ans = MinTime;

//        for(int i=0;i<n;i++){
//            for(int j=0;j<n;j++){
//                if(visited[i][j]==Integer.MIN_VALUE)
//                    System.out.print("- ");
//                else System.out.print(visited[i][j]+" ");
//            }
//            System.out.println();
//        }
//        System.out.println(MinTime);
    }
    public static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        currentVirus = new int[m];

        map = new int[n+1][n+1];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==2) virus.add(new Point(i,j));
            }
        }
        visitedVirus = new boolean[virus.size()];
    }
}
