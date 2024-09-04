import java.util.*;
import java.io.*;



public class IncreasingSubsequence {

    private static int binSearch(List<Integer> arr, int target) {
        int start = 0;
        int end = arr.size()-1;
        while(start <= end) {
            int mid = (start+end)/2;
            if(arr.get(mid) >= target) {
                end = mid-1;
            } else {
                start = mid+1;
            }
        }

        return start;
    }

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        List<Integer> arr = new ArrayList<>();
        arr.add(sc.nextInt());
        for(int i=1; i<n; i++) {
            int val = sc.nextInt();
            int ceil = binSearch(arr, val);
            if(ceil == arr.size()) {
                arr.add(val);
            } else {
                arr.set(ceil, val);
            }
        }

        System.out.println(arr.size());
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

