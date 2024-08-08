import java.util.*;
import java.io.*;



public class PlanetsCycles {

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int next[] = new int[n+1];
        List<List<Integer>> prev = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            prev.add(new ArrayList<>());
        }

        for(int i=1; i<=n; i++) {
            next[i] = sc.nextInt();
            prev.get(next[i]).add(i);
        }

        int cycleId[] = new int[n+1];
        int componentId[] = new int[n+1];
        HashMap<Integer, Integer> cycleSizeMap = new HashMap<>();
        for(int planet=1; planet<=n; planet++) {
            if(cycleId[planet] != 0) {
                continue;
            }

            int at = planet;
            Set<Integer> visited = new HashSet<>();
            boolean alreadyVisitedCycle = false;
            while(!visited.contains(at)) {
                if(cycleId[at] != 0) {
                    alreadyVisitedCycle = true;
                    break;
                }
                visited.add(at);
                cycleId[at] = -1;
                componentId[at] = -1;
                at = next[at];
            }

            if(alreadyVisitedCycle) {
                continue;
            }

            // at a cycle node;
            int temp = next[at];
            cycleId[at] = cycleSizeMap.size()+1;
            componentId[at] = cycleSizeMap.size()+1;
            int count = 1;
            while(temp != at) {
                count++;
                cycleId[temp] = cycleSizeMap.size()+1;
                componentId[temp] = cycleSizeMap.size()+1;
                temp = next[temp];
            }


            cycleSizeMap.put(cycleSizeMap.size()+1, count);
        }

        int distFromCycle[] = new int[n+1];
        List<Pair> level = new ArrayList<>();
        int dist = 1;
        for(int i=1; i<=n; i++) {
            if(cycleId[i] > 0) {
                for(int num : prev.get(i)) {
                    if(cycleId[num] == -1) {
                        level.add(new Pair(num, componentId[i]));
                    }
                }
            }
        }

        while(!level.isEmpty()) {
            List<Pair> newLevel = new ArrayList<>();
            for(Pair num : level) {
                distFromCycle[num.num] = dist;
                componentId[num.num] = num.component;
                for(int newNum : prev.get(num.num)) {
                    newLevel.add(new Pair(newNum, num.component));
                }
            }
            dist++;
            level = newLevel;
        }

        StringBuilder s = new StringBuilder();
        for(int i=1; i<=n; i++) {
            int ans = 0;
            if(cycleId[i] == -1) {
                ans = distFromCycle[i] + cycleSizeMap.get(componentId[i]);
            } else {
                ans = cycleSizeMap.get(cycleId[i]);
            }

            s.append(ans + " ");
        }
        System.out.println(s);

    }
    
}

class Pair {
    int num;
    int component;

    public Pair(int num, int component) {
        this.num = num;
        this.component = component;
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

