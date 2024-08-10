#include <iostream>
#include <vector>
#include <stack>
#include <map>
#include <algorithm>

using namespace std;

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

long long dfs3(const vector<vector<int>>& graph, vector<long long>& total, const vector<long long>& coins, int node) {
    if (total[node] != 0) return total[node];
    
    long long res = 0;
    for (int neighbor : graph[node]) {
        res = max(res, dfs3(graph, total, coins, neighbor));
    }
    res += coins[node];
    total[node] = res;
    return total[node];
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, m;
    cin >> n >> m;

    vector<long long> coins(n + 1);
    for (int i = 1; i <= n; i++) {
        cin >> coins[i];
    }

    vector<vector<int>> graph(n + 1);
    vector<vector<int>> transposedGraph(n + 1);
    
    for (int i = 0; i < m; i++) {
        int a, b;
        cin >> a >> b;
        graph[a].push_back(b);
        transposedGraph[b].push_back(a);
    }

    stack<int> order;
    vector<bool> visited(n + 1, false);
    
    for (int i = 1; i <= n; i++) {
        if (!visited[i]) {
            dfs(graph, visited, order, i);
        }
    }

    vector<int> componentId(n + 1, 0);
    int count = 1;
    
    while (!order.empty()) {
        int element = order.top();
        order.pop();
        
        if (componentId[element] == 0) {
            dfs2(transposedGraph, componentId, element, count);
            count++;
        }
    }

    vector<long long> compCoins(count, 0);
    
    for (int i = 1; i <= n; i++) {
        compCoins[componentId[i]] += coins[i];
    }

    vector<vector<int>> condensedGraph(count);
    
    for (int i = 1; i <= n; i++) {
        for (int neighbor : graph[i]) {
            if (componentId[i] != componentId[neighbor]) {
                condensedGraph[componentId[i]].push_back(componentId[neighbor]);
            }
        }
    }

    vector<long long> total(count, 0);
    long long maxVal = 0;
    
    for (int i = 1; i < count; i++) {
        if (total[i] == 0) {
            maxVal = max(maxVal, dfs3(condensedGraph, total, compCoins, i));
        }
    }

    cout << maxVal << endl;

    return 0;
}
