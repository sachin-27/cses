#include <iostream>
#include <vector>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, q;
    cin >> n >> q;

    vector<int> child(n + 1);
    for (int i = 1; i <= n; ++i) {
        cin >> child[i];
    }

    vector<vector<int>> grid(n + 1, vector<int>(30));
    for (int i = 1; i <= n; ++i) {
        grid[i][0] = child[i];
    }

    for (int power = 1; power < 30; ++power) {
        for (int i = 1; i <= n; ++i) {
            grid[i][power] = grid[grid[i][power - 1]][power - 1];
        }
    }

    while (q-- > 0) {
        int x, k;
        cin >> x >> k;

        int ans = x;
        for (int bit = 29; bit >= 0; --bit) {
            int val = 1 << bit;
            if ((val & k) != 0) {
                ans = grid[ans][bit];
            }
        }

        cout << ans << "\n";
    }

    return 0;
}
