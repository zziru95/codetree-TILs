#include <iostream>
#include <vector>
using namespace std;

int main() {
    int i = 0;
    int j;
    int n = 0;
    int tmp = 0;
    int min = 0;

    cin >> n;
    vector<int> ary(n);
    while (i < n)
        cin >> ary[i++];
    i = 0;
    while (i < n)
    {
        min = i;
        j = i + 1;
        while (j < n)
        {
            if (ary[min] > ary[j])
                min = j;
            j++;
        }
        tmp = ary[i];
        ary[i] = ary[min];
        ary[min] = tmp;
        i++;
    }
    i = 0;
    while (i < n)
        cout << ary[i++] << " ";
    return 0;
}