import java.util.*;
import java.io.*;



public class EditDistance {

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        String s = sc.next();
        String t = sc.next();

        int dp[][] = new int[s.length()+1][t.length()+1];
        for(int i=0; i<=s.length(); i++) {
            dp[i][0] = i;
        }

        for(int i=0; i<=t.length(); i++) {
            dp[0][i] = i;
        }

        for(int i=1; i<=s.length(); i++) {
            for(int j=1; j<=t.length(); j++) {
                if(s.charAt(i-1) == t.charAt(j-1)) {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j]+1, dp[i][j-1]+1), dp[i-1][j-1]);
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j]+1, dp[i][j-1]+1), dp[i-1][j-1]+1); 
                }
            }
        }

        System.out.println(dp[s.length()][t.length()]);
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

