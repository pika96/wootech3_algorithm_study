#include <iostream>
#include <vector>
#include <cstdio>
#include <cstdlib>
#include <queue>
#include <utility>

using namespace std;

struct compare {
    bool operator() (pair<int, int> a, pair<int, int> b) {
        return a.second > b.second;
    }
};

int main() {

    int n;
    int m; 
    int x;

    vector<vector<int> > graph;
    vector<int> distance;
    vector<int> reverseDistance;
    priority_queue<pair<int, int>, vector<pair<int, int> >, compare> next;

    cin >> n >> m >> x;

    graph.resize(n + 1, vector<int>(n + 1, -1));
    distance.resize(n + 1, -1);

    for (int i = 0; i < m; i++) {
        int a;
        int b;
        int w;

        cin >> a >> b >> w;

        graph[a][b] = w;
    }

    distance[x] = 0;
    pair<int, int> start (x, 0);
    next.push(start);

    while(!next.empty()) {
        
        pair<int, int> current = next.top();
        next.pop();
        
        for (int i = 1; i <= n; i++) {
            if (graph[current.first][i] != -1) {

                int candidateDistance = current.second + graph[current.first][i];
                
                if (distance[i] == -1) {

                    distance[i] = candidateDistance;
                    pair<int, int> nextVertex (i, distance[i]);
                    next.push(nextVertex);

                } else if (distance[i] > candidateDistance) {
                        distance[i] = candidateDistance;

                        pair<int, int> nextVertex (i, distance[i]);
                        next.push(nextVertex);

                }
            }
        }
    }

    for (int i = 1; i <= n; i++) {
        if (i == x) {
            continue;
        }

        reverseDistance.clear();
        reverseDistance.resize(n + 1, -1);

        reverseDistance[i] = 0;
        pair<int, int> start (i, 0);
        next.push(start);

        while(!next.empty()) {
        
            pair<int, int> current = next.top();
            next.pop();

            for (int j = 1; j <= n; j++) {
                if (graph[current.first][j] != -1) {

                    int candidateDistance = current.second + graph[current.first][j];
                    
                    if (reverseDistance[j] == -1) {
                        reverseDistance[j] = candidateDistance;
                        pair<int, int> nextVertex (j, reverseDistance[j]);
                        next.push(nextVertex);
                    } else if (reverseDistance[j] > candidateDistance) {
                        reverseDistance[j] = candidateDistance;
                        pair<int, int> nextVertex (j, reverseDistance[j]);
                        next.push(nextVertex);
                    }
                }
            }
        }

    int max = 0;
    for (int i = 1; i <= n; i++) {
        if (distance[i] > max) {
            max = distance[i];
        }
    }

    cout << max;
    return 0;
}
