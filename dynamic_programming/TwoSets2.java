import java.util.*;
import java.io.*;



public class TwoSets2 {

    private static final int MOD = 1000000007;

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();

        int sum = (n * (n+1))/2;
        if((sum&1) != 0) {
            System.out.println(0);
            return;
        }

        int target = sum/2;
        long dp[][] = new long[target+1][n+1];
        for(int i=0; i<n; i++) {
            dp[0][i] = 1;
        }

        for(int i=1; i<=target; i++) {
            for(int j=1; j<=n; j++) {
                dp[i][j] = dp[i][j-1] % MOD;
                if(i-j >= 0) {
                    dp[i][j] += dp[i-j][j-1] % MOD;
                    dp[i][j] %= MOD;
                }
            }
        }

        System.out.println((dp[target][n] * 500000004)%MOD);
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

