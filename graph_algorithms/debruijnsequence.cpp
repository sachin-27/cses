#include <iostream>
#include <unordered_set>
#include <string>
#include <vector>

using namespace std;

void generateStrings(string& s, unordered_set<string>& set, int n) {
    if (s.size() == n) {
        set.insert(s);
        return;
    }

    for (int i = 0; i < 2; i++) {
        s.push_back('0' + i);
        generateStrings(s, set, n);
        s.pop_back();
    }
}

bool dfs(unordered_set<string>& set, string& res, int n) {
    if (set.empty()) {
        return true;
    }

    string cur = res.substr(res.size() - n + 1, n - 1);

    for (int i = 0; i < 2; i++) {
        string next = cur + char('0' + i);
        if (set.find(next) != set.end()) {
            set.erase(next);
            res.push_back('0' + i);
            if (dfs(set, res, n)) {
                return true;
            }
            set.insert(next);
            res.pop_back();
        }
    }
    return false;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    cin >> n;

    string s;
    unordered_set<string> set;
    generateStrings(s, set, n);

    string start = *set.begin();

    string res = start;
    set.erase(start);
    dfs(set, res, n);

    cout << res << endl;

    return 0;
}
