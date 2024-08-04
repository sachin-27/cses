import java.util.*;
import java.io.*;



public class ShortestRoutes2 {

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();
        int q = sc.nextInt();
        
        long graph[][] = new long[n+1][n+1];
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                if(i != j) {
                    graph[i][j] = Long.MAX_VALUE;
                }
            }
        }

        for(int i=0; i<m; i++) {
            int src = sc.nextInt();
            int dest = sc.nextInt();
            long weight = sc.nextLong();

            graph[src][dest] = Math.min(graph[src][dest], weight);
            graph[dest][src] = Math.min(graph[src][dest], weight);
        }

        for(int k=1; k<=n; k++) {
            for(int i=1; i<=n; i++) {
                for(int j=1; j<=n; j++) {
                    if(graph[i][k] == Long.MAX_VALUE || graph[k][j] == Long.MAX_VALUE) {
                        continue;
                    }
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        StringBuilder res = new StringBuilder();
        while(q-- > 0) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            if(graph[a][b] == Long.MAX_VALUE) {
                res.append("-1\n");
            } else {
                res.append(graph[a][b] + "\n");
            }
        }
        System.out.println(res);
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

