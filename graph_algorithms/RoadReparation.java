import java.util.*;
import java.io.*;



public class RoadReparation {

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();

        DSU dsu = new DSU(n);
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for(int i=0; i<m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            long c = sc.nextLong();

            pq.add(new Edge(a, b, c));
        }

        long cost = 0;
        int count = 0;
        while(!pq.isEmpty()) {
            Edge e = pq.poll();

            if(dsu.union(e.from, e.to)) {
                cost += e.weight;
                count++;
            }
        }

        if(count != n-1) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(cost);
        }

    }
    
}

class Edge implements Comparable<Edge> {
    int from;
    int to;
    long weight;

    public Edge(int from, int to, long weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public int compareTo(Edge e) {
        if(this.weight > e.weight) {
            return 1;
        } else if(this.weight < e.weight) {
            return -1;
        }

        return 0;
    }
}

class DSU {
    int parent[];
    int size[];

    public DSU(int n) {
        parent = new int[n+1];
        size = new int[n+1];

        for(int i=0; i<=n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int find(int a) {
        if(a == parent[a]) {
            return a;
        }

        parent[a] = find(parent[a]);
        return parent[a];
    }

    public boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a == b) {
            return false;
        }

        if(size[a] < size[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        size[a] += size[b];
        parent[b] = a;
        return true;
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

