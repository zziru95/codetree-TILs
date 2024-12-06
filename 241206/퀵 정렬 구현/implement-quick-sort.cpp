#include <iostream>
#include <vector>
using namespace std;

int    select_pivot(vector<int>& ary, int low, int high)
{
    int mid = (low + high) / 2;
    int median = 0;
    
    if ((ary[low] < ary[mid] && ary[mid] < ary[high]) || (ary[high] < ary[mid] && ary[mid] < ary[low]))
        median = mid;
    else if ((ary[mid] < ary[low] && ary[low] < ary[high]) || (ary[high] < ary[low] && ary[low] < ary[mid]))
        median = low;
    else 
        median = high;
    return median;
}

int    partition(vector<int>& ary, int low, int high)
{
    int i, j, tmp;
    int pivot;
    
    if (high - low < 3)
        pivot = high;
    else
    {
        pivot = select_pivot(ary, low, high);
        if (pivot != high)
        {
            tmp = ary[high];
            ary[high] = ary[pivot];
            ary[pivot] = tmp;
            pivot = high;
        }
    }

    i = low - 1;
    j = low;

    while (j < high)
    {
        if (ary[j] < ary[pivot])
        {
            i++;
            tmp = ary[i];
            ary[i] = ary[j];
            ary[j] = tmp;
        }
        j++;
    }
    tmp = ary[i + 1];
    ary[i + 1] = ary[high];
    ary[high] = tmp;

    return i + 1;
}
void    quick_sort(vector<int>& ary, int low, int high)
{
    if (low < high)
    {
        int pos = partition(ary, low, high);

        quick_sort(ary, low, pos - 1);
        quick_sort(ary, pos + 1, high);
    }
}

int main() {
    int i = 0;
    int n = 0;

    cin >> n;
    vector<int> ary(n);
    while (i < n)
        cin >> ary[i++];
    quick_sort(ary, 0, n - 1);
    i = 0;
    while (i < ary.size())
        cout << ary[i++] << " ";
    return 0;
}