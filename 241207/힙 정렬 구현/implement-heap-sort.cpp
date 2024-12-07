#include <iostream>
#include <vector>
using namespace std;

void heapify(vector<int>& ary, int n, int i)
{
    int largest = i;
    int l = i * 2 + 1;
    int r = i * 2 + 2;
    int tmp;

    if (l < n && ary[l] > ary[largest])
        largest = l;
    if (r < n && ary[r] > ary[largest])
        largest = r;
    if (i != largest)
    {
        tmp = ary[i];
        ary[i] = ary[largest];
        ary[largest] = tmp;
        heapify(ary, n, largest);
    }
}

void heap_sort(vector<int>& ary, int n)
{
    int i = n / 2 - 1;
    int tmp;

    while (i >= 0)
        heapify(ary, n, i--);
    i = n - 1;
    while (i > 0)
    {
        tmp = ary[0];
        ary[0] = ary[i];
        ary[i] = tmp;
        heapify(ary, i, 0);
        i--;
    }
}

int main() {
    int i = 0;
    int n = 0;

    cin >> n;
    vector<int> ary(n);
    while (i < n)
        cin >> ary[i++];
    heap_sort(ary, n);
    i = 0;
    while (i < n)
        cout << ary[i++] << " ";
    return 0;
}