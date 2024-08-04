import java.util.*;
import java.io.*;



public class ShortestRoutes1 {

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        List<List<Pair>> graph = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<m; i++) {
            int src = sc.nextInt();
            int dest = sc.nextInt();
            long weight = sc.nextLong();

            graph.get(src).add(new Pair(dest, weight));
        }

        long[] dist = new long[n+1];
        for(int i=0; i<=n; i++) {
            dist[i] = Long.MAX_VALUE;
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(1, 0));

        while(!pq.isEmpty()) {
            Pair p = pq.poll();

            if(dist[p.node] < p.weight) {
                continue;
            }

            dist[p.node] = p.weight;

            for(Pair neighbor : graph.get(p.node)) {
                int neighborNode = neighbor.node;
                long neighborWeight = neighbor.weight;

                if(dist[neighborNode] > p.weight + neighborWeight) {
                    pq.add(new Pair(neighborNode, p.weight + neighborWeight));
                }
            }
        }

        StringBuilder s = new StringBuilder();
        for(int i=1; i<=n; i++) {
            s.append(dist[i] + " ");
        }

        System.out.println(s);
    }
    
}

class Pair implements Comparable<Pair> {
    int node;
    long weight;

    public Pair(int node, long weight) {
        this.node = node;
        this.weight = weight;
    }

    public int compareTo(Pair p) {
        if(this.weight > p.weight) {
            return 1;
        } else if(this.weight < p.weight) {
            return -1;
        }
        return 0;
    }
}

class FastScanner { 
    final private int BUFFER_SIZE = 1 << 16; 
    private DataInputStream din; 
    private byte[] buffer; 
    private int bufferPointer, bytesRead; 

    public FastScanner() 
    { 
        din = new DataInputStream(System.in); 
        buffer = new byte[BUFFER_SIZE]; 
        bufferPointer = bytesRead = 0; 
    } 

    public FastScanner(String file_name) throws IOException 
    { 
        din = new DataInputStream( 
            new FileInputStream(file_name)); 
        buffer = new byte[BUFFER_SIZE]; 
        bufferPointer = bytesRead = 0; 
    } 

    public String readLine() throws IOException 
    { 
        byte[] buf = new byte[64]; // line length 
        int cnt = 0, c; 
        while ((c = read()) != -1) { 
            if (c == '\n') { 
                if (cnt != 0) { 
                    break; 
                } 
                else { 
                    continue; 
                } 
            } 
            buf[cnt++] = (byte)c; 
        } 
        return new String(buf, 0, cnt); 
    } 

    public int nextInt() throws IOException 
    { 
        int ret = 0; 
        byte c = read(); 
        while (c <= ' ') { 
            c = read(); 
        } 
        boolean neg = (c == '-'); 
        if (neg) 
            c = read(); 
        do { 
            ret = ret * 10 + c - '0'; 
        } while ((c = read()) >= '0' && c <= '9'); 

        if (neg) 
            return -ret; 
        return ret; 
    } 

    public long nextLong() throws IOException 
    { 
        long ret = 0; 
        byte c = read(); 
        while (c <= ' ') 
            c = read(); 
        boolean neg = (c == '-'); 
        if (neg) 
            c = read(); 
        do { 
            ret = ret * 10 + c - '0'; 
        } while ((c = read()) >= '0' && c <= '9'); 
        if (neg) 
            return -ret; 
        return ret; 
    } 

    public double nextDouble() throws IOException 
    { 
        double ret = 0, div = 1; 
        byte c = read(); 
        while (c <= ' ') 
            c = read(); 
        boolean neg = (c == '-'); 
        if (neg) 
            c = read(); 

        do { 
            ret = ret * 10 + c - '0'; 
        } while ((c = read()) >= '0' && c <= '9'); 

        if (c == '.') { 
            while ((c = read()) >= '0' && c <= '9') { 
                ret += (c - '0') / (div *= 10); 
            } 
        } 

        if (neg) 
            return -ret; 
        return ret; 
    } 

    private void fillBuffer() throws IOException 
    { 
        bytesRead = din.read(buffer, bufferPointer = 0, 
                             BUFFER_SIZE); 
        if (bytesRead == -1) 
            buffer[0] = -1; 
    } 

    private byte read() throws IOException 
    { 
        if (bufferPointer == bytesRead) 
            fillBuffer(); 
        return buffer[bufferPointer++]; 
    } 

    public void close() throws IOException 
    { 
        if (din == null) 
            return; 
        din.close(); 
    } 
} 

