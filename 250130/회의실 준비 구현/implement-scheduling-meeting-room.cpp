#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

int main() {
    int n;

    cin >> n;
    vector<int> s(n);
    vector<int> e(n);
    for (int i = 0; i < n; i++)
        cin >> s[i] >> e[i];
    vector<pair<int, int>> meeting;
    for (size_t i = 0; i < s.size(); i++)
        meeting.push_back({s[i], e[i]});
    sort(meeting.begin(), meeting.end(), [](const pair<int, int>& a, const pair<int, int>& b) {
        return a.second < b.second;
    });

    int count = 1;
    int last_end_time = meeting[0].second;
    for (int i = 1; i < n; i++)
    {
        if (meeting[i].first >= last_end_time)
        {
            count++;
            last_end_time = meeting[i].second;
        }
    }
    cout << count;
    return 0;
}
