package main.java.org.BackJoon.DFS.Problem2239;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Point{
    int y;
    int x;
    public Point(int y,int x){
        this.y=y;
        this.x=x;
    }
}
public class Main {
    static int[][] map = new int[10][10];
    // row媛� 1, 2, 3�씠�떎
    static boolean[][] rowVisited = new boolean[10][10];
    // col�씠 1, 4, 7�씠�떎
    static boolean[][] colVisited = new boolean[10][10];
    static boolean[][] visited = new boolean[10][10];
    static int numOfProblem=0;
    static boolean isEnd=false;
    static ArrayList<Point> problems = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        init();
        run(0);
    }

    private static void run(int now) {
        if(numOfProblem==0){
            for(int i=1;i<=9;i++){
                for(int j=1;j<=9;j++){
                    System.out.printf(String.valueOf(map[i][j]));
                }
                System.out.println();
            }
            isEnd=true;
            return;
        }


        for(int i=now; i<problems.size(); i++)
        {
            if(isEnd) return;
            int dy= problems.get(i).y;
            int dx= problems.get(i).x;
            boolean isValid=false;
            for(int k=1;k<=9;k++){
                if(rowVisited[dy][k]==false &&
                        colVisited[dx][k]==false &&
                        visited[getWhichArea(dy,dx)][k]==false)
                {
                    isValid=true;
                    map[dy][dx]=k;
                    numOfProblem--;
                    rowVisited[dy][k]=true;
                    colVisited[dx][k]=true;
                    visited[getWhichArea(dy,dx)][k]=true;

                    run(i+1);

                    rowVisited[dy][k]=false;
                    colVisited[dx][k]=false;
                    visited[getWhichArea(dy,dx)][k]=false;
                    numOfProblem++;
                    map[dy][dx]=0;
                    isValid=false;
                }
            }
            if(!isValid) return;
        }
    }


    public static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=1;i<=9;i++){
            String s = br.readLine();
            for(int j=1;j<=9;j++){
                int now = s.charAt(j-1)-'0';
                if(now!=0) {
                    map[i][j] = now;
                    rowVisited[i][now] = true;
                    colVisited[j][now] = true;
                    visited[getWhichArea(i, j)][now] = true;
                } else {
                    numOfProblem++;
                    problems.add(new Point(i,j));
                }
            }
        }
    }
    public static int getWhichArea(int y, int x){
        if(y<=3 && x<=3) return 1;
        if(y<=3 && x>=4 && x<=6 ) return 2;
        if(y<=3 && x>=7) return 3;
        if(y>=4 && y<=6 && x<=3) return 4;
        if(y>=4 && y<=6 && x>=4 && x<=6) return 5;
        if(y>=4 && y<=6 && x>=7) return 6;
        if(y>=7 && x<=3) return 7;
        if(y>=7 && x>=4 && x<=6) return 8;
        else return 9;
    }
}
