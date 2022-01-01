# 先行后列
`matrix[row][column]`

`matrix[2][3]` 第3行第4列

# column & row 边界条件

- `column == matrix[0].length || column == -1`
- `row == -1 || row == matrix.length`


## java技巧

## 记录访问过的位置

```java
boolean[][] visited = new boolean[matrix.length][matrix[0].length];
// 也可以用int[][] 记录/判断多种状态
```



## 向四个方向遍历

```java
// 0 r , 1 d , 2 l , 3 u
direction = (direction + 1) % 4;

if (direction == 0) {
  column++; //右
} else if (direction == 1) {
  row++; //下
} else if (direction == 2) {
  column--; // 左
} else {
  row--; // 上
}
```

