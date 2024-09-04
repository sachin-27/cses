import java.util.*;
import java.io.*;



public class MoneySums {

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int coins[] = new int[n];
        int totalSum = 0;
        for(int i=0; i<n; i++) {
            coins[i] = sc.nextInt();
            totalSum += coins[i];
        }

        Arrays.sort(coins);

        boolean dp[][] = new boolean[100001][101];
        for(int i=0; i<=coins.length; i++) {
            dp[0][i] = true;
        }

        for(int sum=1; sum<=totalSum; sum++) {
            for(int coin=1; coin<=coins.length; coin++) {
                dp[sum][coin] = dp[sum][coin-1];
                if(sum - coins[coin-1] >= 0) {
                    dp[sum][coin] = dp[sum][coin] || dp[sum-coins[coin-1]][coin-1];
                }
            }
        }

        StringBuilder s = new StringBuilder();
        int count = 0;
        for(int i=1; i<=totalSum; i++) {
            if(dp[i][coins.length]) {
                count++;
                s.append(i + " ");
            }
        }
        System.out.println(count);
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

