#include <iostream>
#include <vector>
using namespace std;

void merge(vector<int>& ary, int low, int mid, int high)
{
    int i, j, k;
    vector<int> sorted_ary;
    i = low;
    j = mid + 1;
    k = 0;
    while (i <= mid && j <= high)
    {
        if (ary[i] < ary[j])
            sorted_ary.push_back(ary[i++]);
        else
            sorted_ary.push_back(ary[j++]);
    }
    while (i <= mid)
        sorted_ary.push_back(ary[i++]);
    while (j <= high)
        sorted_ary.push_back(ary[j++]);
    i = low;
    while (i <= high)
    {
        ary[i++] = sorted_ary[k++];
    }
}
void merge_sort(vector<int>& ary, int low, int high)
{
    int mid; 
    if (low >= high)
        return ;
    mid = (low + high) / 2;
    merge_sort(ary, low, mid);
    merge_sort(ary, mid + 1, high);
    merge(ary, low, mid, high);
}

int main() {
    int i = 0;
    int n = 0;

    cin >> n;
    vector<int> ary(n);
    while (i < n)
        cin >> ary[i++];
    if (ary.size() > 1)
        merge_sort(ary, 0, n - 1);
    i = 0;
    while (i < ary.size())
        cout << ary[i++] << " ";
    return 0;
}