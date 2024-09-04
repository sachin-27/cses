import java.util.*;
import java.io.*;



public class ArrayDescription {

    private static final int MOD = 1000000007;

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        int arr[] = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }

        int dp[][] = new int[n][m];
        for(int j=0; j<m; j++) {
            if(arr[0] == 0) {
                dp[0][j] = 1;
            } else {
                if(j == arr[0]-1) {
                    dp[0][j] = 1;
                } else {
                    dp[0][j] = 0;
                }
            }
        }
        for(int i=1; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(arr[i] == 0) {
                    for(int diff=-1; diff<=1; diff++) {
                        if(j+diff<m && j+diff>=0) {
                            dp[i][j] += dp[i-1][j+diff];
                            dp[i][j] %= MOD;
                        }
                    }
                } else {
                    if(j != arr[i]-1) {
                        dp[i][j] = 0;
                    } else {
                        for(int diff=-1; diff<=1; diff++) {
                            if(j+diff<m && j+diff>=0) {
                                dp[i][j] += dp[i-1][j+diff];
                                dp[i][j] %= MOD;
                            }
                        } 
                    }
                }
            }
        }

        int count = 0;
        for(int j=0; j<m; j++) {
            count += dp[n-1][j];
            count %= MOD;
        }

        System.out.println(count);
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

