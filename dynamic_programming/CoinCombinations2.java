import java.util.*;
import java.io.*;



public class CoinCombinations2 {

    public static final int MOD = 1000000007;

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int x = sc.nextInt();

        int coins[] = new int[n];
        for(int i=0; i<n; i++) {
            coins[i] = sc.nextInt();
        }

        Arrays.sort(coins);

        int dp[] = new int[x+1];
        dp[0] = 1;

        for(int i=0; i<n; i++) {
            for(int j=0; j<x; j++) {
                if(j + coins[i] <= x) {
                    dp[j+coins[i]] += dp[j];
                    dp[j+coins[i]] %= MOD;
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


