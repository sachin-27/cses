import java.util.*;
import java.io.*;



public class RemovalGame {

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int arr[] = new int[n];
        for(int i=0; i<arr.length; i++) {
            arr[i] = sc.nextInt();
        }

        long dp[][] = new long[arr.length][arr.length];
        for(int i=0; i<arr.length; i++) {
            dp[i][i] = arr[i];
            if(i == arr.length-1) {
                continue;
            }
            dp[i][i+1] = Math.max(arr[i], arr[i+1]);
        }

        for(int start=arr.length-1; start>=0; start--) {
            for(int end=start+2; end<=arr.length-1; end++) {
                long a = arr[start] + Math.min(dp[start+2][end], dp[start+1][end-1]);
                long b = arr[end] + Math.min(dp[start+1][end-1], dp[start][end-2]);
                dp[start][end] = Math.max(a, b);
            }
        }


        System.out.println(dp[0][arr.length-1]);
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

