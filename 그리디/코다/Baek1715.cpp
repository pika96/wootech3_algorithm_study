#include <cstdio>
#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int main() {
    
    int n = 0;
    cin >> n; 

    priority_queue<int, vector<int>, greater<int> > pq;

    for (int i = 0; i < n; i++) {
        int num;
        cin >> num;
        pq.push(num);
    }

    int ans = 0;

    for (int i = 0; i < n - 1; i++) {
        int num1 = pq.top();
        pq.pop();
        int num2 = pq.top();
        pq.pop();
        
        int newNum = num1 + num2;
        ans += newNum;
        
        pq.push(newNum);
    }

    cout << ans;
    return 0;
}