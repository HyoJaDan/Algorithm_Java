package main.java.org.BackJoon.Problem1600;

import java.io.*;
import java.util.*;

class Monkey{
    int y;
    int x;
    int level;
    int jump;
    public Monkey(int y,int x,int level,int jump){
        this.y=y;
        this.x=x;
        this.level=level;
        this.jump=jump;
    }
}

public class Main {
	static int[][] map;
	static int k,n,m,ans=-1;
    static int[][] direct = {{-1,0},{1,0},{0,1},{0,-1},{2,1},{1,2},{1,-2},{2,-1},{-2,1},{-1,2},{-1,-2},{-2,-1}};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        
        map = new int[n][m];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        if(n==1&&m==1) {
        	System.out.println(0);
        	System.exit(0);
        }
        BFS();
        
        System.out.println(ans);
	}
    public static void BFS(){
        Queue<Monkey> q = new LinkedList<>();
        q.add(new Monkey(0,0,0,k));
        boolean isDone=false;
        
        //visited []행 []열에 []번 점프가 남은 상태로 방문한 여부
        boolean visited[][][] = new boolean[n][m][k+1];
        visited[0][0][k]=true;
        while(!q.isEmpty()){
            Monkey now = q.poll();
            
//            System.out.println(now.y+" "+now.x+" "+now.level);
            for(int i=0;i<12;i++){
                if(i>=4 && now.jump<=0) break;
                
                int dy = now.y+direct[i][0];
                int dx = now.x+direct[i][1];
                
                if(dy<0 || dx<0 || dy>=n || dx>=m) continue;
                if(map[dy][dx]==1) continue;
                
                
                if(i>=4) {
                	if(visited[dy][dx][now.jump-1]) continue;
                	
                	visited[dy][dx][now.jump-1]=true;
                	q.add(new Monkey(dy,dx,now.level+1,now.jump-1));
                } else {
                	if(visited[dy][dx][now.jump]) continue;
                	
                	visited[dy][dx][now.jump]=true;
                	 q.add(new Monkey(dy,dx,now.level+1,now.jump));
                }
                if(dy==n-1 && dx==m-1) {
                	isDone=true;
                	ans = now.level+1;
                	break;
                }
            }
            if(isDone) break;
        }
    }
}

