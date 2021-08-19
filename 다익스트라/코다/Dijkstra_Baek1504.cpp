#include <cstdio>
#include <vector>
#include <iostream>
#include <queue>
#include <utility>
#include <cstdlib>

using namespace std;

struct compare {
    bool operator() (pair<int, int> a, pair<int, int>  b) {
        return a.second > b.second;
    }
};

int n;
priority_queue<pair<int, int>, vector<pair<int, int> >, compare> pq;
vector<vector<int> > graph;

vector<int> dijkstra(int startVertex) {
    int startDistance = 0;
    vector<int> distance;
    distance.resize(n + 1, -1);

    pair<int, int> start(startVertex, startDistance);
    pq.push(start);

    distance[startVertex] = startDistance;
    
    while(!pq.empty()) {
        pair<int, int> current = pq.top();
        pq.pop();

        for (int i = 1; i <= n; i++) {
            if (graph[current.first][i] != -1) {

                int candidate = distance[current.first] + graph[current.first][i];

                if (distance[i] == -1 || distance[i] > candidate) {
                    distance[i] = candidate;
                    pair<int, int> next(i, distance[i]);
                    pq.push(next);
                }
            }
        }
    }

    return distance;
}

int main() {

    // 1 -> v1
    // v1 -> v2
    // v2 -> v1
    // v2 -> n
    // v1 -> n
    int e; 

    cin >> n >> e;

    graph.resize(n + 1, vector<int>(n + 1, -1));

    for (int i = 0 ; i < e; i++) {
        int a;
        int b;
        int d;

        cin >> a >> b >> d;
        graph[a][b] = d;
        graph[b][a] = d;
    }

    int v1; 
    int v2;
    cin >> v1 >> v2;

    int firstPath = 0;
    int secondPath = 0;

    //Dijkstra 
    vector<int> distanceFromOne = dijkstra(1);
    vector<int> distanceFromV1 = dijkstra(v1);
    vector<int> distanceFromV2 = dijkstra(v2);    

    int ans = -1;
    if (distanceFromOne[v1] == -1 || distanceFromV1[v2] == -1 || distanceFromV2[n] == -1) {
        firstPath = -1;
    } else {
        firstPath = distanceFromOne[v1] + distanceFromV1[v2] + distanceFromV2[n];
    }

    if (distanceFromOne[v2] == -1 || distanceFromV2[v1] == -1 || distanceFromV1[n] == -1) {
        secondPath = -1;
    } else {
        secondPath = distanceFromOne[v2] + distanceFromV2[v1] + distanceFromV1[n];
    }
    
    if (firstPath == -1) {
        ans = secondPath;
    } else if (secondPath == -1) {
        ans = firstPath;
    } else {
        ans = min(firstPath, secondPath);
    }

    cout << ans;
    return 0;
}