import java.util.*;
import java.io.*;



public class FlightDiscount {

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<List<int[]>> graph = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i=0; i<m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            graph.get(a).add(new int[]{b, c});
        }

        long dist[][] = new long[n+1][2];
        for(int i=0; i<dist.length; i++) {
            if(i == 1) {
                continue;
            }
            dist[i][0] = Long.MAX_VALUE;
            dist[i][1] = Long.MAX_VALUE;
        }

        List<boolean[]> vis = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            vis.add(new boolean[]{false, false});
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(1, 0, 0));
        pq.add(new Pair(1, 0, 1));

        while(!pq.isEmpty()) {
            Pair p = pq.poll();

            if(vis.get(p.node)[p.usedDiscount]) {
                continue;
            }
            vis.get(p.node)[p.usedDiscount] = true;

            for(int neighbor[] : graph.get(p.node)) {
                int node = neighbor[0];
                long weight = neighbor[1];

                if(p.usedDiscount == 1) {
                    if(dist[node][1] > p.distance + weight) {
                        dist[node][1] = p.distance + weight;
                        pq.add(new Pair(node, p.distance + weight, 1));
                    }
                } else {
                    if(dist[node][0] > p.distance + weight) {
                        dist[node][0] = p.distance + weight;
                        pq.add(new Pair(node, p.distance + weight, 0));
                    }
                    if(dist[node][1] > p.distance + weight/2) {
                        dist[node][1] = p.distance + weight/2;
                        pq.add(new Pair(node, p.distance + weight/2, 1));
                    }
                }
            }
        }

        System.out.println(dist[n][1]);

    }
    
}

class Pair implements Comparable<Pair> {
    int node;
    long distance;
    int usedDiscount;

    public Pair(int node, long distance, int usedDiscount) {
        this.node = node;
        this.distance = distance;
        this.usedDiscount = usedDiscount;
    }

    public int compareTo(Pair p) {
        if(this.distance > p.distance) {
            return 1;
        } else if(this.distance < p.distance) {
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

