#include <iostream>
#include <vector>
#include <stack>
#include <string>
#include <cstring>

using namespace std;

bool isEulerianPathPossible(int indegree[], int outdegree[], int n) {
    int start = -1;
    int end = -1;

    for (int i = 1; i <= n; i++) {
        if (indegree[i] == outdegree[i]) {
            continue;
        }

        if (indegree[i] == outdegree[i] + 1 && end == -1) {
            end = i;
            continue;
        }

        if (indegree[i] + 1 == outdegree[i] && start == -1) {
            start = i;
            continue;
        }

        return false;
    }

    if (start == -1 && end == -1) {
        return true;
    }

    if (start > 0 && end > 0) {
        return true;
    }

    return false;
}

void dfs(vector<vector<int>>& graph, stack<int>& s, int outdegree[], int node) {
    while (outdegree[node] > 0) {
        outdegree[node]--;
        dfs(graph, s, outdegree, graph[node][outdegree[node]]);
    }
    s.push(node);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, m;
    cin >> n >> m;

    vector<vector<int>> graph(n + 1);
    int indegree[n + 1], outdegree[n + 1];
    memset(indegree, 0, sizeof(indegree));
    memset(outdegree, 0, sizeof(outdegree));

    for (int i = 0; i < m; i++) {
        int a, b;
        cin >> a >> b;
        graph[a].push_back(b);
        indegree[b]++;
        outdegree[a]++;
    }

    if (!isEulerianPathPossible(indegree, outdegree, n)) {
        cout << "IMPOSSIBLE" << endl;
        return 0;
    }

    int start = 1;
    stack<int> s;
    dfs(graph, s, outdegree, start);

    if (s.size() != (m + 1)) {
        cout << "IMPOSSIBLE" << endl;
        return 0;
    }

    string res;
    int val = -1;
    while (!s.empty()) {
        val = s.top();
        s.pop();
        res += to_string(val) + " ";
    }

    if (val != n) {
        cout << "IMPOSSIBLE" << endl;
        return 0;
    }

    cout << res << endl;

    return 0;
}
