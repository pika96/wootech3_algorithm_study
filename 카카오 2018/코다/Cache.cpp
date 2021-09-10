#include<queue>
#include<utility>
#include<vector>
#include<string>
#include<iostream>
#include<cstdio>

using namespace std;

struct compare {
    bool operator() (pair<string, int> a, pair<string, int> b) {
        return a.second > b.second; //pair 두번째 원소기준 오름차순 정렬 
    }
};

int solution(int cacheSize, vector<string> cities) {
    priority_queue<pair<string, int>, vector<pair<string, int> >, compare> pq1;
    priority_queue<pair<string, int>, vector<pair<string, int> >, compare> pq2;
    int ans = 0;

    if (cacheSize == 0) {
        for (int i = 0; i < cities.size(); i++) {
            ans += 5;
        }
        return ans;
    }

    for (int i = 0; i < cities.size(); i++) {
        for (int j = 0; j < cities[i].size(); j++) {
            cities[i][j] = toupper(cities[i][j]); 
        }    
    }

    pair<string, int> start(cities.at(0), 0);
    pq1.push(start);
    ans += 5;

    for (int i = 1; i < cities.size(); i++) {
        if (i % 2 == 1) {
            int queueSize = pq1.size();
            int inCache = 0;
            for (int j = 0; j < queueSize; j++) {
                pair<string, int> temp = pq1.top();
                cout << temp.first << " "  << cities.at(i) << " " << "i : " << i << endl;                
                if (temp.first.compare(cities.at(i)) == 0) {
                    inCache = 1;
                    ans += 1;
                    temp.second = i;
                    pq2.push(temp);
                    pq1.pop();
                } else {
                    pq2.push(pq1.top());
                    pq1.pop();
                }
            }

            if (inCache == 0) {
                pair<string, int> info(cities.at(i), i);
                if (pq2.size() >= cacheSize) {
                    pq2.pop();
                }
                pq2.push(info);
                ans += 5;
            }
        }

        if (i % 2 == 0) {
            int queueSize = pq2.size();
            int inCache = 0;

            for (int j = 0; j < queueSize; j++) {
                pair<string, int> temp = pq2.top();
                cout << temp.first << " "  << cities.at(i) << " " << "i : " << i << endl;

                if (temp.first.compare(cities.at(i)) == 0) {
                    inCache = 1;
                    ans += 1;
                    temp.second = i;
                    pq1.push(temp);
                    pq2.pop();
                } else {
                    pq1.push(pq2.top());
                    pq2.pop();
                }
            }

            if (inCache == 0) {
                pair<string, int> info(cities.at(i), i);
                if (pq1.size() >= cacheSize) {
                    pq1.pop();
                }
                pq1.push(info);
                ans += 5;
            }
        }
    }

    return ans;
}

int main() {

    vector<string> cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
    int answer = solution(3, cities);

    cout << answer;
    return 0;
}

