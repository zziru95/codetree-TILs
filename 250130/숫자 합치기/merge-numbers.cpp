#include <iostream>
#include <queue>

using namespace std;

int main() {
    int n;

    cin >> n;
    priority_queue<int, vector<int>, greater<int>> pq;

    int num;
    for (int i = 0; i < n; i++) {
        cin >> num;
        pq.push(num);
    }

    int cost;
    int total_cost = 0;
    while (pq.size() > 1) {
        cost = pq.top(); 
        pq.pop();
        cost += pq.top(); 
        pq.pop();
        total_cost += cost;
        pq.push(cost);
    }
    cout << total_cost;
    return 0;
}
