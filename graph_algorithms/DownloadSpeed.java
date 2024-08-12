import java.util.*;
import java.io.*;



public class DownloadSpeed {

    private static List<List<Edge>> graph;
    private static int visited[];
    private static Edge prev[];
    private static int visitedToken;
    private static int source;
    private static int sink;

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();

        source = 1;
        sink = n;
        visited = new int[n+1];
        prev = new Edge[n+1];
        visitedToken = 0;

        graph = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            Edge e = new Edge(a, b, c);
            Edge residual = new Edge(b, a, 0);
            e.residual = residual;
            residual.residual = e;
            graph.get(a).add(e);
            graph.get(b).add(residual);
        }

        System.out.println(solve());
    }

    public static long solve() {
        long flow = Long.MAX_VALUE;
        long maxflow = 0;
        while(flow != 0) {
            flow = Long.MAX_VALUE;
            visitedToken++;
            for(int i=0; i<=sink; i++) {
                prev[i] = null;
            }
            Queue<Integer> q = new LinkedList<>();
            q.add(source);
            while(!q.isEmpty()) {
                int element = q.poll();
                if(visited[element] == visitedToken) {
                    continue;
                }

                if(element == sink) {
                    break;
                }

                visited[element] = visitedToken;
                for(Edge e : graph.get(element)) {
                    if(e.cap - e.flow > 0 && visited[e.to] != visitedToken) {
                        prev[e.to] = e;
                        q.add(e.to);
                    }
                }
            }

            // construct reverse graph and find flow
            if(prev[sink] == null) {
                flow = 0;
                break;
            }

            int index = sink;
            while(index != source) {
                Edge e = prev[index];
                flow = Math.min(flow, e.cap-e.flow);
                index = e.from;
            }

            index = sink;
            while(index != source) {
                Edge e = prev[index];
                e.flow += flow;
                e.residual.flow -= flow;
                index = e.from;
            }

            maxflow += flow;
        }

        return maxflow;
    }
    
}

class Edge {
    int from;
    int to;
    long flow;
    long cap;

    Edge residual;

    public Edge(int from, int to, long cap) {
        this.from = from;
        this.to = to;
        this.cap = cap;
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

