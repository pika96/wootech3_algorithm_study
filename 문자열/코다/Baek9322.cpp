#include <cstdio>
#include <vector>
#include <iostream>

using namespace std;

int main() {

    int testcase = 0;
    cin >> testcase;

    for (int i = 0; i < testcase; i++) {
        int wordCount = 0;
        cin >> wordCount;
        vector<string> firstKey;
        vector<string> secondKey;
        vector<string> password;
        vector<int> order;

        for (int j = 0; j < wordCount; j++) {
            string word;
            cin >> word;
            firstKey.push_back(word);
        }

        for (int j = 0; j < wordCount; j++) {
            string word;
            cin >> word;
            secondKey.push_back(word);
        }

        for (int j = 0; j < wordCount; j++) {
            string word;
            cin >> word;
            password.push_back(word);
        }

        for (int j = 0; j < wordCount; j++) {
            for (int k = 0; k < wordCount; k++) {
                if (firstKey[j].compare(secondKey[k]) == 0) {
                    order.push_back(k);
                }
            }
        }

        for (int j = 0; j < wordCount; j++) {
            cout << password[order[j]] << " ";
        }
    }


    return 0;
}