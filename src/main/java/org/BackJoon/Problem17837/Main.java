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
            default : throw new IllegalArgumentException("Invalid direction");
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
                        moveToBlue(y,x,dy,dx,startPoint);
                    } else if(mapColor[dy][dx]==1){
                        moveToRed(y,x,dy,dx,startPoint);
                        horses[horseNumber].position.y=dy;
                        horses[horseNumber].position.x=dx;
                    } else if(mapColor[dy][dx]==0){
                        moveToWhite(y,x,dy,dx,startPoint);
                        horses[horseNumber].position.y=dy;
                        horses[horseNumber].position.x=dx;
                    }
                } else if(horses[horseNumber].direction==Direction.left){
                    int dy = y+direct[3][0];
                    int dx = x+direct[3][1];
                    if(!canMove(dy,dx)){
                        horses[horseNumber].direction = horses[horseNumber].direction.reverse();
                        dy = y+direct[2][0];
                        dx = x+direct[2][1];
                        boolean isMoved = moveToBlue(y,x,dy,dx,startPoint);
                        if(isMoved){
                            horses[horseNumber].position.y=dy;
                            horses[horseNumber].position.x=dx;
                        }
                    } else if(mapColor[dy][dx]==1){
                        moveToRed(y,x,dy,dx,startPoint);
                        horses[horseNumber].position.y=dy;
                        horses[horseNumber].position.x=dx;
                    } else if(mapColor[dy][dx]==0){
                        moveToWhite(y,x,dy,dx,startPoint);
                        horses[horseNumber].position.y=dy;
                        horses[horseNumber].position.x=dx;
                    }
                } else if(horses[horseNumber].direction==Direction.top){
                    int dy = y+direct[1][0];
                    int dx = x+direct[1][1];
                    if(!canMove(dy,dx)){
                        horses[horseNumber].direction = horses[horseNumber].direction.reverse();
                        dy = y+direct[0][0];
                        dx = x+direct[0][1];
                        boolean isMoved = moveToBlue(y,x,dy,dx,startPoint);
                        if(isMoved){
                            horses[horseNumber].position.y=dy;
                            horses[horseNumber].position.x=dx;
                        }
                    } else if(mapColor[dy][dx]==1){
                        moveToRed(y,x,dy,dx,startPoint);
                        horses[horseNumber].position.y=dy;
                        horses[horseNumber].position.x=dx;
                    } else if(mapColor[dy][dx]==0){
                        moveToWhite(y,x,dy,dx,startPoint);
                        horses[horseNumber].position.y=dy;
                        horses[horseNumber].position.x=dx;
                    }
                } else if(horses[horseNumber].direction==Direction.bottom){
                    int dy = y+direct[0][0];
                    int dx = x+direct[0][1];
                    if(!canMove(dy,dx)){
                        horses[horseNumber].direction = horses[horseNumber].direction.reverse();
                        dy = y+direct[1][0];
                        dx = x+direct[1][1];
                        boolean isMoved = moveToBlue(y,x,dy,dx,startPoint);
                        if(isMoved){
                            horses[horseNumber].position.y=dy;
                            horses[horseNumber].position.x=dx;
                        }
                    } else if(mapColor[dy][dx]==1){
                        moveToRed(y,x,dy,dx,startPoint);
                        horses[horseNumber].position.y=dy;
                        horses[horseNumber].position.x=dx;
                    } else if(mapColor[dy][dx]==0){
                        moveToWhite(y,x,dy,dx,startPoint);
                        horses[horseNumber].position.y=dy;
                        horses[horseNumber].position.x=dx;
                    }
                }
                if(map[horses[0].position.y][horses[0].position.x].size()==horses.length){
                    System.out.println(sequence);
                    System.exit(0);
                }
            }
        }
        System.out.println(-1);
    }
    public static void moveToWhite(int y,int x,int dy,int dx,int startPoint){
        while(map[y][x].size() > startPoint){
            map[dy][dx].add(map[y][x].get(startPoint));
            map[y][x].remove(startPoint);
        }
    }
    public static void moveToRed(int y,int x,int dy,int dx,int startPoint){
        while(map[y][x].size() > startPoint){
            map[dy][dx].add(map[y][x].get(map[y][x].size()-1));
            map[y][x].remove(map[y][x].size()-1);
        }
    }
    public static boolean moveToBlue(int y, int x, int dy, int dx, int startPoint){
        if(!canMove(dy,dx)) return false;

        moveToWhite(y,x,dy,dx,startPoint);
        return true;
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
        horses = new Horse[k];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                map[i][j] = new ArrayList<>();
                mapColor[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0;i<k;i++){
            st = new StringTokenizer(br.readLine());
            horses[i] = new Horse(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken()));
            map[horses[i].position.y][horses[i].position.x].add(i);
        }
    }
}
