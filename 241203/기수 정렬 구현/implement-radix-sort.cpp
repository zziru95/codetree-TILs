#include <iostream>
#include <cmath>
#include <vector>
using namespace std;

int main() {
    int i = 0;
    int j = 0;
    int pow_cnt = 0;
    int n = 0;
    int max = 0;
    int k = 0;
    int digit = 0;
    int tmp = 0;
    
    cin >> n;
    vector<int> ary(n);

    while (i < n)
        cin >> ary[i++];
    max = ary[0];
    i = 1;
    while (i < ary.size())
    {
        if (ary[i] > max)
            max = ary[i];
        i++;
    }
    while (max > 0)
    {
        max /= 10;
        k++;
    }
    while (pow_cnt < k)
    {
        vector<vector<int>> tmp_ary(10);
        i = 0;
        while (i < ary.size())
        {   
            tmp = ary[i] / (int)(pow(10, pow_cnt));
            digit = tmp % 10;
            tmp_ary[digit].push_back(ary[i]);
            i++;
        }
        vector<int> store_ary;
        i = 0;
        while (i < 10)
        {
            j = 0;
            while (j < tmp_ary[i].size())
            {
                store_ary.push_back(tmp_ary[i][j]);
                j++;
            }
            i++;
        }
        ary = store_ary;
        pow_cnt++;
    }
    i = 0;
    while (i < n)
        cout << ary[i++] << " ";
    return 0;
}