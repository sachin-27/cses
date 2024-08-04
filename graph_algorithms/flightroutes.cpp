#include <iostream>
#include <vector>
#include <queue>
#include <climits>
#include <sstream>
#include <algorithm>

using namespace std;

struct Pair {
    int node;
    long long curDist;
    
    Pair(int n, long long d) : node(n), curDist(d) {}

    bool operator<(const Pair& other) const {
        return curDist > other.curDist; // for min-heap
    }
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, m, k;
    cin >> n >> m >> k;

    vector<vector<pair<int, int>>> graph(n + 1);
    for (int i = 0; i < m; i++) {
        int src, dest, weight;
        cin >> src >> dest >> weight;
        graph[src].emplace_back(dest, weight);
    }

    vector<vector<long long>> dist(n + 1, vector<long long>(k, LLONG_MAX));
    priority_queue<Pair> pq;
    pq.emplace(1, 0);

    while (!pq.empty()) {
        Pair p = pq.top();
        pq.pop();

        for (int i = 0; i < k; i++) {
            if (dist[p.node][i] > p.curDist) {
                dist[p.node][i] = p.curDist;
                for (auto& neighbor : graph[p.node]) {
                    pq.emplace(neighbor.first, p.curDist + neighbor.second);
                }
                break;
            }
        }
    }

    stringstream s;
    for (int i = 0; i < k; i++) {
        s << dist[n][i] << " ";
    }
    cout << s.str() << endl;

    return 0;
}
