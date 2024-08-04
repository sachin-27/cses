import java.util.*;
import java.io.*;



public class BuildingTeams {

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();

        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<m; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();

            graph.get(start).add(end);
            graph.get(end).add(start);
        }

        int color[] = new int[n+1];
        boolean possible = true;
        for(int i=1; i<=n; i++) {
            if(color[i] == 0) {
                possible = possible && dfs(graph, color, i, 1);
            }
        }

        if(!possible) {
            System.out.println("IMPOSSIBLE");
            return;
        }

        for(int i=1; i<=n; i++) {
            System.out.print(color[i] + " ");
        }
        System.out.println();

    }

    public static boolean dfs(List<List<Integer>> graph, int color[], int cur, int curentCol) {
        if(color[cur] != 0) {
            for(int neighbor : graph.get(cur)) {
                if(color[neighbor] == color[cur]) {
                    return false;
                }
            }
            return true;
        }

        color[cur] = curentCol;
        boolean possible = true;
        for(int neighbor : graph.get(cur)) {

            if(color[neighbor] == 0) {
                int dfsColor = curentCol == 1 ? 2 : 1;
                possible = possible && dfs(graph, color, neighbor, dfsColor);
            } else {
                if(color[neighbor] == curentCol) {
                    return false;
                }
            }

        }
        return possible;
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

