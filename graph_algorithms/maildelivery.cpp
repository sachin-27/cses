#include <iostream>
#include <vector>
#include <unordered_set>
#include <unordered_map>
#include <stack>
#include <algorithm>

using namespace std;

class DSU {
private:
    vector<int> parent, size;

public:
    DSU(int n) {
        parent.resize(n + 1);
        size.resize(n + 1);
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    int find(int a) {
        if (a == parent[a]) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    bool union_sets(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) {
            return false;
        }

        if (size[a] < size[b]) {
            swap(a, b);
        }

        parent[b] = a;
        size[a] += size[b];
        return true;
    }

    int get_size(int a) {
        return size[find(a)];
    }
};

void dfs(vector<unordered_set<int>>& graph, int node) {
    while(!graph[node].empty()) {
        int neighbor = *(graph[node]).begin();
        graph[node].erase(neighbor);
        graph[neighbor].erase(node);
        dfs(graph, neighbor);
    }
    cout << node << " ";
}

bool is_euler_path_possible(vector<int>& degree) {
    for (int i = 1; i < degree.size(); i++) {
        if (degree[i] % 2 != 0) {
            return false;
        }
    }
    return true;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, m;
    cin >> n >> m;

    vector<unordered_set<int>> graph(n + 1);
    DSU dsu(n);

    vector<int> degree(n + 1, 0);
    for (int i = 0; i < m; i++) {
        int a, b;
        cin >> a >> b;
        graph[a].insert(b);
        graph[b].insert(a);
        degree[a]++;
        degree[b]++;

        dsu.union_sets(a, b);
    }

    int components = 0;
    unordered_set<int> root;
    for (int i = 1; i <= n; i++) {
        int val = dsu.find(i);
        if ((!root.count(val) && dsu.get_size(i) > 1) || (i == 1)) {
            components++;
            root.insert(val);
        }
    }

    if (!is_euler_path_possible(degree) || components > 1) {
        cout << "IMPOSSIBLE" << endl;
        return 0;
    }

    dfs(graph, 1);

    return 0;
}
