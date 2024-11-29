#include <iostream>
#include <string>
#include <list>
using namespace std;

int main() {
    int n = 0;
    int m = 0;
    long unsigned int idx = 0;
    string str;
    string cmd;
    string ch;
    list<char> toast;
    list<char>::iterator it;

    cin >> n;
    cin >> m;
    cin.ignore();
    getline(cin, str);
    while (idx < str.size())
        toast.push_back(str[idx++]);
    it = toast.end();
    while (m > 0)
    {
        cin >> cmd;
        if (cmd == "L")
        {
            if (it != toast.begin())
                it--;
        }
        else if (cmd == "R")
        {
            if (it != toast.end())
                it++;
        }
        else if (cmd == "D")
        {
            if (!(toast.empty()))
            {
                it = toast.erase(it);
            }
        }
        else if (cmd == "P")
        {
            cin >> ch;
            toast.insert(it, ch[0]);
        }
        m--;
    }
    it = toast.begin();
    while (it != toast.end())
    {
        cout << *it;
        it++;
    }
    cout << endl;
    return 0;
}