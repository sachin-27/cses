#include <iostream>
#include <vector>
#include <unordered_map>
#include <unordered_set>
#include <queue>

using namespace std;

int kthDescendent(vector<vector<int>>& sparseTable, int a, int k) {
    int num = a;
    int log2Val = sparseTable[0].size();
    for (int val = log2Val - 1; val >= 0; val--) {
        if ((1 << val) & k) {
            num = sparseTable[num][val];
        }
    }
    return num;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, q;
    cin >> n >> q;

    vector<int> next(n + 1);
    vector<vector<int>> prev(n + 1);

    for (int i = 1; i <= n; ++i) {
        cin >> next[i];
        prev[next[i]].push_back(i);
    }

    vector<int> cycleId(n + 1, 0);
    vector<unordered_map<int, int>> cycleMapList;

    for (int planet = 1; planet <= n; ++planet) {
        int at = planet;

        if (cycleId[at] != 0) {
            continue;
        }

        unordered_set<int> visited;
        bool alreadyVisited = false;

        while (!visited.count(at)) {
            if (cycleId[at] != 0) {
                alreadyVisited = true;
                break;
            }
            visited.insert(at);
            cycleId[at] = -1;
            at = next[at];
        }

        if (alreadyVisited) {
            continue;
        }

        unordered_map<int, int> cycleMap;
        while (!cycleMap.count(at)) {
            cycleMap[at] = cycleMap.size();
            cycleId[at] = cycleMapList.size() + 1;
            at = next[at];
        }

        cycleMapList.push_back(cycleMap);
    }

    vector<int> distFromCycle(n + 1, -1);
    queue<int> queue;

    for (int i = 1; i <= n; ++i) {
        if (cycleId[i] > 0) {
            for (int prevValue : prev[i]) {
                if (cycleId[prevValue] == -1) {
                    queue.push(prevValue);
                }
            }
        }
    }

    int curDist = 1;
    while (!queue.empty()) {
        int size = queue.size();
        while (size--) {
            int num = queue.front();
            queue.pop();
            distFromCycle[num] = curDist;
            for (int newNum : prev[num]) {
                queue.push(newNum);
            }
        }
        curDist++;
    }

    int log2Val = 1;
    while ((1 << log2Val) < n) {
        log2Val++;
    }

    vector<vector<int>> sparseTable(n + 1, vector<int>(log2Val));
    for (int i = 1; i <= n; ++i) {
        sparseTable[i][0] = next[i];
    }

    for (int j = 1; j < log2Val; ++j) {
        for (int i = 1; i <= n; ++i) {
            sparseTable[i][j] = sparseTable[sparseTable[i][j - 1]][j - 1];
        }
    }

    while (q-- > 0) {
        int a, b;
        cin >> a >> b;

        int aDesc = kthDescendent(sparseTable, a, n);
        int bDesc = kthDescendent(sparseTable, b, n);

        if (a == b) {
            cout << "0\n";
            continue;
        }

        if (cycleId[aDesc] != cycleId[bDesc]) {
            cout << "-1\n";
            continue;
        }

        if (distFromCycle[a] > 0 && distFromCycle[b] > 0) {
            if (distFromCycle[a] > distFromCycle[b]) {
                int possibleB = kthDescendent(sparseTable, a, distFromCycle[a] - distFromCycle[b]);
                if (possibleB == b) {
                    cout << distFromCycle[a] - distFromCycle[b] << "\n";
                    continue;
                }
            }
            cout << "-1\n";
            continue;
        }

        if (distFromCycle[a] == -1 && distFromCycle[b] > 0) {
            cout << "-1\n";
            continue;
        }

        if (distFromCycle[b] == -1 && distFromCycle[a] > 0) {
            int ans = distFromCycle[a];
            int at = kthDescendent(sparseTable, a, ans);
            auto& cMap = cycleMapList[cycleId[bDesc] - 1];
            int cMapSize = cMap.size();
            int bCycleIndex = cMap[b];
            int aCycleIndex = cMap[at];

            if (aCycleIndex > bCycleIndex) {
                ans += bCycleIndex + cMapSize - aCycleIndex;
            } else {
                ans += bCycleIndex - aCycleIndex;
            }

            cout << ans << "\n";
            continue;
        }

        if (distFromCycle[a] == -1 && distFromCycle[b] == -1) {
            auto& cMap = cycleMapList[cycleId[bDesc] - 1];
            int ans = 0;
            int cMapSize = cMap.size();
            int bCycleIndex = cMap[b];
            int aCycleIndex = cMap[a];

            if (aCycleIndex > bCycleIndex) {
                ans += bCycleIndex + cMapSize - aCycleIndex;
            } else {
                ans += bCycleIndex - aCycleIndex;
            }

            cout << ans << "\n";
            continue;
        }
    }

    return 0;
}
