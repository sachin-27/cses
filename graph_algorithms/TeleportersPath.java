import java.util.*;
import java.io.*;



public class TeleportersPath {

    public static boolean isEulerianPathPossible(int indegree[], int outdegree[]) {
        int start = -1;
        int end = -1;

        for(int i=1; i<outdegree.length; i++) {
            if(indegree[i] == outdegree[i]) {
                continue;
            }

            if(indegree[i] == outdegree[i]+1 && end == -1) {
                end = i;
                continue;
            }

            if(indegree[i]+1 == outdegree[i] && start == -1) {
                start = i;
                continue;
            }

            return false;
        }

        if(start == -1 && end == -1) {
            return true;
        } 

        if(start > 0 && end > 0) {
            return true;
        }

        return false;
    }

    public static void dfs(List<List<Integer>> graph, Stack<Integer> s, int outdegree[], int node) {
        while(outdegree[node] > 0) {
            outdegree[node]--;
            dfs(graph, s, outdegree, graph.get(node).get(outdegree[node]));
        }
        s.add(node);
    }

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();

        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }

        int indegree[] = new int[n+1];
        int outdegree[] = new int[n+1];
        for(int i=0; i<m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            graph.get(a).add(b);
            indegree[b]++;
            outdegree[a]++;
        }

        if(!isEulerianPathPossible(indegree, outdegree)) {
            System.out.println("IMPOSSIBLE");
            return;
        }

        int start = 1;
        Stack<Integer> s = new Stack<>();
        dfs(graph, s, outdegree, start);

        if(s.size() != (m+1)) {
            System.out.println("IMPOSSIBLE");
            return;
        }
        
        StringBuilder res = new StringBuilder();
        int val = -1;
        while(!s.isEmpty()) {
            val = s.pop();
            res.append(val + " ");
        }

        if(val != n) {
            System.out.println("IMPOSSIBLE");
            return;
        }

        System.out.println(res);
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

