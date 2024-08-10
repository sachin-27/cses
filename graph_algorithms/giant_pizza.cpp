#include <iostream>
#include <vector>
#include <stack>

using namespace std;

int m;

int not_op(int a) {
    return (a > m) ? a - m : a + m;
}

void dfs(const vector<vector<int>>& graph, vector<bool>& visited, stack<int>& order, int node) {
    if (visited[node]) return;
    
    visited[node] = true;
    for (int neighbor : graph[node]) {
        dfs(graph, visited, order, neighbor);
    }
    order.push(node);
}

void dfs2(const vector<vector<int>>& graph, vector<int>& componentId, int node, int count) {
    if (componentId[node] != 0) return;
    
    componentId[node] = count;
    for (int neighbor : graph[node]) {
        dfs2(graph, componentId, neighbor, count);
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    cin >> n >> m;

    vector<vector<int>> graph(2 * m + 1);
    vector<vector<int>> transposedGraph(2 * m + 1);

    for (int i = 1; i <= n; ++i) {
        char a, c;
        int b, d;
        cin >> a >> b >> c >> d;

        if (a == '-') b = not_op(b);
        if (c == '-') d = not_op(d);

        graph[not_op(b)].push_back(d);
        transposedGraph[d].push_back(not_op(b));

        graph[not_op(d)].push_back(b);
        transposedGraph[b].push_back(not_op(d));
    }

    stack<int> order;
    vector<bool> visited(2 * m + 1, false);
    
    for (int i = 1; i <= 2 * m; ++i) {
        if (!visited[i]) {
            dfs(graph, visited, order, i);
        }
    }

    vector<int> componentId(2 * m + 1, 0);
    int count = 1;
    
    while (!order.empty()) {
        int element = order.top();
        order.pop();

        if (componentId[element] == 0) {
            dfs2(transposedGraph, componentId, element, count++);
        }
    }

    for (int i = 1; i <= m; ++i) {
        if (componentId[i] == componentId[not_op(i)]) {
            cout << "IMPOSSIBLE\n";
            return 0;
        }
    }

    for (int i = 1; i <= m; ++i) {
        if (componentId[i] > componentId[not_op(i)]) {
            cout << "+ ";
        } else {
            cout << "- ";
        }
    }
    cout << endl;

    return 0;
}
