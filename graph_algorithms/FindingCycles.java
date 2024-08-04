import java.util.*;
import java.io.*;



public class FindingCycles {

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();

        int edges[][] = new int[m+n][3];
        for(int i=0; i<n; i++) {
            edges[i][0] = 0;
            edges[i][1] = i+1;
            edges[i][2] = 0;
        }
        for(int i=n; i<n+m; i++) {
            for(int j=0; j<3; j++) {
                edges[i][j] = sc.nextInt();
            }
        }

        long dist[] = new long[n+1];
        int parent[] = new int[n+1];
        for(int i=0; i<=n; i++) {
            dist[i] = Long.MAX_VALUE;
            parent[i] = -1;
        }
        dist[0] = 0;

        // bellman ford for n-1 iterations for sssp
        for(int i=0; i<n; i++) {
            for(int[] edge : edges) {
                int src = edge[0];
                int dest = edge[1];
                long weight = edge[2];
    
                if(dist[src] != Long.MAX_VALUE) {
                    if(dist[src] + weight < dist[dest]) {
                        dist[dest] = dist[src] + weight;
                        parent[dest] = src;
                    }
                }
            }
        }

        // bellman ford for n-1 more iterations to detect negative cycle
        int index = -1;
        for(int i=0; i<n; i++) {
            for(int edge[] : edges) {
                int src = edge[0];
                int dest = edge[1];
                long weight = edge[2];
                
                if(dist[src] != Long.MAX_VALUE) {
                    if(dist[src] + weight < dist[dest]) {
                        index = src;
                        break;
                    }
                }
            }
        }

        if(index == -1) {
            System.out.println("NO");
        } else {
            Set<Integer> vis = new HashSet<>();
            int temp = index;
            int cycleVertex = -1;
            while(!vis.contains(temp)) {
                vis.add(temp);
                temp = parent[temp];
            }

            cycleVertex = temp;
            List<Integer> path = new ArrayList<>();
            path.add(temp);
            temp = parent[temp];
            while(temp != cycleVertex) {
                path.add(temp);
                temp = parent[temp];
            }
            path.add(cycleVertex);

            StringBuilder s = new StringBuilder();
            s.append("YES\n");
            for(int i=path.size()-1; i>=0; i--) {
                s.append(path.get(i) + " ");
            }
            System.out.println(s);
        }


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

