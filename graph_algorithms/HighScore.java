import java.util.*;
import java.io.*;



public class HighScore {

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();

        long edges[][] = new long[m][3];
        for(int i=0; i<m; i++) {
            for(int j=0; j<3; j++) {
                edges[i][j] = sc.nextInt();
            }
        }

        long dist[] = new long[n+1];
        for(int i=0; i<=n; i++) {
            dist[i] = Long.MIN_VALUE;
        }
        dist[1] = 0;

        for(int i=0; i<=n-1; i++) {
            for(long edge[] : edges) {
                int src = (int)edge[0];
                int dest = (int)edge[1];
                long weight = edge[2];

                if(weight < 0 && dist[src] < Long.MIN_VALUE - weight) {
                    continue;
                }
                if(dist[src] == Long.MIN_VALUE) {
                    continue;
                }

                dist[dest] = Math.max(dist[dest], dist[src] + weight);
            }
        }

        for(int i=0; i<=n-1; i++) {
            for(long edge[] : edges) {
                int src = (int)edge[0];
                int dest = (int)edge[1];
                long weight = edge[2];

                if(weight < 0 && dist[src] < Long.MIN_VALUE - weight) {
                    continue;
                }
                if(dist[src] == Long.MIN_VALUE) {
                    continue;
                }
    
                if(dist[dest] == Long.MAX_VALUE || dist[src] == Long.MAX_VALUE || dist[dest] < dist[src] + weight) {
                    dist[dest] = Long.MAX_VALUE;
                }
            }
        }

        if(dist[n] == Long.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(dist[n]);
        }
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