import java.util.*;
import java.io.*;



public class GiantPizza {

    private static int m;

    public static int not(int a) {
        if(a > m) {
            return a-m;
        }

        return a+m;
    }

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        m = sc.nextInt();


        List<List<Integer>> graph = new ArrayList<>();
        List<List<Integer>> transposedGraph = new ArrayList<>();
        for(int i=0; i<=2*m; i++) {
            graph.add(new ArrayList<>());
            transposedGraph.add(new ArrayList<>());
        }

        for(int i=1; i<=n ; i++) {
            char a = sc.next().charAt(0);
            int b = sc.nextInt();
            char c = sc.next().charAt(0);
            int d = sc.nextInt();

            if(a == '-') {
                b = not(b);
            }
            if(c == '-') {
                d = not(d);
            }

            graph.get(not(b)).add(d);
            transposedGraph.get(d).add(not(b));

            graph.get(not(d)).add(b);
            transposedGraph.get(b).add(not(d));
        }

        Stack<Integer> order = new Stack<>();
        boolean visited[] = new boolean[2*m+1];
        for(int i=1; i<=2*m; i++) {
            if(!visited[i]) {
                dfs(graph, visited, order, i);
            }
        }

        int componentId[] = new int[2*m+1];
        int count = 1;
        while(!order.isEmpty()) {
            int element = order.pop();

            if(componentId[element] != 0) {
                continue;
            }
            dfs2(transposedGraph, componentId, element, count);
            count++;
        }

        for(int i=1; i<=m; i++) {
            if(componentId[i] == componentId[not(i)]) {
                System.out.println("IMPOSSIBLE");
                return;
            }
        }

        StringBuilder s = new StringBuilder();
        for(int i=1; i<=m; i++) {
            if(componentId[i] > componentId[not(i)]) {
                s.append("+ ");
            } else {
                s.append("- ");
            }
        }
        System.out.println(s);

    }

    private static void dfs2(List<List<Integer>> graph, int componentId[], int node, int count) {
        if(componentId[node] != 0) {
            return;
        }

        componentId[node] = count;
        for(int neighbor : graph.get(node)) {
            dfs2(graph, componentId, neighbor, count);
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

