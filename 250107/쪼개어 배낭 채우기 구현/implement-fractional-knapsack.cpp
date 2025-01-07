#include <iostream>
#include <vector>
#include <algorithm>
#include <iomanip>
using namespace std;

struct Jewel {
    double value;
    double weight;
    double ratio;
};

bool compare(Jewel a, Jewel b) {
    return a.ratio > b.ratio;  // 내림차순 정렬
}

int main() {
    int N;
    double M;

    cin >> N >> M;

    vector<Jewel> jewels(N);
    
    for (int i = 0; i < N; i++) {
        cin >> jewels[i].weight >> jewels[i].value;
        jewels[i].ratio = jewels[i].value / jewels[i].weight;
    }
    sort(jewels.begin(), jewels.end(), compare);
    int i = 0;
    double total_value = 0.0;
    double remaining_weight = M;
    while (i < N && remaining_weight > 0)
    {
        if (jewels[i].weight <= remaining_weight)
        {
            total_value += jewels[i].value;
            remaining_weight -= jewels[i].weight;
        }
        else
        {
            total_value += jewels[i].value * (remaining_weight / jewels[i].weight);
            remaining_weight = 0;
        }
        i++;
    }
    cout << fixed << setprecision(3) << total_value;
    return 0;
}
