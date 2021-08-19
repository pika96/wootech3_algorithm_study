#include <iostream>
#include <vector>
#include <queue>
#include <cstdio>
#include <algorithm>

using namespace std;

int main() {

    int n = 0;
    int m = 0;

    cin >> n >> m;

    vector<vector<int> > map;
    map.resize(n + 1, vector<int>(m + 1, 0));

    vector<vector<vector<int> > > path;
    path.resize(n + 1, vector<vector<int> >(m + 1, vector<int>(2, -1)));

    int x[4] = {1, -1, 0, 0};
    int y[4] = {0, 0, 1, -1}; 

    for (int i = 1; i <= n; i++) {
        string str;
        cin >> str;
        for (int j = 1; j <= m; j++) {
            map[i][j] = str.front() - '0';
            str.erase(str.begin());
        }
    }

    //풀이
    queue<vector<int> > qu;
    vector<int> start(3, 0);
    start[0] = 1;
    start[1] = 1;
    start[2] = 0;

    path[1][1][0] = 1;
    path[1][1][1] = 1;

    qu.push(start);
    
    int ans = -1;
    while(!qu.empty()) {
        vector<int> current;
        current = qu.front();
        qu.pop();

        if (current[0] == n && current[1] == m) {
            ans = path[n][m][current[2]];
            break;
        }
        
        for (int i = 0; i < 4; i++) {
            int nextX = current[0] + x[i];
            int nextY = current[1] + y[i];

            if (nextX > n || nextX < 1 || nextY > m || nextY < 1) {
                continue;
            }

            if (map[nextX][nextY] == 0 && path[nextX][nextY][current[2]] == -1) {    
                path[nextX][nextY][current[2]] = path[current[0]][current[1]][current[2]] + 1;
                
                vector<int> nextIndex(3, 0);
                nextIndex[0] = nextX;
                nextIndex[1] = nextY;
                nextIndex[2] = current[2];
                    
                qu.push(nextIndex);
            
            } else if (map[nextX][nextY] == 1 && current[2] == 0) {
                path[nextX][nextY][1] = path[current[0]][current[1]][current[2]] + 1;
                
                vector<int> nextIndex(3, 0);
                nextIndex[0] = nextX;
                nextIndex[1] = nextY;
                nextIndex[2] = 1;
                    
                qu.push(nextIndex);
            }
        }
    }

    cout << ans;
    return 0; 
}