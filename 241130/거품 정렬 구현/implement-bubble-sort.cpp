#include <iostream>
#include <vector>
using namespace std;

int main() {
    int n = 0;
    int i = 0;
    int j = 0;
    int tmp = 0;

    cin >> n;
    vector<int> ary(n);
    while (i < n)
        cin >> ary[i++];
    i = 0;
    while (i < n - 1)
    {
        j = 0;
        while (j < n - i - 1)
        {
            if (ary[j] > ary[j + 1])
            {
                tmp = ary[j];
                ary[j] = ary[j + 1];
                ary[j + 1] = tmp;
            }
            j++;
        }
        i++;
    }
    i = 0;
    while (i < n)
        cout << ary[i++] << " ";
    return 0;
}