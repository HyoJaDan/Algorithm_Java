package org.PCCP.Problem3;

class Point{
    int y,x;

    public Point(int y,int x){
        this.y=y;
        this.x=x;
    }
}

public class Solution {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;

        boolean flag=false;
        int[] startPoints = new int[routes.length];
        for(int i=0;i<routes.length;i++)
            startPoints[i]=0;

        while(!flag){
            flag=true;
            for(int i=0;i<points.length;i++){
                int[][] map = new int[points.length][points.length];
                if(startPoints[i] >= routes[i].length-1)
                    break;

                flag=false;

                Point from = new Point(points[startPoints[i]][0], points[startPoints[i]][1]);
                Point to = new Point(points[startPoints[i+1]][0], points[startPoints[i+1]][1]);

                // 이동
                Point nextPos = shift(from,to);
                map[nextPos.y][nextPos.x]++;

                // 이미 다른게 있다면
                if(map[nextPos.y][nextPos.x]>1) answer++;

                // 도착지라면 다음 목적지로 이동
                if(nextPos.y == to.y && nextPos.x == to.x)
                    startPoints[i]++;

            }
        }
        return answer;
    }

    public Point shift(Point from, Point to){
        if(from.y !=to.y){
            if(from.y > to.y)
                return new Point(from.y-1, from.x);
            return new Point(from.y+1, from.x);
        } else {
            if(from.x > to.x)
                return new Point(from.y, from.x-1);
            return new Point(from.y, from.x+1);
        }
    }
}