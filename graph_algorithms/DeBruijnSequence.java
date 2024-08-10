import java.util.*;
import java.io.*;



public class DeBruijnSequence {

    public static void generateStrings(StringBuilder s, Set<String> set, int n) {
        if(s.length() == n) {
            set.add(s.toString());
            return;
        }

        for(int i=0; i<2; i++) {
            s.append(i);
            generateStrings(s, set, n);
            s.setLength(s.length()-1);
        }
    }

    public static boolean dfs(Set<String> set, StringBuilder res, int n) {
        if(set.size() == 0) {
            return true;
        }

        for(int i=0; i<2; i++) {
            String cur = res.substring(res.length()-n+1, res.length());
            if(set.contains(cur+i)) {
                set.remove(cur+i);
                res.append(i);
                boolean possible = dfs(set, res, n);
                if(possible) {
                    return true;
                }
                set.add(cur+i);
                res.setLength(res.length()-1);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        StringBuilder s = new StringBuilder();
        Set<String> set = new HashSet<>();
        generateStrings(s, set, n);

        String start="";
        for(String st : set) {
            start = st;
            break;
        }

        StringBuilder res = new StringBuilder();
        res.append(start);
        set.remove(start);
        dfs(set, res, n);

        System.out.println(res);
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

