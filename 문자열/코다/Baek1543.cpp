#include <cstdio>
#include <string>
#include <iostream>

using namespace std;

int main() {

    string input;
    getline(cin, input);

    string word;
    getline(cin, word);

    int index = 0;
    int ans = 0;

    if (word.length() > input.length()) {
        cout << 0 << endl;
        return 0;
    }

    for (int i = 0; i < input.length() - word.length() + 1; i++) {
        if (input.at(i) == word.at(0)) {
            for (int j = 0; j < word.length(); j++) {
                if (input.at(i + j) != word.at(j)) {
                    break;
                } 

                if (j == word.length() - 1) {
                    ans++;
                    i += word.length() - 1;
                }
            }
        }    
    }

    cout << ans << endl;
    return 0; 
}
