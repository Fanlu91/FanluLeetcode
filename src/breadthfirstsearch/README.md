# 深度优先遍历

## 什么情况下用bfs

一个图中，起点到终点，寻找**最短路径**。

## 通用框架

bfs 是有明显的框架可循的。


``` java
int BFS(Node start, Node target) {
    Queue<String> queue= new LinkedList<>(); // 使用队列记录待走的路径
    Set<String> visited = new HashSet<>(); // 使用集合记录已走的路径

    queue.offer(start); // 将起点加入队列
    visited.add(start);
    int step = 0; // 记录扩散的步数

    while (!queue.isEmpty()) {
        int size = queue.size(); // 确定了当前层有几个节点，后续继续加入并不影响。
        /* 将当前队列中的所有节点向四周扩散 */
        for (int i = 0; i < size; i++) {
            Node cur = queue.poll();
            /* 划重点：这里判断是否到达终点 */
            if (cur is target)
                return step;
            /* 将 cur 的相邻节点加入队列 */
            for (Node x : cur.adj()) // cur.adj() 泛指 cur 相邻的节点
                if (x not in visited) {
                    queue.offer(x);
                    visited.add(x);
                }
        }
        // 当前层结束，增加一步开始计算下一层
        step++;
    }
}
```

可以使用双向BFS，从起点和终点同时扩散来提高效率。