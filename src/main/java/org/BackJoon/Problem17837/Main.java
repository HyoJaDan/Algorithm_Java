package org.BackJoon.Problem17837;

import java.io.*;
import java.util.*;

enum Direction{
    right,left,top,bottom;

    public Direction reverse() {
        if (this == Direction.right) return Direction.left;
        if (this == Direction.left) return Direction.right;
        if (this == Direction.top) return Direction.bottom;
        return Direction.top;

    }


    public static Direction fromInt(int d) {
        if (d == 1) return right;
        if (d == 2) return left;
        if (d == 3) return top;
        return bottom;
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
        for(int sequence = 1; sequence <= 1000; sequence++){
            for(int horseNumber=1; horseNumber<=k; horseNumber++){
                int y = horses[horseNumber].position.y;
                int x = horses[horseNumber].position.x;
                int startPoint = 0;

                for(int i=0; i< map[y][x].size(); i++){
                    if(map[y][x].get(i) == horseNumber){
                        startPoint = i;
                        break;
                    }
                }

                if(horses[horseNumber].direction==Direction.right){
                    int dy = y+direct[2][0];
                    int dx = x+direct[2][1];

                    if(!canMove(dy,dx)){
                        horses[horseNumber].direction = horses[horseNumber].direction.reverse();
                        dy = y+direct[3][0];
                        dx = x+direct[3][1];
                        if(canMove(dy,dx))
                            moveToBlue(y,x,dy,dx,startPoint);
                    } else if(mapColor[dy][dx]==1){
                        moveToRed(y,x,dy,dx,startPoint);
                    } else if(mapColor[dy][dx]==0){
                        moveToWhite(y,x,dy,dx,startPoint);
                    }
                } else if(horses[horseNumber].direction==Direction.left){
                    int dy = y+direct[3][0];
                    int dx = x+direct[3][1];
                    if(!canMove(dy,dx)){
                        horses[horseNumber].direction = horses[horseNumber].direction.reverse();
                        dy = y+direct[2][0];
                        dx = x+direct[2][1];
                        if(canMove(dy,dx))
                            moveToBlue(y,x,dy,dx,startPoint);
                    } else if(mapColor[dy][dx]==1){
                        moveToRed(y,x,dy,dx,startPoint);
                    } else if(mapColor[dy][dx]==0){
                        moveToWhite(y,x,dy,dx,startPoint);
                    }
                } else if(horses[horseNumber].direction==Direction.top){
                    int dy = y+direct[0][0];
                    int dx = x+direct[0][1];
                    if(!canMove(dy,dx)){
                        horses[horseNumber].direction = horses[horseNumber].direction.reverse();
                        dy = y+direct[1][0];
                        dx = x+direct[1][1];
                        if(canMove(dy,dx))
                            moveToBlue(y,x,dy,dx,startPoint);
                    } else if(mapColor[dy][dx]==1){
                        moveToRed(y,x,dy,dx,startPoint);
                    } else if(mapColor[dy][dx]==0){
                        moveToWhite(y,x,dy,dx,startPoint);
                    }
                } else if(horses[horseNumber].direction==Direction.bottom){
                    int dy = y+direct[1][0];
                    int dx = x+direct[1][1];
                    if(!canMove(dy,dx)){
                        horses[horseNumber].direction = horses[horseNumber].direction.reverse();
                        dy = y+direct[0][0];
                        dx = x+direct[0][1];
                        if(canMove(dy,dx))
                            moveToBlue(y,x,dy,dx,startPoint);
                    } else if(mapColor[dy][dx]==1){
                        moveToRed(y,x,dy,dx,startPoint);
                    } else if(mapColor[dy][dx]==0){
                        moveToWhite(y,x,dy,dx,startPoint);
                    }
                }
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (map[i][j].size() >= 4) {
                            System.out.println(sequence);
                            System.exit(0);
                        }
                    }
                }
            }
        }
        System.out.println(-1);
    }
    public static void moveToWhite(int y,int x,int dy,int dx,int startPoint){
        while(map[y][x].size() > startPoint){
            int currentHorse = map[y][x].get(startPoint);
            horses[currentHorse].position= new Point(dy,dx);

            map[dy][dx].add(currentHorse);
            map[y][x].remove(startPoint);
        }
    }
    public static void moveToRed(int y,int x,int dy,int dx,int startPoint){
        while(map[y][x].size() > startPoint){
            int currentHorse = map[y][x].get(map[y][x].size()-1);
            horses[currentHorse].position = new Point(dy,dx);

            map[dy][dx].add(currentHorse);
            map[y][x].remove(map[y][x].size()-1);
        }
    }
    public static void moveToBlue(int y, int x, int dy, int dx, int startPoint){
        if(mapColor[dy][dx]==0)
            moveToWhite(y,x,dy,dx,startPoint);
        else moveToRed(y,x,dy,dx,startPoint);
    }
    public static boolean canMove(int dy, int dx){
        if(dy<0||dx<0||dy>=n||dx>=n||mapColor[dy][dx]==2) return false;
        return true;
    }
    public static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new ArrayList[n][n];
        mapColor = new int[n][n];
        horses = new Horse[k+1];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                map[i][j] = new ArrayList<>();
                mapColor[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=1;i<=k;i++){
            st = new StringTokenizer(br.readLine());
            horses[i] = new Horse(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken()));
            map[horses[i].position.y][horses[i].position.x].add(i);
        }
    }
}
