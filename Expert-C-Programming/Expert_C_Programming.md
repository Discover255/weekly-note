# const
const本身是一个限定符<br>
const char *类型表示一个指向const char的指针，因此这段代码是错误的
```C
char *cp;
const char *ccp;
//ccp初始化后
cp = ccp;
```
cp和ccp是两个类型的变量了
# break
break跳出的最近的那层循环语句或switch语句。如果错误地使用break会引起严重后果。
# 自动合并字符串
ANSI C中相邻字符串自动连接，省掉了过去加\符号的做法