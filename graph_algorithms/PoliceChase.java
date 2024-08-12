import java.util.*;
import java.io.*;



public class PoliceChase {

    private static List<List<Integer>> graph;
    private static int cap[][];
    private static int visited[];
    private static int visitedToken;
    private static int source;
    private static int sink;
    private static int prev[];

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();

        source = 1;
        sink = n;
        visited = new int[n+1];
        visitedToken = 0;
        prev = new int[n+1];
        cap = new int[n+1][n+1];
        graph = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.get(a).add(b);
            cap[a][b]++;
            graph.get(b).add(a);
            cap[b][a]++;
        }

        StringBuilder s = new StringBuilder();

        int flow = maxflow();
        s.append(flow + "\n");

        visitedToken++;
        dfs(source);

        for(int i=1; i<=n; i++) {
            if(visited[i] != visitedToken) {
                continue;
            }
            for(int neighbor : graph.get(i)) {
                if(visited[neighbor] != visitedToken) {
                    s.append(i + " " + neighbor + "\n");
                }
            }
        }

        System.out.println(s);
        
    }

    public static void dfs(int node) {
        if(visited[node] == visitedToken) {
            return;
        }

        visited[node] = visitedToken;
        for(int neighbor : graph.get(node)) {
            if(visited[neighbor] != visitedToken && cap[node][neighbor] > 0) {
                dfs(neighbor);
            }
        }
    }

    public static int bfs() {
        visitedToken++;
        for(int i=1; i<prev.length; i++) {
            prev[i] = -1;
        }
       
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(source, Integer.MAX_VALUE));
        while(!q.isEmpty()) {
            Pair p = q.poll();
            int node = p.a;
            int aug = p.b;

            if(node == sink) {
                return aug;
            }

            if(visited[node] == visitedToken) {
                continue;
            }

            visited[node] = visitedToken;
            for(int neighbor : graph.get(node)) {
                if(visited[neighbor] != visitedToken && cap[node][neighbor] > 0) {
                    prev[neighbor] = node;
                    aug = Math.min(aug, cap[node][neighbor]);
                    q.add(new Pair(neighbor, aug));
                }
            }
        }

        return 0;
    }

    public static int maxflow() {
        int total = 0;
        int flow = Integer.MAX_VALUE;
        while(flow != 0) {
            flow = bfs();
            total += flow;
            if(flow == 0) {
                break;
            }
            if(prev[sink] == -1) {
                flow = 0;
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
    
}

class Pair {
    int a;
    int b;

    public Pair(int a, int b) {
        this.a = a;
        this.b = b;
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

