# 存储

图的存储方式 
1.内存里 
2.持久化在库中 
3.专业图数据库 
比如neo4j 存储超大图并涉及大量图计算

# 内存中

邻接矩阵Adjacent Matrix的方式存储图，可以将很多图的运算转化称矩阵之间的运算。比如求解最短路径的Floyd-Warshall算法，就是利用矩阵循环相乘若干次得到。

邻接表Adjacent List

逆邻接表

十字交叉链表

邻接多重表

# 图的算法引申

广度、深度优先：图遍历 无权图搜索 
单源最短路径：Dijkstra 
全源最短路径：Bellman-Ford、Floyd-Warshall

最小生成树算法：bingchaji算法 Prim、Kruskal 
拓扑排序网络流：货运二分图匹配

## BFS DFS 遍历/搜索算法

广度优先搜索Breath-First-Search 
地毯式推进，先最近，然后次近，依次向外搜索 
广度优先搜索可以找到最短路径。

深度优先搜索Depth-First-Search 
最直观的例子是走迷宫