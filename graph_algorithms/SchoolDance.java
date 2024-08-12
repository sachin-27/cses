import java.util.*;
import java.io.*;



public class SchoolDance {

    private static int source;
    private static int sink;
    private static List<List<Integer>> graph;
    private static int vis[];
    private static int visitedToken;
    private static int prev[];
    private static int cap[][];

    public static int bfs() {
        visitedToken++;
        for(int i=0; i<prev.length; i++) {
            prev[i] = -1;
        }

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(source, Integer.MAX_VALUE, -1));
        while(!q.isEmpty()) {
            Pair p = q.poll();
            int node = p.a;
            int aug = p.b;

            if(vis[node] == visitedToken) {
                continue;
            }

            prev[node] = p.c;
            vis[node] = visitedToken;

            if(node == sink) {
                return aug;
            }

            for(int neighbor : graph.get(node)) {
                if(vis[neighbor] != visitedToken && cap[node][neighbor] > 0) {
                    int aug2 = Math.min(aug, cap[node][neighbor]);
                    q.add(new Pair(neighbor, aug2, node));
                }
            }
        }

        return 0;
    }

    public static int maxflow() {
        int flow = Integer.MAX_VALUE;
        int total = 0;
        while(flow > 0) {
            flow = bfs();
            total += flow;
            if(flow == 0 || prev[sink] == -1) {
                break;
            }
            int index = sink;
            while(index != source) {
                int temp = prev[index];
                cap[temp][index]-=flow;
                cap[index][temp]+=flow;
                index = temp;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        graph = new ArrayList<>();
        for(int i=0; i<=n+m+1; i++) {
            graph.add(new ArrayList<>());
        }

        source = 0;
        sink = n+m+1;
        vis = new int[n+m+2];
        visitedToken = 0;
        prev = new int[n+m+2];
        cap = new int[n+m+2][n+m+2];

        for(int i=1; i<=n; i++) {
            graph.get(0).add(i);
            graph.get(i).add(0);
            cap[0][i]++;
        }

        for(int i=n+1; i<=n+m; i++) {
            graph.get(i).add(n+m+1);
            graph.get(n+m+1).add(i);
            cap[i][n+m+1]++;
        }

        for(int i=0; i<k; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            if(graph.get(a).contains(b+n)) {
                continue;
            }

            b += n;
            graph.get(a).add(b);
            graph.get(b).add(a);
            cap[a][b]++;
        }

        StringBuilder s = new StringBuilder();
        s.append(maxflow() + "\n");
        
        for(int i=1; i<=n; i++) {
            for(int neighbor : graph.get(i)) {
                if(cap[i][neighbor] == 0 && neighbor > n) {
                    s.append(i + " " + (neighbor-n) + "\n");
                }
            }
        }
        System.out.println(s);
        
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

