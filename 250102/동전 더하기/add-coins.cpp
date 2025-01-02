#include <iostream>
#include <vector>
using namespace std;

int main() {
    int i;
    int n, k;
    int tmp = 0, cnt = 0;

    cin >> n >> k;

    vector<int> ary(n);
    i = n - 1;
    while (i >= 0)
        cin >> ary[i--];
    i = 0;
    while (tmp != k)
    {
        tmp += ary[i];
        cnt++;
        if (tmp > k)
        {
            tmp -= ary[i];
            cnt--;
            i++;
        }
    }
    cout << cnt;
    return 0;
}