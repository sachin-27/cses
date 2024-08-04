import java.util.*;
import java.io.*;



public class FlightRoutes {

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        List<List<int[]>> graph = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<m; i++) {
            int src = sc.nextInt();
            int dest = sc.nextInt();
            int weight = sc.nextInt();
            graph.get(src).add(new int[]{dest, weight});
        }

        long dist[][] = new long[n+1][k];
        for(int i=0; i<=n; i++) {
            for(int j=0; j<k; j++) {
                dist[i][j] = Long.MAX_VALUE;
            }
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(1, 0));
        while(!pq.isEmpty()) {
            Pair p = pq.poll();

            for(int i=0; i<k; i++) {
                if(dist[p.node][i] > p.curDist) {
                    dist[p.node][i] = p.curDist;
                    for(int neighbor[] : graph.get(p.node)) {
                        pq.add(new Pair(neighbor[0], p.curDist + neighbor[1]));
                    }
                    break;
                }
            }
        }

        StringBuilder s = new StringBuilder();
        for(int i=0; i<k; i++) {
            s.append(dist[n][i] + " ");
        }
        System.out.println(s);

    }
    
}

class Pair implements Comparable<Pair> {
    int node;
    long curDist;

    public Pair(int node, long curDist) {
        this.node = node;
        this.curDist = curDist;
    }

    public int compareTo(Pair p) {
        if(this.curDist > p.curDist) {
            return 1;
        } else if(this.curDist < p.curDist) {
            return -1;
        }

        return 0;
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

