#include <cstdio>
#include <iostream>
#include <vector>

using namespace std;

int main() {

    int n;
    vector<int> p;
    vector<int> dp; 
    
    cin >> n;
    
    p.resize(n + 1, 0);
    dp.resize(n + 1, 0);

    for (int i = 1; i <= n; i++) {
        cin >> p[i];
    }

    for (int i = 1; i <= n; i++) {
        dp[i] = p[i];
        for (int j = 1; j <= i - 1; j++) {
            int candidate = dp[i - j] + p[j];
            if (candidate > dp[i]) {
                dp[i] = candidate;
            }
        }
    }

    cout << dp[n];
    return 0;
}
