package org;

import java.util.*;

class Point{
    int y;
    int x;
    public Point(int y,int x){
        this.y=y;this.x=x;
    }
}
public class TankGame {
	 // 0: �� ����, 1: ȣ��, 2: ��, 3: ���� ������ 4 : Ÿ�� 5 : ��ũ
    static int[][] map = {
            {0, 0, 0, 2, 0, 0, 0, 1, 0, 0},
            {0, 1, 0, 2, 0, 1, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 1, 1, 1, 1, 1, 0, 1, 2, 3},
            {0, 0, 2, 0, 0, 1, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 4, 0},
            {0, 0, 0, 2, 0, 0, 0, 1, 0, 0},
            {0, 1, 0, 2, 0, 1, 0, 1, 0, 0},
            {0, 0, 4, 0, 0, 0, 0, 1, 0, 0}
    };
    static int[][] copyMap = new int[10][10];
    static int fire=1;
    static int direct[][] = {{-1,0},{1,0},{0,1},{0,-1}};
    static List<Point> ans  = new ArrayList<>();
    static List<Point> fires = new ArrayList<>();
    static Point Tank = new Point(3,3);
    public static void main(String[] args) {
    	// 0. map ����
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                copyMap[i][j] = map[i][j];
                if(map[i][j]==4){
                    ans.add(new Point(i,j));
                }
                if(map[i][j]==3){
                    fires.add(new Point(i,j));
                }
            }
        }
        // 1. target�� �����¿츦 ��� �������� ��ȯ
        func1();
        printCopyMap();
        // �ִܰŸ� Ž��

        Point nextPos = func2();
        System.out.println(nextPos.y+nextPos.x);
    }
    static Point func2(){
    	// 0: �� ����, 1: ȣ��, 2: ��, 3: ���� ������ 4 : Ÿ�� 5 : ��ũ
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(Tank.y, Tank.x));
        copyMap[Tank.y][Tank.x] = 5;
        while(!q.isEmpty()){
            Point now = q.poll();

            for(int i=0;i<4;i++){
                int y= now.y+direct[i][0];
                int x =now.x+direct[i][1];

                if(y<0 || x<0 || y>=map.length || x>=map[y].length)
                    continue;
                if(map[y][x]==2)
                    continue;
                if(copyMap[y][x]>=5)
                    continue;
                if(copyMap[y][x]==4){
                    return func3(y,x,copyMap[now.y][now.x]+1);
                }
                if(copyMap[y][x]==3){
                    if(fire < ans.size()) {
                        return func3(y,x,copyMap[now.y][now.x]+1);

                    }
                }
                copyMap[y][x] =copyMap[now.y][now.x]+1;
                q.offer(new Point(y,x));
            }
        }
        return null;
    }
    static Point func3(int y, int x, int num) {
    	printCopyMap();
        List<Point> path = new ArrayList<>();
        path.add(new Point(y, x)); // ���� ��ġ�� ��ο� �߰�

        while (num > 5) { // num�� 5�� �Ǹ� ��ũ ��ġ
            boolean found = false;

         // �����¿� Ž���Ͽ� ���ڰ� �ϳ� �۾����� ������ ã��
            for (int i = 0; i < 4; i++) {
                int newY = y + direct[i][0];
                int newX = x + direct[i][1];

             // ��ǥ�� ������ ����� �ʴ��� Ȯ��
                if (newY < 0 || newX < 0 || newY >= 10 || newX >= 10) continue;

             // ���ڰ� �ϳ� ���� ��ǥ�� ã���� �ش� ��ǥ�� �̵�
                if (copyMap[newY][newX] == num - 1) {
                    y = newY;
                    x = newX;
                    num--;
                    path.add(new Point(y, x)); // ?��로운 좌표�? 경로?�� 추�?
                    found = true;
                    break;
                }
            }

         // ���� �ֺ��� ���ڰ� �ϳ� ���� ��ΰ� ���ٸ� �߸��� ����̹Ƿ� ����
            if (!found) {
                System.out.println("��θ� ã�� ���߽��ϴ�.");
            }
        }

        // 최종?��?���? ?��?��?�� 경로�? 반환
        for(int i=0;i<path.size();i++){
            System.out.println(path.get(i).y+" "+path.get(i).x);
        }
        return path.get(path.size()-2);
    }
    static void func1(){
        for(int i=0;i<ans.size();i++){
            int nowY = ans.get(i).y;
            int nowX = ans.get(i).x;

            // 1. ?��?���? 모두 ?��?��
            for(int y=nowY+1; y<10; y++){
                if(map[y][nowX]==2) break;
                if(map[y][nowX]==1) continue;
                copyMap[y][nowX]=4;
            }
            for(int y = nowY-1; y>=0;y--){
                if(map[y][nowX]==2) break;
                if(map[y][nowX]==1) continue;
                copyMap[y][nowX]=4;
            }
            for(int x = nowX+1;x<10;x++){
                if(map[nowY][x]==2) break;
                if(map[nowY][x]==1) continue;
                copyMap[nowY][x]=4;
            }
            for(int x = nowX-1;x>=0;x--){
                if(map[nowY][x]==2) break;
                if(map[nowY][x]==1) continue;
                copyMap[nowY][x]=4;
            }
        }
    }
    static void printMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 5) {
                    System.out.print("T ");  // ��ũ ��ġ
                } else if (map[i][j] == 0) {
                    System.out.print(". ");  // �� ����
                } else if (map[i][j] == 1) {
                    System.out.print("~ ");  // ȣ��
                } else if (map[i][j] == 2) {
                    System.out.print("# ");  // ��
                } else if (map[i][j] == 3) {
                    System.out.print("C ");  // ���� ������
                } else if (map[i][j] == 4) {
                    System.out.print("X ");  // ǥ��
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    static void printCopyMap() {
        for (int i = 0; i < copyMap.length; i++) {
            for (int j = 0; j < copyMap[i].length; j++) {
                if (copyMap[i][j] == 5) {
                    System.out.print("T ");  // ��ũ ��ġ
                } else if (copyMap[i][j] == 0) {
                    System.out.print(". ");  // �� ����
                } else if (copyMap[i][j] == 1) {
                    System.out.print("~ ");  // ȣ��
                } else if (copyMap[i][j] == 2) {
                    System.out.print("# ");  // ��
                } else if (copyMap[i][j] == 3) {
                    System.out.print("C ");  // ���� ������
                } else if (copyMap[i][j] == 4) {
                    System.out.print("X ");  // ǥ��
                } else {
                    System.out.print(copyMap[i][j]+" ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
