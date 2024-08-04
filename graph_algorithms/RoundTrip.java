import java.util.*;
import java.io.*;



public class RoundTrip {

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();

        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<m; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            graph.get(start).add(end);
            graph.get(end).add(start);
        }

        
        int parent[] = new int[n+1];
        boolean visited[] = new boolean[n+1];
        int start = -1;
        int end = -1;
        Stack<int[]> q = new Stack<>();
        for(int i=1; i<=n; i++) {
            if(!visited[i]) {
                q.add(new int[]{i, -1});
            }
            while(!q.isEmpty()) {
                int[] value = q.pop();

                int cur = value[0];
                int prev = value[1];

                if(visited[cur]) {
                    // found cycle
                    start = cur;
                    end = prev;
                    break;
                }

                visited[cur] = true;
                parent[cur] = prev;

                for(int neighbor : graph.get(cur)) {
                    if(neighbor == prev) {
                        continue;
                    }

                    q.add(new int[]{neighbor, cur});
                }
            }

            if(start != -1) {
                break;
            }
        }

        if(start == -1) {
            System.out.println("IMPOSSIBLE");
            return;
        }

        int cur = end;
        List<Integer> path = new ArrayList<>();
        while(cur != start) {
            path.add(cur);
            cur = parent[cur];
        };
        path.add(start);

        StringBuilder s = new StringBuilder();
        for(int i=path.size()-1; i>=0; i--) {
            s.append(path.get(i) + " ");
        }

        s.append(start + " ");

        System.out.println(path.size()+1);
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

