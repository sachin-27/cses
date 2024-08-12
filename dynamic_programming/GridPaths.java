import java.util.*;
import java.io.*;



public class GridPaths {

    public static final int MOD = 1000000007;
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        char grid[][] = new char[n][n];
        int dp[][] = new int[n][n];
        for(int i=0; i<n; i++) {
            String s = sc.next();
            for(int j=0; j<s.length(); j++) {
                grid[i][j] = s.charAt(j);
            }
        }

        if(grid[0][0] == '*' || grid[n-1][n-1] == '*') {
            System.out.println(0);
            return;
        }

        dp[0][0] = 1;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(grid[i][j] == '*') {
                    continue;
                }
                if(i+1 < n) {
                    dp[i+1][j] += dp[i][j];
                    dp[i+1][j] %= MOD;
                }
                if(j+1 < n) {
                    dp[i][j+1] += dp[i][j];
                    dp[i][j+1] %= MOD;
                }
            }
        }

        System.out.println(dp[n-1][n-1]);
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

