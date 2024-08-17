package org.BackJoon.Implementation.Problem15683;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

enum Direction{
    Right,Bottom,Left,Up;

    public Direction rotate90(){
        return Direction.values()[ (this.ordinal()+1) % values().length];
    }
}
class CCTV{
    int y;
    int x;
    int CCTVNum;
    Direction direction;

    public CCTV(int y,int x, int CCTVNum){
        this.y=y;
        this.x=x;
        this.CCTVNum=CCTVNum;
        this.direction = Direction.Right;
    }
    public void rotate90(){
        this.direction = this.direction.rotate90();
    }
    public void look(Map map, boolean isBack){
        if(CCTVNum==1){
            lookImplements(map, this.direction, isBack);
        } else if(CCTVNum==2) {
            lookImplements(map,direction, isBack);
            lookImplements(map,direction.rotate90().rotate90(), isBack);
        } else if(CCTVNum==3) {
            lookImplements(map,direction, isBack);
            lookImplements(map,direction.rotate90(), isBack);
        } else if(CCTVNum==4) {
            lookImplements(map,direction, isBack);
            lookImplements(map,direction.rotate90(), isBack);
            lookImplements(map,direction.rotate90().rotate90(), isBack);
        } else if(CCTVNum==5) {
            lookImplements(map,direction, isBack);
            lookImplements(map,direction.rotate90(), isBack);
            lookImplements(map,direction.rotate90().rotate90(), isBack);
            lookImplements(map,direction.rotate90().rotate90().rotate90(), isBack);
        }
    }
    public void lookImplements(Map map, Direction direction, boolean isBack){
        switch (direction){
            case Right:
                for(int x = this.x+1; x< map.mapSizeX; x++) {
                    if(map.grid[this.y][x]==6) break;
                    else if(map.grid[this.y][x]==0 && !isBack) map.grid[this.y][x] = 7;
                    else if(map.grid[this.y][x]>=7 && !isBack) map.grid[this.y][x]++;
                    else if(map.grid[this.y][x]==7 && isBack) map.grid[this.y][x] = 0;
                    else if(map.grid[this.y][x]>=7 && isBack) map.grid[this.y][x]--;
                }
                break;
            case Bottom:
                for(int y=this.y+1; y<map.mapSizeY; y++){
                    if(map.grid[y][this.x]==6) break;
                    else if(map.grid[y][this.x]==0 && !isBack) map.grid[y][this.x] = 7;
                    else if(map.grid[y][this.x]>=7 && !isBack) map.grid[y][this.x]++;
                    else if(map.grid[y][this.x]==7 && isBack) map.grid[y][this.x] = 0;
                    else if(map.grid[y][this.x]>=7 && isBack) map.grid[y][this.x]--;
                }
                break;
            case Left:
                for(int x=this.x-1; x>=0;x--){
                    if(map.grid[this.y][x]==6) break;
                    else if(map.grid[this.y][x]==0 && !isBack) map.grid[this.y][x] = 7;
                    else if(map.grid[this.y][x]>=7 && !isBack) map.grid[this.y][x]++;
                    else if(map.grid[this.y][x]==7 && isBack) map.grid[this.y][x] = 0;
                    else if(map.grid[this.y][x]>=7 && isBack) map.grid[this.y][x]--;
                }
                break;
            case Up:
                for(int y=this.y-1; y>=0; y--){
                    if(map.grid[y][this.x]==6) break;
                    else if(map.grid[y][this.x]==0 && !isBack) map.grid[y][this.x] = 7;
                    else if(map.grid[y][this.x]>=7 && !isBack) map.grid[y][this.x]++;
                    else if(map.grid[y][this.x]==7 && isBack) map.grid[y][this.x] = 0;
                    else if(map.grid[y][this.x]>=7 && isBack) map.grid[y][this.x]--;
                }
        }
    }
}
class Map{
    int[][] grid;
    int mapSizeY;
    int mapSizeX;
    public Map(int y, int x){
        this.mapSizeY = y;
        this.mapSizeX = x;
        grid = new int[y][x];
    }
    public int getNumOfSagak(){
        int currentSagak=0;
        for(int i=0;i<mapSizeY;i++){
            for(int j=0;j<mapSizeX;j++) {
                if (grid[i][j] == 0)
                    currentSagak++;
            }
        }
        return currentSagak;
    }
}
public class Main {
    static Map map;
    static ArrayList<CCTV> cctv = new ArrayList<>();
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        init();
        bruteForce(0);
        System.out.println(ans);
    }
    public static void init() throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        map = new Map(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));

        for(int i=0;i<map.mapSizeY;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<map.mapSizeX; j++){
                map.grid[i][j]=Integer.parseInt(st.nextToken());
                if (map.grid[i][j]>=1 && map.grid[i][j]<=5)
                    cctv.add(new CCTV(i,j,map.grid[i][j]));
            }
        }
    }
    public static void bruteForce(int level){
        if(level == cctv.size()){
            int currentSagak = map.getNumOfSagak();
            if(currentSagak < ans) ans=currentSagak;
            return;
        }

        for(int i=0;i<4;i++){
            cctv.get(level).look(map, false);
            bruteForce(level+1);
            cctv.get(level).look(map, true);

            cctv.get(level).rotate90();
        }
    }

}