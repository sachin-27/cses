#include <iostream>
#include <vector>
#include <climits>
#include <cstring>
 
using namespace std;
 
const int MOD = 1000000007;
const long long INF = LLONG_MAX;
 
struct Edge {
    int to;
    long long cost;
};
 
struct Pair {
    int minCities;
    int maxCities;
    long long price;
    long long routes;
};
 
vector<long long> prices;
vector<long long> routes;
vector<int> minCities;
vector<int> maxCities;
vector<int> visited;
vector<vector<Edge>> graph;
 
Pair dfs(int node, int n) {
    if (visited[node] == 2) return {minCities[node], maxCities[node], prices[node], routes[node]};

    if(visited[node] == 1) {
       return {minCities[node], maxCities[node], INF, routes[node]}; 
    }

    visited[node] = 1;
 
    if (node == n) {
        prices[node] = 0;
        routes[node] = 1;
        minCities[node] = 0;
        maxCities[node] = 0;
        visited[node] = 2;
        return {0, 0, 0, 1};
    }
 
    int minCitiesNode = INT_MAX;
    int maxCitiesNode = INT_MIN;
    long long routesNode = 0;
    long long priceNode = INF;
 
    for (const auto& edge : graph[node]) {
        int neighbor = edge.to;
        long long weight = edge.cost;
        Pair p = dfs(neighbor, n);
 
        if (p.price == INF) continue;
 
        if (priceNode == p.price + weight) {
            routesNode = (routesNode + p.routes) % MOD;
        } else if (priceNode > p.price + weight) {
            routesNode = p.routes;
            minCitiesNode = INT_MAX;
            maxCitiesNode = INT_MIN;
        }

        priceNode = min(priceNode, p.price + weight);
        if (priceNode == p.price + weight) {
            minCitiesNode = min(minCitiesNode, p.minCities + 1);
            maxCitiesNode = max(maxCitiesNode, p.maxCities + 1);
        }
    }
 
    prices[node] = priceNode;
    minCities[node] = minCitiesNode;
    maxCities[node] = maxCitiesNode;
    routes[node] = routesNode;

    visited[node] = 2;
 
    return {minCitiesNode, maxCitiesNode, priceNode, routesNode};
}
 
int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
 
    int n, m;
    cin >> n >> m;
 
    graph.resize(n + 1);
    prices.resize(n + 1, INF);
    routes.resize(n + 1, 0);
    minCities.resize(n + 1, INT_MAX);
    maxCities.resize(n + 1, INT_MIN);
    visited.resize(n + 1, 0);
 
    for (int i = 0; i < m; ++i) {
        int a, b, c;
        cin >> a >> b >> c;
        graph[a].push_back({b, c});
    }
 
    Pair result = dfs(1, n);
 
    if (prices[1] == INF) {
        cout << "IMPOSSIBLE\n";
    } else {
        cout << prices[1] << " " << routes[1] << " " << minCities[1] << " " << maxCities[1] << "\n";
    }
 
    return 0;
}
