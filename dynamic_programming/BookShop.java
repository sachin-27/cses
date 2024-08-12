import java.util.*;
import java.io.*;



public class BookShop {

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int x = sc.nextInt();

        int prices[] = new int[n];
        for(int i=0; i<n; i++) {
            prices[i] = sc.nextInt();
        }

        int pages[] = new int[n];
        for(int i=0; i<n; i++) {
            pages[i] = sc.nextInt();
        }

        int dp[] = new int[x+1];
        for(int i=1; i<=n; i++) {
            for(int j=x; j>=0; j--) {
                if(j-prices[i-1] < 0 ){
                    continue;
                }
                dp[j] = Math.max(dp[j], dp[j-prices[i-1]]+pages[i-1]);
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

