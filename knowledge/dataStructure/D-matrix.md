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


# 正矩阵翻转
对于`matrix[i][j], i<n,j<m`本质上来说有4种翻转：
## 水平方向上下翻转 
`(i,j) - (n-i-1,j)`
## 竖直方向左右翻转
`(i,j) - (i,m-j-1)`
## 对角线翻转（左上右下对角线、左下右上对角线）
`(i,j) - (j,i)`

```java
// 水平上下翻转
for (int i = 0; i < n / 2; i++) {
	for (int j = 0; j < n; j++) {
		swap(matrix[i][j], matrix[n - 1 - i][j]);
	}
}
// 竖直左右翻转
for (int j = 0; j < n / 2; j++) {
	for (int i = 0; i < n; i++) {
		swap(matrix[i][j], matrix[i][n - 1 - j]);
	}
}

// 左上到右下为轴，遍历左下区域，与右上区域swap
for (int i = 0; i < n; i++) {
	for (int j = 0; j < i; j++) {
		swap(matrix[i][j], matrix[j][i]);
	}
}

// 左下到右上为轴，遍历左上区域，与右下区域swap
for (int i = 0; i < n; i++) {
	for (int j = 0; j < n - i; j++) {
		swap(matrix[i][j], matrix[j][i]) ;
	}
}
```

# 顺时针旋转
使用翻转实现旋转
## 90
上下翻转 + 左上到右下为轴对角线翻转
## 180
上下翻转 + 左右翻转
## 270
左右翻转 + 左上到右下为轴对角线翻转