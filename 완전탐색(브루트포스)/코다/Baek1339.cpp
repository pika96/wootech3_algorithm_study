#include <cstdio>
#include <vector>
#include <iostream>
#include <cmath>

using namespace std;

int main() {
    int n;
    cin >> n;

    vector<string> words;
    vector<int> weight;
    weight.resize(26, 0);

    for (int i = 0; i < n; i++) {
        string temp;
        cin >> temp;
        words.push_back(temp);
    }   

    for (int i = 0; i < n; i++) {
        string word = words[i];
        for (int j = 0; j < word.length(); j++) {
            int currentPos = word[j] - 'A';
            weight[currentPos] += pow(10, words[i].length() - j - 1);
        }
    }

    int num = 9;
    int max = -1;
    int maxIndex = -1;
    int ans = 0;

    while (max != 0) {
        max = 0;
        maxIndex = -1;
        for (int i = 0; i < 26; i++) {
            if (weight[i] > max) {
                max = weight[i];
                maxIndex = i;
            }
        }
        ans += max * num;
        num--;
        weight[maxIndex] = 0;
    }

    cout << ans;
    return 0;
}