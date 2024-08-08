import java.util.*;
import java.io.*;


public class RoadConstruction {

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();

        StringBuilder s = new StringBuilder();

        int largestComponent = 1;
        int totalComponents = n;

        DSU dsu = new DSU(n);
        for(int i=0; i<m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            if(dsu.union(a, b)) {
                totalComponents--;
            }

            largestComponent = Math.max(largestComponent, dsu.getSize(a));
            s.append(totalComponents + " ");
            s.append(largestComponent + "\n");
        }

        System.out.println(s);

    }
    
}

class DSU {
    int parent[];
    int size[];

    public DSU(int n) {
        this.parent = new int[n+1];
        this.size = new int[n+1];
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

    public int getSize(int a) {
        a = find(a);
        return size[a];
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

