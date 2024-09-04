import java.util.*;
import java.io.*;

// 0 is vertical split
// 1 is horizontal split

public class CountingTowers {

    private static final int MOD = 1000000007;

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int testcases = sc.nextInt();
        long dp[][] = new long[1000001][2];
        dp[1][0] = 1;
        dp[1][1] = 1;

        for(int i=2; i<dp.length; i++) {
            dp[i][0] = (dp[i-1][1]%MOD + (4 * dp[i-1][0])%MOD) % MOD;
            dp[i][1] = ((2 * dp[i-1][1])%MOD + dp[i-1][0]%MOD) % MOD;
        }

        while(testcases-- > 0) {
            int n = sc.nextInt();
            System.out.println((dp[n][0] + dp[n][1]) % MOD);
        }
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

