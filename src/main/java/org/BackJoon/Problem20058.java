package org.BackJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem20058 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        int mapSize = (int) Math.pow(2,N);
        int[][] map = new int[mapSize][mapSize];

        for(int i=0;i<mapSize;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<mapSize;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<Q;i++){
            int power = (int) Math.pow(2, Integer.parseInt(st.nextToken()));
            devideAndConquer(map, power,0,mapSize-1,0,mapSize-1 );
        }
    }
    static void devideAndConquer(int[][]map, int power, int fromY, int toY,int fromX, int toX){
        if((toY-fromY == power-1) && (toX-fromX==power-1)){
            rotate90(map, power, fromY,fromX, toY,toX);
        }
        else if((toY-fromY < power) || (toX-fromX<power)){
            return;
        }
        else
        {
            int midY = (fromY+toY)/2;
            int midX = (fromX+toX)/2;

            devideAndConquer(map,power,fromY,midY,fromX,midX);
            devideAndConquer(map,power,fromY,midY,midX+1,toX);
            devideAndConquer(map,power,midY+1,toY,fromX,midX);
            devideAndConquer(map,power,midY+1,toY,midX+1,toX);
        }

    }
    static void rotate90(int[][]map, int rotateSize, int fromY,int fromX, int toY, int toX){

        int[][] newMap = new int[8][8];
        for(int i=fromY; i<toY; i++){
            for(int j=fromX; j<toX; j++){
                newMap[j][rotateSize-i-1] = map[i][j];
            }
        }

        for(int i=0;i<rotateSize;i++){
            for(int j=0;j<rotateSize;j++){
                map[i][j]=newMap[i][j];
                System.out.printf(map[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
}
