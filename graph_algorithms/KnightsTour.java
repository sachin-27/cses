import java.util.*;
import java.io.*;



public class KnightsTour {

    public static int dirs[][] = {{1, 2}, {2, 1}, {-1, 2}, {-2, 1}, {1, -2}, {2, -1}, {-1, -2}, {-2, -1}};

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int x = sc.nextInt();
        int y = sc.nextInt();
        x--;
        y--;

        int board[][] = new int[8][8];
        int count = 1;

        dfs(board, y, x, count);

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                sb.append(board[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static boolean dfs(int board[][], int x, int y, int count) {
        if(count == 64) {
            board[x][y] = count; 
            return true;
        }

        board[x][y] = count;

        Move m = findNextMove(board, x, y);
        while(m != null) {
            if(dfs(board, m.x, m.y, count+1)) {
                return true;
            }
            m = findNextMove(board, x, y);
            
        }

        board[x][y] = 0;
        return false;
    }

    public static Move findNextMove(int[][] board, int x, int y) {
        int minDegree = 10;
        int minx = -1;
        int miny = -1;
        for(int dir[] : dirs) {
            int newx = x + dir[0];
            int newy = y + dir[1];

            if(newx >= 8 || newx < 0 || newy >= 8 || newy < 0) {
                continue;
            }

            if(board[newx][newy] != 0) {
                continue;
            }

            int degree = findDegree(board, newx, newy);
            if(degree < minDegree) {
                minDegree = degree;
                minx = newx;
                miny = newy;
            }
        }

        if(minDegree == 10) {
            return null;
        }
        
        return new Move(minx, miny);

    }

    public static int findDegree(int board[][], int x, int y) {
        int degree = 0;
        for(int dir[] : dirs) {
            int newx = x + dir[0];
            int newy = y + dir[1];

            if(newx >=0 && newx < 8 && newy >= 0 && newy < 8 && board[newx][newy] == 0) {
                degree++;
            }
        }
        return degree;
    }
    
}

class Move {
    int x;
    int y;

    public Move(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class FastScanner {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer("");
 
    String next() {
        while (!st.hasMoreTokens())
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        return st.nextToken();
    }
 
    int nextInt() {
        return Integer.parseInt(next());
    }
 
    int[] readArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = nextInt();
        return a;
    }
 
    long nextLong() {
        return Long.parseLong(next());
    }
}

