package main.java.org.BackJoon.Problem15684;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Ladder{
    int N,H;
    int grid[][];
    public Ladder(int N, int H){
        this.N=N;
        this.H=H;
        grid = new int[H+1][N+1];
    }
    public boolean isPathValid(){
        for(int tc=1; tc<=N; tc++){
            int nowX = tc;
            for(int nowY =1; nowY <=H; nowY++){
                if(nowX <N && grid[nowY][nowX]==1){
                    nowX++;
                } else if(nowX>1 &&grid[nowY][nowX-1]==1){
                    nowX--;
                }
            }
            if(nowX != tc){
                return false;
            }
        }
        return true;
    }
    public void putLadder(int from, int to){
        grid[from][to] =1;
    }
    public void deleteLadder(int from, int to){
        grid[from][to] =0;
    }
}
public class Main {
    private static int M;
    private static Ladder ladder;
    private static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException{
        init();
        bruteForce(0,1 ,1);
        System.out.println(ans>3 ? -1 : ans);
    }
    public static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        ladder = new Ladder(N,H);

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            ladder.putLadder(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
    }
    public static void bruteForce(int level,int nowY, int nowX){
        if(ladder.isPathValid()){
            ans = Math.min(level, ans);
            return;
        }
        if(level>2){
            return;
        }
        for(int i=nowY;i<=ladder.H;i++){
            for(int j=nowX; j<= ladder.N; j++){
                if(j == ladder.N){
                    nowX=1;
                    break;
                }
                if( ladder.grid[i][j]== 1 || ladder.grid[i][j+1] == 1)
                    continue;
                ladder.putLadder(i,j);
                bruteForce(level+1, i,j);
                ladder.deleteLadder(i,j);
            }
        }
    }
}
