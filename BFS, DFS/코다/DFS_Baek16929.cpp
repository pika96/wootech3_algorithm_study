#include <iostream>
#include <cstdio>
#include <vector>

using namespace std;

vector<vector<int> > visit;
vector<vector<int> > countSize;
vector<vector<int> > map;
int n;
int m;
int x[4] = {1, -1, 0, 0};
int y[4] = {0, 0, 1, -1};
int startX;
int startY;
int done;

void dfs(int indexX, int indexY, int count) {
    visit[indexX][indexY] = 1;
    countSize[indexX][indexY] = count;
    
    for (int i = 0; i < 4; i++) {
        int nextX = indexX + x[i];
        int nextY = indexY + y[i];

        if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) {
            continue;
        }

        if (nextX == startX && nextY == startY && count + 1 >= 4) {
            cout << "Yes";
            done = 1;
            return;
        }

        if (visit[nextX][nextY] == 0 && map[indexX][indexY] == map[nextX][nextY]) {
            dfs(nextX, nextY, count + 1);
            if (done == 1) {
                return;
            }
        }
    }
}

int main() {

    cin >> n >> m;

    map.resize(n, vector<int>(m, 0));
    visit.resize(n, vector<int>(m, 0));
    countSize.resize(n, vector<int>(m, 0));

    for (int i = 0; i < n; i++) {
        string str;
        cin >> str;
        for (int j = 0; j < m; j++) {
            map[i][j] = str.front();
            str.erase(str.begin());
        }
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            startX = i;
            startY = j;
            dfs(i, j, 1);

            visit.clear();
            visit.resize(n, vector<int>(m, 0));

            countSize.clear();
            countSize.resize(n, vector<int>(m, 0));

            if (done == 1) {
                return 0;
            }
        }
    }

    cout << "No";
    return 0;
}