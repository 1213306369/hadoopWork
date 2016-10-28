# 10.26 Hadoop实践练习

## ex1

不断地从标准输入的管道中读入，一边读入，一边把内容输出到标准输出当中

Input:
* abc
* def
* nice

Output(stdout):
* abc
* def
* nice

Output(stderr):

I have recieved n lines. 

其中n为读入的总行数

## ex2

通过标准输入读入access.log的内容，按照规定格式输出

Input: 

* `113.105.12.154 - - [31/Dec/2015:00:00:23 +0800] "GET /show/19871 HTTP/1.1" 301 182 "http://m.sp.sm.cn/s?q=http%3A%2F%2Fyouyanchu.com%2Fshow%2F19871&uc_param_str=dnntnwvepffrgibijbprsv&dn=14904029003-5933e910&nt=2&nw=0&ve=10.3.0.547&pf=44&fr=iphone&gi=bTkwBTOkNreQarpBJRt20qTSX65lSDpkHPE%2BmsMrVum%2BooA%3D&bi=997&jb=2&pr=UCBrowser&sv=app&from=ucframe&yz=1&by=submit&snum=0" "Mozilla/5.0 (iPhone; CPU iPhone OS 8_4_1 like Mac OS X; zh-CN) AppleWebKit/537.51.1 (KHTML, like Gecko) Mobile/12H321 UCBrowser/10.3.0.547 Mobile"`

Output:

* `113.105.12.154 \t /show/19871`

## ex3

通过使用Linux自带的工具，对ex2的输出数据，按照第一列排序输出

## ex4

编写程序，统计每个IP访问过的路径数。采用ex3的输出作为输入

Output:

* `113.105.12.154 \t 1 `

数字1表示这个IP一共访问过一个路径。相同路径的访问，只算一次。

## 使用bash脚本，把ex2,ex3,ex4，串联在一起运行，校验运行结果。
