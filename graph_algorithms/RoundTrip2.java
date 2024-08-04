import java.util.*;
import java.io.*;



public class RoundTrip2 {

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();

        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i=0; i<m; i++) {
            int src = sc.nextInt();
            int dest = sc.nextInt();
            graph.get(src).add(dest);
        }

        int parent[] = new int[n+1];
        int vis[] = new int[n+1];
        int index = -1;
        for(int i=1; i<=n; i++) {
            if(vis[i] != 2) {
                index = dfs(graph, vis, parent, i);
            }

            if(index != -1) {
                break;
            }
        }

        if(index == -1) {
            System.out.println("IMPOSSIBLE");
            return;
        }

        // System.out.println(index);
        // for(int i=1; i<=n; i++) {
        //     System.out.print(parent[i] + " ");
        // }
        // System.out.println();

        List<Integer> path = new ArrayList<>();
        path.add(index);
        int temp = index;
        temp = parent[index];
        while(temp != index) {
            path.add(temp);
            temp = parent[temp];
        }
        path.add(index);
        
        StringBuilder s = new StringBuilder();
        s.append(path.size() + "\n");
        for(int i=path.size()-1; i>=0; i--) {
            s.append(path.get(i) + " ");
        }
        System.out.println(s);

    }

    public static int dfs(List<List<Integer>> graph, int vis[], int parent[], int node) {
        if(vis[node] == 2) {
            return -1;
        }
        if(vis[node] == 1) {
            return node;
        }

        vis[node] = 1;
        for(int neighbor : graph.get(node)) {
            parent[neighbor] = node;
            int index = dfs(graph, vis, parent, neighbor);
            if(index != -1) {
                return index;
            }
        }
        vis[node] = 2;
        return -1;
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

