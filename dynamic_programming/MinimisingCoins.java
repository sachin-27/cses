import java.util.*;
import java.io.*;



public class MinimisingCoins {

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int x = sc.nextInt();

        int coins[] = new int[n];
        for(int i=0; i<n; i++) {
            coins[i] = sc.nextInt();
        }

        int dp[] = new int[x+1];
        for(int i=1; i<=x; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        for(int i=0; i<x; i++) {
            if(dp[i] == Integer.MAX_VALUE) {
                continue;
            }

            for(int coin : coins) {
                if(i + coin <= x) {
                    dp[i+coin] = Math.min(dp[i]+1, dp[i+coin]);
                }
            }
        }

        if(dp[x] == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(dp[x]);
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

