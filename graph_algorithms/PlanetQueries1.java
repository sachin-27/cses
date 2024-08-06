import java.util.*;
import java.io.*;



public class PlanetQueries1 {

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int q = sc.nextInt();

        int child[] = new int[n+1];
        for(int i=1; i<=n; i++) {
            child[i] = sc.nextInt();
        }

        int grid[][] = new int[n+1][30];
        for(int i=1; i<=n; i++) {
            grid[i][0] = child[i];
        }

        for(int power=1; power<30; power++) {
            for(int i=1; i<=n; i++) {
                grid[i][power] = grid[grid[i][power-1]][power-1];
            }
        }

        StringBuilder s = new StringBuilder();

        while(q-- > 0) {
            int x = sc.nextInt();
            int k = sc.nextInt();

            int ans = x;
            for(int bit=29; bit>=0; bit--) {
                int val = 1<<bit;
                
                if((val & k) != 0) {
                    ans = grid[ans][bit];
                }
            }

            s.append(ans + "\n");
        }

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

