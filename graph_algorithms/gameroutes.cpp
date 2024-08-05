#include <iostream>
#include <vector>
#include <cstring>

using namespace std;

const int MOD = 1000000007;

int dfs(vector<vector<int>>& graph, vector<int>& paths, int node, int dest) {
    if (node == dest) {
        return 1;
    }

    if (paths[node] != -1) {
        return paths[node];
    }

    int totalPaths = 0;
    for (int neighbor : graph[node]) {
        totalPaths += dfs(graph, paths, neighbor, dest);
        totalPaths %= MOD;
    }
    paths[node] = totalPaths;
    return totalPaths;
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

    vector<int> paths(n + 1, -1);
    paths[n] = 1;
    dfs(graph, paths, 1, n);
    cout << paths[1] << endl;

    return 0;
}
