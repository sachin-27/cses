#include <iostream>
#include <vector>
#include <stack>
#include <algorithm>

using namespace std;

void dfs(int node, vector<vector<int>>& graph, vector<int>& dist, vector<int>& forwardNode, vector<bool>& visited, int dest) {
    visited[node] = true;
    if (node == dest) {
        dist[node] = 1;
        forwardNode[node] = 0;
        return;
    }

    for (int neighbor : graph[node]) {
        if (!visited[neighbor]) {
            dfs(neighbor, graph, dist, forwardNode, visited, dest);
        }
        if (dist[neighbor] != -1 && dist[neighbor] + 1 > dist[node]) {
            dist[node] = dist[neighbor] + 1;
            forwardNode[node] = neighbor;
        }
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, m;
    cin >> n >> m;

    vector<vector<int>> graph(n + 1);
    for (int i = 0; i < m; ++i) {
        int a, b;
        cin >> a >> b;
        graph[a].emplace_back(b);
    }

    vector<int> dist(n + 1, -1);
    vector<int> forwardNode(n + 1, -1);
    vector<bool> visited(n + 1, false);

    dfs(1, graph, dist, forwardNode, visited, n);

    if (dist[1] == -1) {
        cout << "IMPOSSIBLE" << endl;
        return 0;
    }

    cout << dist[1] << "\n";
    int index = 1;
    while (index != n) {
        cout << index << " ";
        index = forwardNode[index];
    }
    cout << n << endl;

    return 0;
}
