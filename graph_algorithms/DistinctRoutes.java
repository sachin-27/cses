import java.util.*;
import java.io.*;



public class DistinctRoutes {

    private static List<List<Edge>> graph;
    private static int source;
    private static int sink;

    private static int prev[];
    private static int vis[];
    private static int visToken;

    private static int cap[][];

    private static List<List<Boolean>> visitedEdges;

    public static int bfs() {
        visToken++;
        for(int i=0; i<prev.length; i++) {
            prev[i] = -1;
        }

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(source, Integer.MAX_VALUE, -1));
        while(!q.isEmpty()) {
            Pair p = q.poll();
            int node = p.a;
            int aug = p.b;
            int pr = p.c;

            if(vis[node] == visToken) {
                continue;
            }

            vis[node] = visToken;
            prev[node] = pr;
            
            if(node == sink) {
                return aug;
            }

            for(Edge neighbor : graph.get(node)) {
                if(vis[neighbor.to] != visToken && cap[node][neighbor.to] > 0) {
                    int aug2 = Math.min(aug, cap[node][neighbor.to]);
                    q.add(new Pair(neighbor.to, aug2, node));
                }
            }
        }

        return 0;
    }

    public static int maxFlow() {
        int total = 0;
        int flow = Integer.MAX_VALUE;
        while(flow > 0) {
            flow = bfs();
            if(flow == 0 || prev[sink] == -1) {
                break;
            }
            total += flow;
            int temp = sink;
            while(temp != source) {
                cap[prev[temp]][temp]-=flow;
                cap[temp][prev[temp]]+=flow;
                temp = prev[temp];
            }
        }
        return total;
    }

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();

        graph = new ArrayList<>();
        source = 1;
        sink = n;
        prev = new int[n+1];
        vis = new int[n+1];
        cap = new int[n+1][n+1];
        visToken = 0;
        
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            graph.get(a).add(new Edge(b, false));
            cap[a][b]++;
            graph.get(b).add(new Edge(a, true));
        }

        StringBuilder s = new StringBuilder();
        
        int f = maxFlow();
        s.append(f + "\n");
        
        visitedEdges = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            visitedEdges.add(new ArrayList<>());
            for(Edge neighbor : graph.get(i)) {
                visitedEdges.get(i).add(false);
            }
        }
        List<Integer> path = new LinkedList<>();
        for(int i=0; i<f; i++) {
            dfs(path, s, 1, n);
        }
        System.out.println(s);
    }

    public static boolean dfs(List<Integer> path, StringBuilder s, int node, int n) {
        path.add(node);
        if(node == n) {
            s.append(path.size() + "\n");
            for(int num : path) {
                s.append(num + " ");
            }
            s.append("\n");
            path.remove(path.size()-1);
            return true;
        }

        for(int i=0; i<graph.get(node).size(); i++) {
            Edge neighbor = graph.get(node).get(i);
            if(visitedEdges.get(node).get(i) == false && cap[node][neighbor.to] == 0 && neighbor.isres == false) {
                visitedEdges.get(node).set(i, true);
                if(dfs(path, s, neighbor.to, n)) {
                    path.remove(path.size()-1);
                    return true;
                }
            }
        }
        path.remove(path.size()-1);
        return false;
    }
    
}

class Pair {
    int a;
    int b;
    int c;

    public Pair(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}

class Edge {
    int to;
    boolean isres;

    public Edge(int to, boolean isres) {
        this.to = to;
        this.isres = isres;
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

