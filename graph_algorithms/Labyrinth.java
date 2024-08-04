import java.util.*;
import java.io.*;



public class Labyrinth {

    private static final int[][] dirs = {{1, 0}, {0,1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] start = new int[]{0, 0};
        int[] end = new int[]{0, 0};
        char grid[][] = new char[n][m];
        char parent[][] = new char[n][m];
        for(int i=0; i<n; i++) {
            String s = sc.next(); 
            for(int j=0; j<m; j++) {
                if(s.charAt(j) == 'A') {
                    start = new int[]{i, j};
                } else if(s.charAt(j) == 'B') {
                    end = new int[]{i, j};
                }
                grid[i][j] = s.charAt(j);
                parent[i][j] = 'x';
            }
        }
        
        Queue<int[]> q = new LinkedList<>();
        boolean found = false;
        q.add(new int[]{start[0], start[1], 0});
        while(!q.isEmpty()) {
            int[] element = q.poll();

            if(parent[element[0]][element[1]] != 'x') {
                continue;
            }

            char p = '0';
            if(element[2] == 1) {
                p = 'D';
            } else if(element[2] == 2) {
                p = 'R';
            } else if(element[2] == 3) {
                p = 'U';
            } else if(element[2] == 4) {
                p = 'L';
            }
            parent[element[0]][element[1]] = p;

            if(element[0] == end[0] && element[1] == end[1]) {
                found = true;
                break;
            }

            int idx = 1;
            for(int[] dir : dirs) {
                int x = element[0] + dir[0];
                int y = element[1] + dir[1];

                if(x >= 0 && x < n && y >= 0 && y < m && parent[x][y] == 'x' && grid[x][y] != '#') {
                    q.add(new int[]{x, y, idx});
                }
                idx++;
            }
        }

        if(!found) {
            System.out.println("NO");
            return;
        }

        System.out.println("YES");

        StringBuilder s = new StringBuilder();
        int curx = end[0];
        int cury = end[1];

        while(parent[curx][cury] != '0') {
            s.append(parent[curx][cury]);
            char c = parent[curx][cury];
            if(c == 'L') {
                cury++;
            } else if(c == 'R') {
                cury--;
            } else if(c == 'U') {
                curx++;
            } else {
                curx--;
            }
        }
        System.out.println(s.length());
        System.out.println(s.reverse());

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

