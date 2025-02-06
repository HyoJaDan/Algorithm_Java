package main.java.org.BackJoon.Problem17837;

import java.io.*;
import java.util.*;

enum Direction{
    right,left,top,bottom;

    public Direction reverse(){
        switch(this){
            case right : return left;
            case left : return right;
            case top : return bottom;
            case bottom : return top;
        }
    }

    public static Direction fromInt(int d){
        switch(d){
            case 1 : return right;
            case 2 : return left;
            case 3 : return top;
            case 4 : return bottom;
            default : throw new IllegalArgumentException("Invalid direction");
        }
    }
}

class Point{
    int y;int x;
    public Point(int y,int x){
        this.y=y;this.x=x;
    }
}

class Horse{
    Point position;
    Direction direction;

    public Horse(Point p, int direction){
        this.position=p;
        this.direction = Direction.fromInt(direction);
    }
    public Horse(int y,int x, int direction){
        this.position= new Point(y,x);
        this.direction = Direction.fromInt(direction);
    }
}

public class Main {
    static int n,k;
    static List<Integer>[][] map;
    static int[][] mapColor;
    static Horse[] horses;
    static final int[][] direct = {{-1,0},{1,0},{0,1},{0,-1}};

    public static void main(String[] args) throws IOException{
        init();
        for(int sequence = 1; sequence < 1000; sequence++){
            for(int horseNumber=0; horseNumber<k; horseNumber++){

            }
        }
    }
    public static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new ArrayList[n][n];
        mapColor = new int[n][n];
        horses = new Horse[k];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                map[i][j] = new ArrayList<>();
                mapColor[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0;i<k;i++){
            st = new StringTokenizer(br.readLine());
            horses[i] = new Horse(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
            map[horses[i].position.y][horses[i].position.x].add(i);
        }
    }
}
