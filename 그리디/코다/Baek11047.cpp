#include <cstdio>
#include <iostream>
#include <vector>

using namespace std;

int main() {

    int n = 0;
    int k = 0;
    int ans = 0;

    vector<int> coin;

    cin >> n >> k;
    
    for (int i = 0; i < n; i++) {
        int num;
        cin >> num;
        coin.insert(coin.begin(), num);
    }

    int left = k;
    for (int i = 0; i < n; i++) {
        if ((left - coin[i]) < 0) {
            continue;
        }

        while ((left - coin[i]) >= 0) {
            ans++;
            left -= coin[i];
        }
    }

    cout << ans;
    return 0;
}