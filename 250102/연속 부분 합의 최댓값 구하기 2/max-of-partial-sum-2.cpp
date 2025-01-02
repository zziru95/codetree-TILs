#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int n;
int sum = 0;
vector<int> res;
int ary[100000];

int main() {
    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> ary[i];
    }

    int i = 0;
    while (i < n) {
        sum += ary[i];
        
        if (sum < 0) {
            res.push_back(sum);
            sum = 0; 
        }
        
        i++;
    }

    if (sum > 0) {
        res.push_back(sum);
    }

    auto max_iter = max_element(res.begin(), res.end());
    cout << *max_iter;
    return 0;
}
