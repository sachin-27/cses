import java.util.*;
import java.io.*;



public class RectangleCutting {

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int a = sc.nextInt();
        int b = sc.nextInt();

        int dp[][] = new int[a+1][b+1];

        for(int i=0; i<=a; i++) {
            for(int j=0; j<=b; j++) {
                if(i != j) {
                    dp[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for(int i=0; i<=a; i++) {
            dp[i][1] = i-1;
        }

        for(int j=0; j<=b; j++) {
            dp[1][j] = j-1;
        }

        for(int i=2; i<=a; i++) {
            for(int j=2; j<=b; j++) {
                for(int k=1; k<i; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[k][j] + dp[i-k][j] + 1);
                }

                for(int k=1; k<j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[i][j-k] + 1);
                }
            }
        }

        System.out.println(dp[a][b]);
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

