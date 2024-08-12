import java.util.*;
import java.io.*;



public class CoinCombinations1 {

    public static final int MOD = 1000000007;

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int x = sc.nextInt();

        int coins[] = new int[n];
        for(int i=0; i<n; i++) {
            coins[i] = sc.nextInt();
        }

        int dp[] = new int[x+1];
        dp[0] = 1;

        for(int i=0; i<x; i++) {
            for(int coin : coins) {
                if(i+coin <= x) {
                    dp[i+coin] += dp[i];
                    dp[i+coin] %= MOD;
                }
            }
        }

        System.out.println(dp[x]);
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

