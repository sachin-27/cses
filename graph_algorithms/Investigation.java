import java.util.*;
import java.io.*;



public class Investigation {

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
            int c = sc.nextInt();

            graph.get(a).add(new Edge(b, c));
        }

        long dist[] = new long[n+1];
        long routes[] = new long[n+1];
        int minCities[] = new int[n+1];
        int maxCities[] = new int[n+1];
        for(int i=0; i<=n; i++) {
            dist[i] = Long.MAX_VALUE;
            minCities[i] = Integer.MAX_VALUE;
            maxCities[i] = Integer.MIN_VALUE;
        }
        dist[1] = 0;
        routes[1] = 1;
        minCities[1] = 0;
        maxCities[1] = 0;
        
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(1, 0));
        while(!pq.isEmpty()) {
            Pair p = pq.poll();

            for(Edge edge : graph.get(p.node)) {
                if(dist[edge.to] > p.totalCost + edge.cost) {
                    dist[edge.to] = p.totalCost + edge.cost;
                    pq.add(new Pair(edge.to, edge.cost + p.totalCost));
                    routes[edge.to] = routes[p.node];
                    minCities[edge.to] = minCities[p.node]+1;
                    maxCities[edge.to] = maxCities[p.node]+1;
                } else if(dist[edge.to] == p.totalCost + edge.cost) {
                    routes[edge.to] += routes[p.node];
                    routes[edge.to] %= MOD;
                    minCities[edge.to] = Math.min(minCities[edge.to], minCities[p.node]+1);
                    maxCities[edge.to] = Math.max(maxCities[edge.to], maxCities[p.node]+1);
                }
            }
        }

        System.out.println(dist[n] + " " + routes[n] + " " + minCities[n] + " " + maxCities[n]);

    }
    
}

class Pair implements Comparable<Pair> {
    int node;
    long totalCost;

    public Pair(int node, long totalCost) {
        this.node = node;
        this.totalCost = totalCost;
    }

    public int compareTo(Pair p) {
        if(this.totalCost > p.totalCost) {
            return 1;
        } else if(this.totalCost < p.totalCost) {
            return -1;
        }
        return 0;
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

