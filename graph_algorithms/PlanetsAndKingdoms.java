import java.util.*;
import java.io.*;



public class PlanetsAndKingdoms {

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

        Stack<Integer> order = new Stack<>();
        boolean visited[] = new boolean[n+1];
        for(int i=1; i<=n; i++) {
            if(!visited[i]) {
                dfs(graph, visited, order, i);
            }
        }

        int componentCount = 1;
        int componentId[] = new int[n+1];
        while(!order.isEmpty()) {
            int element = order.pop();
            if(componentId[element] != 0) {
                continue;
            }

            dfs2(transposedGraph, componentId, element, componentCount);
            componentCount++;
        }

        StringBuilder s = new StringBuilder();
        s.append(componentCount-1 + "\n");
        for(int i=1; i<=n; i++) {
            s.append(componentId[i] + " ");
        }

        System.out.println(s);
    }

    public static void dfs2(List<List<Integer>> graph, int componentId[], int node, int componentCount) {
        if(componentId[node] != 0) {
            return;
        }

        componentId[node] = componentCount;
        for(int neighbor : graph.get(node)) {
            dfs2(graph, componentId, neighbor, componentCount);
        }
    }

    public static void dfs(List<List<Integer>> graph, boolean visited[], Stack<Integer> order, int node) {
        if(visited[node]) {
            return;
        } 

        visited[node] = true;
        for(int neighbor : graph.get(node)) {
            dfs(graph, visited, order, neighbor);
        }
        order.add(node);
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

