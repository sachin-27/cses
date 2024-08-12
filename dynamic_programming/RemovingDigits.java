import java.util.*;
import java.io.*;



public class RemovingDigits {

    private static int dp[];

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();

        dp = new int[n+1];
        for(int i=1; i<=n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0;

        for(int i=1; i<=n; i++) {
            int num = i;
            while(num != 0) {
                if(num%10 != 0) {
                    dp[i] = Math.min(dp[i], dp[i-num%10]+1);
                }
                num /= 10;
            }
        }
        System.out.println(dp[n]);
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

