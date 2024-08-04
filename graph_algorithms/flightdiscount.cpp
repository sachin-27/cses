#include <iostream>
#include <vector>
#include <climits>
#include <string>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
     cin.tie(NULL);

    int n, m;
    cin >> n >> m;

    vector< vector < int > > edges(m + n, vector< int >(3));
    for (int i = 0; i < n; i++) {
        edges[i][0] = 0;
        edges[i][1] = i + 1;
        edges[i][2] = 0;
    }
    for (int i = n; i < n + m; i++) {
        for (int j = 0; j < 3; j++) {
            cin >> edges[i][j];
        }
    }

    vector<long long> dist(n + 1, LLONG_MAX);
    vector<int> parent(n + 1, -1);
    dist[0] = 0;

    // Bellman-Ford for n iterations for SSSP
    for (int i = 0; i < n; i++) {
        for (auto &edge : edges) {
            int src = edge[0];
            int dest = edge[1];
            long long weight = edge[2];

            if (dist[src] != LLONG_MAX && dist[src] + weight < dist[dest]) {
                dist[dest] = dist[src] + weight;
                parent[dest] = src;
            }
        }
    }

    // Bellman-Ford for n more iterations to detect negative cycle
    int index = -1;
    for (int i = 0; i < n; i++) {
        for (auto &edge : edges) {
            int src = edge[0];
            int dest = edge[1];
            long long weight = edge[2];

            if (dist[src] != LLONG_MAX && dist[src] + weight < dist[dest]) {
                index = src;
                break;
            }
        }
    }

    if (index == -1) {
        cout << "NO" << endl;
    } else {
        vector<int> path;
        path.push_back(index);
        int temp = index;
        temp = parent[temp];
        while (temp != index) {
            path.push_back(temp);
            temp = parent[temp];
        }
        path.push_back(index);

        cout << "YES" << endl;
        for (int i = path.size() - 1; i >= 0; i--) {
            cout << path[i] << " ";
        }
        cout << endl;
    }

    return 0;
}
