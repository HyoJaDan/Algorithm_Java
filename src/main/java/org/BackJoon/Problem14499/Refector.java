package main.java.org.BackJoon.Problem14499;
//package main.java.org.BackJoon.Implementation.Problem14499;
//
///**
// * Dice 클래스 개선:
// * 주사위의 현재 위치를 Dice 클래스에서 관리하도록 수정했습니다.
// * updatePosition 메서드를 추가하여 주사위의 위치를 업데이트하도록 했습니다.
// * getTopValue 메서드를 추가하여 주사위 윗면의 값을 반환하도록 했습니다.
// * setBottomValue 메서드를 수정하여 주사위의 바닥면과 지도의 값을 업데이트하도록 했습니다.
// * Direction enum 개선:
// * 각 방향에 따른 이동 로직을 move 메서드로 추가했습니다.
// * Main 클래스 개선:
// * init 메서드에서 초기화 로직을 처리합니다.
// * executeCommands 메서드에서 명령을 실행하고, 주사위를 굴린 후 결과를 출력합니다.
// * isValidPosition 메서드를 추가하여 유효한 위치인지를 검사합니다.
// 
// 
// * 주요 변경 사항:
// * Dice 클래스의 위치 메소드 추가: getX와 getY 메소드 추가로 Dice 객체의 현재 위치를 가져올 수 있습니다.
// * executeCommands 메소드 추가: 명령을 실행하는 메
// */
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.EnumMap;
//import java.util.StringTokenizer;
//
//enum Direction {
//    temp, east, west, north, south;
//
//    public int[] move(int x, int y) {
//        switch (this) {
//            case east: return new int[]{x, y + 1};
//            case west: return new int[]{x, y - 1};
//            case north: return new int[]{x - 1, y};
//            case south: return new int[]{x + 1, y};
//            default: return new int[]{x, y};
//        }
//    }
//}
//
//enum Face {
//    temp, top, back, right, left, front, bottom
//}
//
//class Dice {
//    private EnumMap<Face, Integer> faces = new EnumMap<>(Face.class);
//    private int x, y;
//
//    public Dice(int startX, int startY) {
//        for (Face face : Face.values()) {
//            if (face != Face.temp) {
//                faces.put(face, 0);
//            }
//        }
//        this.x = startX;
//        this.y = startY;
//    }
//
//    public void rollDice(Direction direction) {
//        if (direction == Direction.east) {
//            int temp = faces.get(Face.top);
//            faces.put(Face.top, faces.get(Face.left));
//            faces.put(Face.left, faces.get(Face.bottom));
//            faces.put(Face.bottom, faces.get(Face.right));
//            faces.put(Face.right, temp);
//        } else if (direction == Direction.west) {
//            int temp = faces.get(Face.top);
//            faces.put(Face.top, faces.get(Face.right));
//            faces.put(Face.right, faces.get(Face.bottom));
//            faces.put(Face.bottom, faces.get(Face.left));
//            faces.put(Face.left, temp);
//        } else if (direction == Direction.north) {
//            int temp = faces.get(Face.top);
//            faces.put(Face.top, faces.get(Face.front));
//            faces.put(Face.front, faces.get(Face.bottom));
//            faces.put(Face.bottom, faces.get(Face.back));
//            faces.put(Face.back, temp);
//        } else if (direction == Direction.south) {
//            int temp = faces.get(Face.top);
//            faces.put(Face.top, faces.get(Face.back));
//            faces.put(Face.back, faces.get(Face.bottom));
//            faces.put(Face.bottom, faces.get(Face.front));
//            faces.put(Face.front, temp);
//        }
//    }
//
//    public void updatePosition(int newX, int newY) {
//        this.x = newX;
//        this.y = newY;
//    }
//
//    public int getTopValue() {
//        return faces.get(Face.top);
//    }
//
//    public void setBottomValue(int[][] map) {
//        if (map[x][y] == 0) {
//            map[x][y] = faces.get(Face.bottom);
//        } else {
//            faces.put(Face.bottom, map[x][y]);
//            map[x][y] = 0;
//        }
//    }
//
//    public int getX() {
//        return x;
//    }
//
//    public int getY() {
//        return y;
//    }
//}
//
//public class Refector {
//    private static int[][] map;
//    private static int n, m;
//    private static Dice dice;
//    private static Direction[] process;
//
//    public static void main(String[] args) throws IOException {
//        init();
//        executeCommands();
//    }
//
//    private static void init() throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        n = Integer.parseInt(st.nextToken());
//        m = Integer.parseInt(st.nextToken());
//        map = new int[n][m];
//
//        int startX = Integer.parseInt(st.nextToken());
//        int startY = Integer.parseInt(st.nextToken());
//        int processSize = Integer.parseInt(st.nextToken());
//        process = new Direction[processSize];
//
//        dice = new Dice(startX, startY);
//
//        for (int i = 0; i < n; i++) {
//            st = new StringTokenizer(br.readLine());
//            for (int j = 0; j < m; j++) {
//                map[i][j] = Integer.parseInt(st.nextToken());
//            }
//        }
//
//        st = new StringTokenizer(br.readLine());
//        for (int i = 0; i < processSize; i++) {
//            process[i] = Direction.values()[Integer.parseInt(st.nextToken())];
//        }
//    }
//
//    private static void executeCommands() {
//        int x = dice.getX();
//        int y = dice.getY();
//
//        for (Direction direction : process) {
//            int[] newPos = direction.move(x, y);
//            int newX = newPos[0];
//            int newY = newPos[1];
//
//            if (isValidPosition(newX, newY)) {
//                dice.rollDice(direction);
//                dice.updatePosition(newX, newY);
//                dice.setBottomValue(map);
//                System.out.println(dice.getTopValue());
//
//                x = newX;
//                y = newY;
//            }
//        }
//    }
//
//    private static boolean isValidPosition(int x, int y) {
//        return x >= 0 && x < n && y >= 0 && y < m;
//    }
//}
