#include <iostream>
#include <vector>
#include <cstring>
#include <cmath>

using namespace std;

const int MOD = 1000000007;
int dp[20][1 << 20];
vector<vector<int>> graph;

int dfs(int mask, int index) {
    if (index == 0 && mask == 1) {
        return 1;
    }

    if (index == 0) {
        return 0;
    }

    if ((mask & (1 << index)) == 0) {
        return 0;
    }

    if (dp[index][mask] != -1) {
        return dp[index][mask];
    }

    int mask1 = mask ^ (1 << index);
    int total = 0;
    for (int neighbor : graph[index]) {
        total = (total + dfs(mask1, neighbor)) % MOD;
    }

    dp[index][mask] = total;
    //cout<<"dp["<<index<<"]["<<mask<<"]="<<total<<endl;
    return total;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, m;
    cin >> n >> m;

    graph.resize(n + 1);
    memset(dp, -1, sizeof(dp));

    for (int i = 0; i < m; i++) {
        int a, b;
        cin >> a >> b;
        a--;
        b--;
        graph[b].push_back(a);
    }

    int target = (1<<n) - 1;
    long count = dfs(target, n - 1);
    cout << count << endl;

    return 0;
}
