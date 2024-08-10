import java.util.*;
import java.io.*;



public class CoinCollector {

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();

        long coins[] = new long[n+1];
        for(int i=1; i<=n; i++) {
            coins[i] = sc.nextLong();
        }

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

        int componentId[] = new int[n+1];
        int count = 1;
        while(!order.isEmpty()) {
            int element = order.pop();
            if(componentId[element] == 0) {
                dfs2(transposedGraph, componentId, element, count);
                count++;
            }
        }

        Map<Integer, Long> compCoins = new HashMap<>();
        for(int i=1; i<count; i++) {
            compCoins.put(i, 0L);
        }

        for(int i=1; i<=n; i++) {
            compCoins.put(componentId[i], compCoins.get(componentId[i]) + coins[i]);
        }

        List<List<Integer>> condensedGraph = new ArrayList<>();
        for(int i=0; i<count; i++) {
            condensedGraph.add(new ArrayList<>());
        }

        for(int i=1; i<=n; i++) {
            for(int neighbor : graph.get(i)) {
                if(componentId[i] != componentId[neighbor]) {
                    condensedGraph.get(componentId[i]).add(componentId[neighbor]);
                }
            }
        }

        long total[] = new long[count];
        long maxVal = 0;
        for(int i=1; i<count; i++) {
            if(total[i] == 0) {
                maxVal = Math.max(maxVal, dfs3(condensedGraph, total, compCoins, i));
            }
        }

        System.out.println(maxVal);
    }

    public static long dfs3(List<List<Integer>> graph, long total[], Map<Integer, Long> coins, int node) {
        if(total[node] != 0) {
            return total[node];
        }

        long res = 0;
        for(int neighbor : graph.get(node)) {
            res = Math.max(res, dfs3(graph, total, coins, neighbor));
        }
        res += coins.get(node);
        total[node] = res;
        return total[node];
    }

    public static void dfs2(List<List<Integer>> graph, int componentId[], int node, int count) {
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
        order.push(node);
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

