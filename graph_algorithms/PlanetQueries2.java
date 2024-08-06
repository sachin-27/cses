import java.util.*;
import java.io.*;



public class PlanetQueries2 {

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int q = sc.nextInt();

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
        List<Map<Integer, Integer>> cycleMapList = new ArrayList<>();
        for(int planet=1; planet<=n; planet++) {
            int at = planet;
            
            if(cycleId[at] != 0) {
                continue;
            }

            Set<Integer> visited = new HashSet<>();
            boolean alreadyVisited = false;
            while(!visited.contains(at)) {
                if(cycleId[at] != 0) {
                    alreadyVisited = true;
                    break;
                }
                visited.add(at);
                cycleId[at] = -1;
                at = next[at];
            }

            if(alreadyVisited) {
                continue;
            }

            // at is the start of cycle
            Map<Integer, Integer> cycleMap = new HashMap<>();
            while(!cycleMap.containsKey(at)) {
                cycleMap.put(at, cycleMap.size());
                cycleId[at] = cycleMapList.size()+1;
                at = next[at];
            }

            cycleMapList.add(cycleMap);

        }

        // calculate dist from cycle;
        int distFromCycle[] = new int[n+1];
        for(int i=0; i<=n; i++) {
            distFromCycle[i] = -1;
        }

        List<Integer> queue = new ArrayList<>();
        for(int i=1; i<=n; i++) {
            if(cycleId[i] > 0) {
                for(int prevValue : prev.get(i)) {
                    if(cycleId[prevValue] == -1) {
                        queue.add(prevValue);
                    }
                }
            } 
        }

        int curDist = 1;
        while(!queue.isEmpty()) {
            List<Integer> nextLevel = new ArrayList<>();
            for(int num : queue) {
                distFromCycle[num] = curDist;
                for(int newNum : prev.get(num)) {
                    nextLevel.add(newNum);
                }
            }
            queue = nextLevel;
            curDist++;
        }

        int log2Val = 1;
        while(1<<log2Val < n) {
            log2Val++;
        }

        int sparseTable[][] = new int[n+1][log2Val];
        for(int i=1; i<=n; i++) {
            sparseTable[i][0] = next[i];
        }

        for(int j=1; j<sparseTable[0].length; j++) {
            for(int i=1; i<=n; i++) {
                sparseTable[i][j] = sparseTable[sparseTable[i][j-1]][j-1];
            }
        }

        StringBuilder s = new StringBuilder();
        while(q-- > 0) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            int aDesc = kthDescendent(sparseTable, a, n);
            int bDesc = kthDescendent(sparseTable, b, n);

            if(a == b) {
                s.append("0\n");
                continue;
            }

            if(cycleId[aDesc] != cycleId[bDesc]) {
                s.append("-1\n");
                continue;
            }

            if(distFromCycle[a] > 0 && distFromCycle[b] > 0) {
                if(distFromCycle[a] > distFromCycle[b]) {
                    int possibleB = kthDescendent(sparseTable, a, distFromCycle[a] - distFromCycle[b]);
                    if(possibleB == b) {
                        s.append(distFromCycle[a] - distFromCycle[b] + "\n");
                        continue;
                    }
                }

                s.append("-1\n");
                continue;
            }

            if(distFromCycle[a] == -1 && distFromCycle[b] > 0) {
                s.append("-1\n");
                continue;
            }

            if(distFromCycle[b] == -1 && distFromCycle[a] > 0) {
                int ans = distFromCycle[a];
                int at = kthDescendent(sparseTable, a, ans);
                Map<Integer, Integer> cMap = cycleMapList.get(cycleId[bDesc]-1);
                int cMapSize = cMap.size();
                int bCycleIndex = cMap.get(b);
                int aCycleIndex = cMap.get(at);

                if(aCycleIndex > bCycleIndex) {
                    ans += bCycleIndex + cMapSize - aCycleIndex;
                } else {
                    ans += bCycleIndex - aCycleIndex;
                }

                s.append(ans + "\n");
                continue;
            }

            if(distFromCycle[a] == -1 && distFromCycle[b] == -1) {
                Map<Integer, Integer> cMap = cycleMapList.get(cycleId[bDesc]-1);
                int ans = 0;
                int cMapSize = cMap.size();
                int bCycleIndex = cMap.get(b);
                int aCycleIndex = cMap.get(a);

                if(aCycleIndex > bCycleIndex) {
                    ans += bCycleIndex + cMapSize - aCycleIndex;
                } else {
                    ans += bCycleIndex - aCycleIndex;
                }

                s.append(ans + "\n");
                continue; 
            }

        }

        System.out.println(s);

    }

    public static int kthDescendent(int sparseTable[][], int a, int k) {
        int num = a;
        for(int val=sparseTable[0].length-1; val>=0; val--) {
            if(((1<<val) & k) != 0) {
                num = sparseTable[num][val];
            }
        }

        return num;
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

