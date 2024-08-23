package main.java.org.BackJoon.Implementation.Problem17144;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class MicroNanoMunJi implements Comparable<MicroNanoMunJi>{
    int y;
    int x;
    int level;
    public MicroNanoMunJi(int y,int x,int level){
        this.y=y;
        this.x=x;
        this.level = level;
    }
    @Override
    public int compareTo(MicroNanoMunJi other){
        return Integer.compare(this.level,other.level);
    }
}
public class Main {
    private static int N,M,T;
    private static int[][] map;
    private static final int[][] direct = {{-1,0},{1,0},{0,1},{0,-1}};
    private static PriorityQueue<MicroNanoMunJi> pq = new PriorityQueue<>();
    private static int[][] machine = new int[2][2];
    public static void main(String[] args) throws IOException{
        init();
        BFS();

        int ans=0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++)
                if(map[i][j]!=-1) ans+=map[i][j];
        }
        System.out.println(ans);
    }
    public static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                int inputData = Integer.parseInt(st.nextToken());

                if(inputData>1) pq.add(new MicroNanoMunJi(i,j,1));
                if(inputData==-1){
                    if(machine[0][0]==0 && machine[0][1]==0){
                        machine[0][0]=i;
                        machine[0][1]=j;
                    } else{
                        machine[1][0]=i;
                        machine[1][1]=j;
                    }
                }
                map[i][j]=inputData;
            }
        }
    }
    public static void BFS(){
        for(int current=1;current<=T;current++) {
            System.out.println("--------------------------");
            while (!pq.isEmpty()) {
                MicroNanoMunJi now = pq.poll();
                if(now.level != current){
                    System.out.println(now.level);
                    pq.add(new MicroNanoMunJi(now.y,now.x,now.level));
                    break;
                }

                int spreadNumber=0;
                for (int i = 0; i < 4; i++) {
                    int dy = now.y + direct[i][0];
                    int dx = now.x + direct[i][1];

                    if (dy < 0 || dx < 0 || dy >= N || dx >= M) continue;
                    if(map[dy][dx]==-1) {
                        spreadNumber++;
                        continue;
                    }

                    pq.add(new MicroNanoMunJi(dy,dx,now.level+1));
                    map[dy][dx] += map[now.y][now.x]/5;
                    spreadNumber++;
                }
                map[now.y][now.x] = map[now.y][now.x] - (map[now.y][now.x]/5*spreadNumber);
            }
            EngineMachine();
        }
    }
    public static void EngineMachine(){
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        circulateUpperAir(machine[0][0],machine[0][1]);
        circulateUnderAir(machine[1][0],machine[1][1]);
    }

    private static void circulateUpperAir(int y, int x) {
        for(int i=y-2; i>=0; i--){
            map[i+1][x] = map[i][x];
        }
        for(int j=x+1; j<M;j++){
            map[0][j-1] = map[0][j];
        }
        for(int i=1; i<=y ;i++){
            map[i-1][M-1] = map[i][M-1];
        }
        for(int j=M-2; j>x; j--){
            map[y][j+1] = map[y][j];
        }
        map[y][x+1]=0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
    private static void circulateUnderAir(int y, int x) {
        for(int i=y+2; i<N; i++){
            map[i-1][x] = map[i][x];
        }
        for(int j=x+1; j<M;j++){
            map[N-1][j-1]=map[N-1][j];
        }
        for(int i=N-2; i>=y; i--){
            map[i+1][M-1] = map[i][M-1];
        }
        for(int j=M-2; j>x; j--){
            map[y][j+1] = map[y][j];
        }
        map[y][x+1]=0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
}
