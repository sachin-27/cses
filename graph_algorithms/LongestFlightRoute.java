import java.util.*;
import java.io.*;



public class LongestFlightRoute {

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

        int dist[] = new int[n+1];
        int forwardNode[] = new int[n+1];
        for(int i=0; i<=n; i++) {
            dist[i] = -1;
            forwardNode[i] = -1;
        }
        dfs(graph, dist, forwardNode, 1, n);
        
        if(dist[1] == -1) {
            System.out.println("IMPOSSIBLE");
            return;
        }

        StringBuilder s = new StringBuilder();
        s.append(dist[1] + "\n");
        int index = 1;
        while(index != n) {
            s.append(index + " ");
            index = forwardNode[index];
        }
        s.append(n);
        System.out.println(s);
    }

    public static int dfs(List<List<Integer>> graph, int[] dist, int[] forwardNode, int node, int dest) {
        if(node == dest) {
            dist[node] = 1;
            forwardNode[node] = 0;
            return 1;
        }

        if(dist[node] != -1) {
            return dist[node];
        }

        int distFromDest = -1;
        int nextNode = -1;
        for(int neighbor : graph.get(node)) {
            int d = dfs(graph, dist, forwardNode, neighbor, dest);
            if(d == -1) {
                continue;
            }
            if(d+1 > distFromDest) {
                distFromDest = d+1;
                nextNode = neighbor;
            }
        }

        dist[node] = distFromDest;
        forwardNode[node] = nextNode;
        return distFromDest;
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

