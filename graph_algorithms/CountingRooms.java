import java.io.*;
import java.util.*;
 
public class CountingRooms {
 
    private static final int dirs[][] = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
 
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
 
        int n = sc.nextInt();
        int m = sc.nextInt();
 
        String[] map = new String[n];
        for(int i=0; i<n; i++) {
            map[i] = sc.next();
        }
 
        Queue<int[]> q = new LinkedList<>(); 
        boolean visited[][] = new boolean[n][m];
        int rooms = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
            
            
               if(!visited[i][j] && map[i].charAt(j) != '#') {
                    rooms++;
                    q.add(new int[]{i, j});
                    while(!q.isEmpty()) {
                        int[] element = q.poll();

                        if(visited[element[0]][element[1]]) {
                            continue;
                        }

                        visited[element[0]][element[1]] = true;
                        for(int[] dir : dirs) {
                            int x = element[0] + dir[0];
                            int y = element[1] + dir[1];
 
                            if(x >= 0 && x < n && y>=0 && y<m && !visited[x][y] && map[x].charAt(y) != '#') {
                                q.add(new int[]{x, y});
                            }
                        }
                    }
               }
 
            }
        }
 
        System.out.println(rooms);
 
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