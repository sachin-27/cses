import java.util.*;
import java.io.*;



public class BuildingRoads {

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();

        DSU dsu = new DSU(n);
        int totalComponents = n;
        for(int i=0; i<m; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();

            boolean wasUnioned = dsu.union(start, end);
            if(wasUnioned) {
                totalComponents--;
            }
        }

        StringBuilder s = new StringBuilder();
        s.append((totalComponents-1) + "\n");
        for(int i=1; i<n; i++) {
            boolean wasUnioned = dsu.union(i, i+1);
            if(wasUnioned) {
                s.append(i + " " + (i+1) + "\n");
            }
        }

        System.out.println(s);

    }
    
}

class DSU {
    private int parent[];
    private int size[];

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

        if(size[a] < size[b]){
            int temp = a;
            a = b;
            b = temp;
        }

        parent[b] = a;
        size[a] += b;

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

