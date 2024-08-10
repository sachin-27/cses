import java.util.*;
import java.io.*;



public class HamiltonianFlights {

    public static final int MOD = 1000000007;
    public static int[][]dp;
    public static List<List<Integer>> graph;

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();

        dp = new int[n][1<<20];
        graph = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
            if(i < n) {
                Arrays.fill(dp[i], -1);
            }
        }

        for(int i=0; i<m; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            a--;
            b--;
            graph.get(b).add(a);
        }

        int target = (1<<n)-1;
        long count = dfs(target, n-1);
        System.out.println(count);

    }

    public static int dfs(int mask, int index) {
        if(index==0 && mask == 1) {
            return 1;
        }

        if(index == 0) {
            return 0;
        }

        if((mask & (1<<index)) == 0) {
            return 0;
        }

        if(dp[index][mask] != -1) {
            return dp[index][mask];
        }

        int mask1 = mask ^ (1<<index);

        int total = 0;
        for(int neighbor : graph.get(index)) {
            total = (total + dfs(mask1, neighbor)) % MOD;
        }

        dp[index][mask] = total;
        return total;

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

