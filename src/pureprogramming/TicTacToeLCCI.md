
# 纯编程

# 可以用加法处理
```java

public String tictactoe(String[] board) {
    char leftLast = 'M';
    char rightLast = 'M';
    boolean isA = true;
    boolean isB = true;
    boolean isEmpty = false;
    c:for (int i = 0;i < board.length;i++){
        String b = board[i];
        if (!b.contains("X") && !b.contains(" ")){
            return "O";
        }
        if (!b.contains("O") && !b.contains(" ")){
            return "X";
        }
        if (b.contains(" ")){
            isEmpty = true;
        }
        char left = b.charAt(i);
        char right = b.charAt(b.length() - 1 - i);
        if (leftLast == 'M')
            leftLast = left;
        if (rightLast == 'M')
            rightLast = right;
        if (isA && leftLast != left){
            isA = false;
        }
        if (isB && rightLast != right){
            isB = false;
        }
        char last = b.charAt(i);
        for (String d : board) {
            char a = d.charAt(i);
            if (a == ' ')
                continue c;
            if (last != a) {
                continue c;
            }
        }
        return String.valueOf(last);
    }
    if (isA && leftLast != ' ')
        return String.valueOf(leftLast);
    if (isB && rightLast != ' ')
        return String.valueOf(rightLast);
    return isEmpty?"Pending":"Draw";
}

```