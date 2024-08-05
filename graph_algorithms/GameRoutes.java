import java.util.*;
import java.io.*;



public class GameRoutes {

    private static final int MOD = 1000000007;

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.get(a).add(b);
        }

        int paths[] = new int[n+1];
        paths[n] = 1;
        dfs(graph, paths, 1, n);
        System.out.println(paths[1]);
    }

    public static int dfs(List<List<Integer>> graph, int paths[], int node, int dest) {
        if(node == dest) {
            return 1;
        }

        if(paths[node] != 0) {
            return paths[node];
        }

        int totalPaths = 0;
        for(int neighbor : graph.get(node)) {
            totalPaths += dfs(graph, paths, neighbor, dest);
            totalPaths %= MOD;
        }
        paths[node] = totalPaths;
        return totalPaths;
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

