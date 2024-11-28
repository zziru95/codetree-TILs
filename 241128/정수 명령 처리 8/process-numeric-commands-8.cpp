#include <iostream>
#include <cstring>
#include <list>
using namespace std;

int main() {
    char    cmd[100];
    int     cnt = 0;
    int     value = 0;
    list<int>   lst;

    cin >> cnt;
    while (cnt > 0)
    {
        cin >> cmd;
        if (strcmp(cmd, "push_front") == 0)
        {
            cin >> value;
            lst.push_front(value);
        }
        else if (strcmp(cmd, "push_back") == 0)
        {
            cin >> value;
            lst.push_back(value);
        }
        else if (strcmp(cmd,"pop_front") == 0)
        {
            if (!lst.empty())
            {
            cout << lst.front() << endl;
            lst.pop_front();
            }
        }
        else if (strcmp(cmd, "pop_back") == 0)
        {
            if (!lst.empty())
            {
            cout << lst.back() << endl;
            lst.pop_back();
            }
        }
        else if (strcmp(cmd, "front") == 0)
        {
            if (!lst.empty())    
                cout << lst.front() << endl;
        }
        else if (strcmp(cmd, "back") == 0)
        {
            if (!lst.empty())
                cout << lst.back() << endl;
        }
        else if (strcmp(cmd, "size") == 0)
            cout << lst.size() << endl;
        else if (strcmp(cmd, "empty") == 0)
            cout << lst.empty() << endl;
        cnt--;
    }


    return 0;
}