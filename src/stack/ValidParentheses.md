[[D-stack&queue]]

典型栈的应用。


需要注意switch语法的运用 [[JavaDetails#swtich]]。


```java
if(stack.isEmpty())
	return false;

char a = stack.pop();
switch (a){
	case '(':
		if(c!=')')
			return false;
		break;
	case '{':
		if(c!='}')
			return false;
		break;
	case '[':
		if(c!=']')
			return false;
		break;
}
```