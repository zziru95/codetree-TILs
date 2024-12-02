#include <iostream>
#include <vector>
using namespace std;

int main() {
    int i = 0;
    int j = 0;
    int n = 0;
    int key = 0;

    cin >> n;
    vector<int> ary(n);
    while (i < n)
        cin >> ary[i++];
    i = 1;
    while (i < n)
    {
        key = ary[i];
        j = i - 1;
        while (j >= 0 && ary[j] > key)
        {
            ary[j + 1] = ary[j];
            j--;
        }
        ary[j + 1] = key;
        i++;
    }
    i = 0;
    while (i < n)
        cout << ary[i++] << " ";   
    return 0;
}