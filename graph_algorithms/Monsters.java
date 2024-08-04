import java.util.*;
import java.io.*;



public class Monsters {

    private static final int dirs[][] = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();

        char[][] currentChar = new char[n][m];
        int[][] humanDist = new int[n][m];
        int[][] monsterDist = new int[n][m];
        char[][] humanPrevPos = new char[n][m];
        int[] start = new int[2];
        List<int[]> monstersList = new ArrayList<>();
        for(int i=0; i<n; i++) {
            String s = sc.next();
            for(int j=0; j<m; j++) {
                humanDist[i][j] = Integer.MAX_VALUE;
                monsterDist[i][j] = Integer.MAX_VALUE;
                humanPrevPos[i][j] = '0';
                if(s.charAt(j) == 'M') {
                    currentChar[i][j] = '.';
                    monstersList.add(new int[]{i, j});
                } else if(s.charAt(j) == 'A') {
                    currentChar[i][j] = '.';
                    start = new int[]{i, j};
                } else {
                    currentChar[i][j] = s.charAt(j);
                    
                }
            }
        }

        Queue<int[]> q = new LinkedList<>();
        for(int[] monster : monstersList) {
            q.add(new int[]{monster[0], monster[1], 0});
        }

        while(!q.isEmpty()) {
            int[] element = q.poll();

            int x = element[0];
            int y = element[1];
            int dist = element[2];

            if(monsterDist[x][y] <= dist) {
                continue;
            }

            monsterDist[x][y] = dist;

            for(int[] dir : dirs) {
                int newX = x + dir[0];
                int newY = y + dir[1];

                if(newX >= 0 && newX < n && newY >= 0 && newY < m && currentChar[newX][newY]=='.') {
                    q.add(new int[]{newX, newY, dist+1});
                }
            }
        }

        q.offer(new int[]{start[0], start[1], 0, -1});

        while(!q.isEmpty()) {
            int[] element = q.poll();

            int x = element[0];
            int y = element[1];
            int dist = element[2];
            int prevPos = element[3];

            if(humanDist[x][y] != Integer.MAX_VALUE) {
                continue;
            }
            
            if(dist >= monsterDist[x][y]) {
                continue;
            }

            humanDist[x][y] = dist;
            if(prevPos == 1) {
                humanPrevPos[x][y] = 'D';
            } else if(prevPos == 2) {
                humanPrevPos[x][y] = 'R';
            } else if(prevPos == 3) {
                humanPrevPos[x][y] = 'U';
            } else if(prevPos == 4) {
                humanPrevPos[x][y] = 'L';
            } else {
                humanPrevPos[x][y] = '0';
            }

            if(x == 0 || x == n-1 || y == 0 || y == m-1) {

                StringBuilder s = new StringBuilder();

                while(x != start[0] || y != start[1]) {
    
                    char prev = humanPrevPos[x][y];
                    s.append(prev);
    
                    if(prev == 'D') {
                        x -= 1;
                    } else if(prev == 'R') {
                        y -= 1;
                    } else if(prev == 'L') {
                        y += 1;
                    } else {
                        x += 1;
                    }
    
                }

                StringBuilder result = new StringBuilder();
                result.append("YES\n");
                result.append(s.length() + "\n");
                result.append(s.reverse());
                System.out.println(result);
                return;
            }

            int idx = 1;
            for(int[] dir : dirs) {
                int newX = x + dir[0];
                int newY = y + dir[1];

                if(newX >= 0 && newX < n && newY >= 0 && newY < m && currentChar[newX][newY]=='.' && humanDist[newX][newY] == Integer.MAX_VALUE) {
                    q.add(new int[]{newX, newY, dist+1, idx});
                }
                idx++;
            }

        }

        System.out.println("NO");

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

