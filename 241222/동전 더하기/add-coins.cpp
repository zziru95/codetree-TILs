#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main()
{
    int n, k;
    int i = 0;
    int cnt = 0;

    cin >> n >> k;
    vector<int> ary(n);
    while (i < n)
        cin >> ary[i++];
    sort(ary.rbegin(), ary.rend());
    i = 0;
    while (k > 0 && i < n)
    {
        if (ary[i] <= k)
        {
            cnt += k / ary[i];  
            k %= ary[i];
        }
        i++;
    }
    cout << cnt;
    return 0;
}