#include <cstdio>
#include <vector>
#include <iostream>

using namespace std;

int s = 0;
int ans = 0; 

void check(int current, int start, vector<int> nums) {
    for (int i = start; i < nums.size(); i++) {
        if ((current + nums[i]) == s) {
            ans++;
        }
            
        check(current + nums[i], i + 1, nums);
    }
}

int main() {

    int n;

    cin >> n;
    cin >> s;

    vector<int> nums;
    
    for (int i = 0; i < n; i++) {
        int temp;
        cin >> temp;
        nums.push_back(temp);
    }

    for (int i = 0; i < nums.size(); i++) {
        if (nums[i] == s) {
            ans++;
        }

        check(nums[i], i + 1, nums);
    }


    cout << ans;
    return 0;

}