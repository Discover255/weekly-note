|C版本|GCC命令行选项|
|-----|-----------:|
|GNU 89|无，-std=gnu89|
|ANSI,ISO C90|-ansi,-std=c89|
|ISO C99|--std=c99|
|GNU 99|-std=gnu99|

|C声明|32位机器|64位机器|
|----|:------:|------:|
|char|1|1|
|short int|2|2|
|int|4|4|
|long int|4|8|
|long long int|8|8|
|char *|4|8|
|float|4|4|
|double|8|8|
# 第三章 程序的机器级表示
仍在使用的技术
* 平坦寻址 flat-addressing
* X87浮点指令模型，但是更推荐使用SSE指令集
