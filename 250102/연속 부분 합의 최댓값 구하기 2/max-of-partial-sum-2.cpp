#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
    int n;
    cin >> n;
    
    vector<int> ary(n);
    for (int i = 0; i < n; i++) {
        cin >> ary[i];
    }

    int current_sum = 0; 
    int max_sum = ary[0];

    for (int i = 0; i < n; i++) {
        current_sum = max(ary[i], current_sum + ary[i]);
        max_sum = max(max_sum, current_sum);
    }

    cout << max_sum;
    return 0;
}
