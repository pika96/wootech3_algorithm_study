#include <cstdio>
#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int main() {

    int n;
    int m;
    cin >> n >> m;

    vector<vector<int> > start;
    int x[4] = {1, -1, 0, 0};
    int y[4] = {0, 0, 1, -1};

    vector<vector<int> > box;
    box.resize(m, vector<int>(n, 0));

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            int temp;
            cin >> temp;

            if (temp == 1) {
                vector<int> startIndex;
                startIndex.push_back(i);
                startIndex.push_back(j);
                start.push_back(startIndex);
            }

            box[i][j] = temp;
        }
    }

        vector<vector<int> > visit;
        visit.resize(m, vector<int>(n, 0));
        queue<vector<int> > qu;

    for (int i = 0; i < start.size(); i++) {
        qu.push(start[i]);
        visit[start[i][0]][start[i][1]] = 1;
    }   

        while (!qu.empty()) {
            vector<int> currentIndex = qu.front();
            qu.pop();
            
            int day = box[currentIndex[0]][currentIndex[1]] + 1;

            for (int i = 0; i < 4; i++) {
                int currentX = currentIndex[0] + x[i];
                int currentY = currentIndex[1] + y[i];

                if (currentX < 0 || currentX >= m || currentY < 0 || currentY >= n) {
                    continue;
                }

                if (box[currentX][currentY] == -1 || visit[currentX][currentY] == 1) {
                    continue;
                }

                visit[currentX][currentY] = 1;
                vector<int> next;
                next.push_back(currentX);
                next.push_back(currentY);
                qu.push(next);

                if (box[currentX][currentY] > day || box[currentX][currentY] == 0) {
                    box[currentX][currentY] = day;
                }
            }
        }

    int max = 0;
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (box[i][j] == 0) {
                cout << "-1";
                return 0;
            }

            if (max < box[i][j]) {
                max = box[i][j];
            }
        }
    }

    if (max == 1) {
        cout << "0";
    } else {
        cout << max - 1;
    }

    return 0;
}