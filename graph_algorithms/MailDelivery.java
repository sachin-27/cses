import java.util.*;
import java.io.*;



public class MailDelivery {

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();

        List<Set<Integer>> graph = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            graph.add(new HashSet<>());
        }

        DSU dsu = new DSU(n);

        int degree[] = new int[n+1];
        for(int i=0; i<m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.get(a).add(b);
            graph.get(b).add(a);
            degree[a]++;
            degree[b]++;

            dsu.union(a, b);
        }

        int components = 0;
        Set<Integer> root = new HashSet<>();
        for(int i=1; i<=n; i++) {
            int val = dsu.find(i);
            if((!root.contains(val) && dsu.getSize(i) > 1) || (i == 1)) {
                components++;
                root.add(val);
            }
        }

        if(!isEulerPathPossible(degree) || components > 1) {
            System.out.println("IMPOSSIBLE");
            return;
        }

        StringBuilder s = new StringBuilder();
        dfs(graph, s, 1);
        System.out.println(s);
    }

    public static void dfs(List<Set<Integer>> graph, StringBuilder s, int node) {
        while(graph.get(node).size() > 0) {
            int val = -1;
            for(int num : graph.get(node)) {
                val = num;
                break;
            }
            graph.get(node).remove(val);
            graph.get(val).remove(node);
            dfs(graph, s, val);
        }
        s.append(node + " ");
    }

    public static boolean isEulerPathPossible(int degree[]) {
        for(int i=1; i<degree.length; i++) {
            if(degree[i]%2 == 1) {
                return false;
            }
        }

        return true;
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

        if(size[a] < size[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        parent[b] = a;
        size[a] += size[b];
        return true;
    }

    public int getSize(int a) {
        return size[find(a)];
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

