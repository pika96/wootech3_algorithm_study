#include <cstdio>
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> visited;
vector<vector<int> > graph;
vector<int> order;

void dfs(int index) {
    
    if (visited[index] == 1) {
        return;
    }
    
    order.push_back(index);
    visited[index] = 1;

    for(int i = 0; i < graph[index].size(); i++) {
        if (visited[graph[index][i]] == 0) {
            dfs(graph[index][i]);
        }
    }
}

void bfs(int index) {

    vector<int> candidates;
    candidates.push_back(index);
    visited[index] = 1;
    order.push_back(index);


    while(!candidates.empty()) {
        int currentIndex = candidates.front();
        candidates.erase(candidates.begin());

        for (int i = 0; i < graph[currentIndex].size(); i++) {
            if (visited[graph[currentIndex][i]] == 0) {
                visited[graph[currentIndex][i]] = 1;
                candidates.push_back(graph[currentIndex][i]);
                order.push_back(graph[currentIndex][i]);
            }
        }
    }
}

int main() {

    int n;
    int m;
    int v;

    cin >> n >> m >> v;

    graph.resize(n + 1);
    visited.resize(n + 1, 0);

    for (int i = 0; i < m; i++) {
        int a;
        int b; 
        cin >> a >> b;

        graph[a].push_back(b);
        graph[b].push_back(a);
    }

    for (int i = 0; i <= n; i ++) {
        sort(graph[i].begin(), graph[i].end());
    }

    dfs(v);
    
    for (int i = 0; i < order.size(); i++) {
        cout << order[i] << " ";
    }

    cout << endl;

    order.clear();
    visited.clear();
    visited.resize(n + 1, 0);

    bfs(v);

    for (int i = 0; i < order.size(); i++) {
        cout << order[i] << " ";
    }

    return 0; 
}