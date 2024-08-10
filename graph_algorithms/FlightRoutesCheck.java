import java.util.*;
import java.io.*;



public class FlightRoutesCheck {

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();

        List<List<Integer>> graph = new ArrayList<>();
        List<List<Integer>> transposedGraph = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
            transposedGraph.add(new ArrayList<>());
        }

        for(int i=0; i<m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.get(a).add(b);
            transposedGraph.get(b).add(a);          
        }

        Stack<Integer> s = new Stack<>();
        boolean visited[] = new boolean[n+1];
        for(int i=1; i<=n; i++) {
            if(!visited[i]) {
                dfs(graph, visited, s, i);
            }
        }

        visited = new boolean[n+1];
        int element = s.pop();
        dfs2(transposedGraph, visited, element);
        boolean isConnected = true;
        for(int i=1; i<=n; i++) {
            if(!visited[i]) {
                System.out.println("NO");
                System.out.println(i + " " + element);
                isConnected = false;
                break;
            }
        }

        if(isConnected) {
            System.out.println("YES");
        }

    }

    public static void dfs2(List<List<Integer>> graph, boolean visited[], int node) {
        if(visited[node]) {
            return;
        }

        visited[node] = true;
        for(int neighbor : graph.get(node)) {
            dfs2(graph, visited, neighbor);
        }
    }

    public static void dfs(List<List<Integer>> graph, boolean visited[], Stack<Integer> s, int node) {
        if(visited[node]) {
            return;
        }

        visited[node] = true;
        for(int neighbor : graph.get(node)) {
            dfs(graph, visited, s, neighbor);
        }
        s.add(node);
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

