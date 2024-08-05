import java.util.*;
import java.io.*;



public class Investigation {

    private static long prices[];
    private static long routes[];
    private static int minCities[];
    private static int maxCities[];
    private static int vis[];
    private static final int MOD = 1000000007;

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();

        List<List<Edge>> graph = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            long c = sc.nextLong();

            graph.get(a).add(new Edge(b, c));
        }

        prices = new long[n+1];
        routes = new long[n+1];
        minCities = new int[n+1];
        maxCities = new int[n+1];
        vis = new int[n+1];

        for(int i=0; i<=n; i++) {
            prices[i] = Long.MAX_VALUE;
        }

        prices[n] = 0;
        routes[n] = 1;
        minCities[n] = 0;
        maxCities[n] = 0;

        dfs(graph, 1);

        System.out.println(prices[1] + " " + routes[1] + " " + minCities[1] + " " + maxCities[1]);
    }

    public static Pair dfs(List<List<Edge>> graph, int node) {
        if(vis[node] == 1) {
            return null;
        }
        
        vis[node] = 1;
        if(prices[node] != Long.MAX_VALUE) {
            vis[node] = 0;
            return new Pair(minCities[node], maxCities[node], prices[node], routes[node]);
        }
    
        int minCitiesNode = Integer.MAX_VALUE;
        int maxCitiesNode = Integer.MIN_VALUE;
        long routesNode = 0;
        long priceNode = Long.MAX_VALUE;

        for(Edge edge : graph.get(node)) {
            int neighbor = edge.to;
            long weight = edge.cost;

            Pair p = dfs(graph, neighbor);

            if(p == null) {
                continue;
            }

            if(priceNode == p.price + weight) {
                routesNode += p.routes;
                routesNode %= MOD;

            } else if(priceNode > p.price + weight) {
                routesNode = p.routes;
                routesNode %= MOD;
                minCitiesNode = Integer.MAX_VALUE;
                maxCitiesNode = Integer.MIN_VALUE;
            }

            priceNode = Math.min(priceNode, p.price + weight);
            if(priceNode == p.price + weight) {
                minCitiesNode = Math.min(minCitiesNode, 1 + p.minCities);
                maxCitiesNode = Math.max(maxCitiesNode, 1 + p.maxCities);
            }

        }

        prices[node] = priceNode;
        minCities[node] = minCitiesNode;
        maxCities[node] = maxCitiesNode;
        routes[node] = routesNode;

        if(prices[node] == Long.MAX_VALUE) {
            return null;
        }

        vis[node] = 0;
        return new Pair(minCitiesNode, maxCitiesNode, priceNode, routesNode);
    }
    
}

class Edge {
    int to;
    long cost;

    public Edge(int to, long cost) {
        this.to = to;
        this.cost = cost;
    }
}

class Pair {
    int minCities;
    int maxCities;
    long price;
    long routes;

    public Pair(int minCities, int maxCities, long price, long routes) {
        this.minCities = minCities;
        this.maxCities = maxCities;
        this.price = price;
        this.routes = routes;
    }

    public String toString() {
        return "{minc:" + minCities + ", maxc: " + maxCities + ", price: " + price + ", routes: " + routes + "}";
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

