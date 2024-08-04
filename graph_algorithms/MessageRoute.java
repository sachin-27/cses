import java.util.*;
import java.io.*;



public class MessageRoute {

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();

        List<List<Integer>> graph = new ArrayList<>();
        int parent[] = new int[n+1];
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<m; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();

            graph.get(start).add(end);
            graph.get(end).add(start);
        }

        boolean visited[] = new boolean[n+1];
        Queue<int[]> q = new LinkedList<>();
        boolean found = false;
        q.add(new int[]{1, 0});
        while(!q.isEmpty()) {
            int element[] = q.poll();

            if(visited[element[0]]) {
                continue;
            }

            parent[element[0]] = element[1];
            visited[element[0]] = true;

            if(element[0] == n) {
                found = true;
                break;
            }

            for(int neighbor : graph.get(element[0])) {
                if(!visited[neighbor]) {
                    q.add(new int[]{neighbor, element[0]});
                }
            }
        }

        if(!found) {
            System.out.println("IMPOSSIBLE");
            return;
        }

        List<Integer> path = new ArrayList<>();
        int cur = n;
        while(cur != 0) {
            path.add(cur);
            cur = parent[cur];
        }

        System.out.println(path.size());
        StringBuilder s = new StringBuilder();
        for(int i=path.size()-1; i>=0; i--) {
            s.append(path.get(i) + " ");
        }

        System.out.println(s);

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

