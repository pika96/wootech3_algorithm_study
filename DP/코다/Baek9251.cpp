#include <cstdio>
#include <iostream>
#include <vector>
#include <string>

using namespace std;

int main() {

    string s;
    string t;

    cin >> s;
    cin >> t;

    vector<vector<int> > dp;
    dp.resize(s.length() + 1, vector<int>(t.length() + 1, 0));

    for (int i = 1; i <= s.size(); i++) {
        for (int j = 1; j <= t.size(); j++) {
            if (s[i-1] == t[j-1]) {
                dp[i][j] = dp[i-1][j-1] + 1;
            } else {
                if (dp[i][j-1] > dp[i-1][j]) {
                    dp[i][j] = dp[i][j-1];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
    }

    cout << dp[s.length()][t.length()];
    
    return 0;
}