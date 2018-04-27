#include <stdio.h>
int main() {
    int i = 1, j = 0, len = 3;
    char ch[] = "abb";
    int next[4];
    while(i<len) {
        if(j==0||ch[i]==ch[j]) {
            i++;j++;
            next[j]=j;
        } else {
            j = next[j];
        }
    }
    printf("%d%d%d", next[1], next[2], next[3]);
}