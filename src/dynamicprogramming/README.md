# 动态规划
# 为什么要用动态规划

动态规划适合用来求解最优解问题，比如最大值、最小值等。
它可以显著降低时间复杂度，提高代码的执行效率。

其难点在于有些类似递归，不太符合人类常规的思维方式。

# 什么样的问题可以用动态规划解决

动态规划类问题通常符合一个模型三个特征

## 多阶段决策最优解模型

一般用动态规划解决最优解问题，解决过程需要经历多个决策阶段，每个阶段都对应着一组状态。
每次决策依赖于当前状态，又随即引起状态的转移。一个决策序列就是在变化的状态中产生，通过这组序列产出最终求解期望的最优值。

## 三个特征

### 最优字结构

指问题的最优解包含子问题的最优解。后面阶段的状态可以由前面阶段的状态推导出来。 可以通过子问题的最优解，推导出问题的最优解。

### 无后效性

1. 推导后面阶段的状态时，只关心**前面阶段的状态值**而不关心其是如何推导出的
2. 某阶段状态一旦确定，不会受到后面阶段状态的影响，即某状态以后的过程不会影响之前的状态。

### 重复子问题

不同的决策序列，达到某个相同的阶段时，可能会产生重复的状态，一个子问题在下一阶段决策中可能被多次使用到。该性质并不是动态规划适用的必要条件，但是如果没有这条性质，动态规划算法同其他算法相比就不具备明显优势。

# 解决动态规划问题的思路

## 首先分析模型和特征

- 是否分阶段、**阶段是什么**？ 这点通常不难
- 与问题的最优解有关的**子问题是什么**？
- 透过子问题、不同阶段、最优解 -- 我们应当关注（计算和记录）的 **状态** 是什么？这点非常非常重要。无后效性关注的仅是状态，状态不变但是状态的值，而是我们已经关注并且记录了下来，随着阶段的变化不断跟踪更新着，最终用于产出最优解。

围绕状态这一主题具体的动态规划方法有两种，分别是画**状态转移表**或者推导**状态转移方程**，然后将其转化为代码。

## 状态转移表

一般可以用动态规划解决的问题，都能使用回溯法暴力解决。虽然并不高效，但其思考难度是相对简单的。
有了回溯算法，可以以此定义状态并用每个状态表示一个节点，对应画出**递归树**。通过递归树较容易看出是否有重复子问题，以及子问题是如何产生的，从中寻找规律看是否可以使用动态规划解决。
若找到重复子问题，我们可以直接用回溯加“备忘录”的方法处理。从执行效率上来看与动态规划已经没有太大差别，但并不是所有的重复子问题都能用备忘录来优化；
此时也可以用状态转移表法，使用动态规划来解决问题。

状态表一般是二维的，可以想像成二维数组，每个状态包含三个变量- 行、列、数组值。它有点像一个辅助人脑进行计算和梳理状态的表格，所以除了应用动态规划套路，遇到问题应冷静考虑人脑会如何处理。
我们根据决策过程，从前往后根据递推关系，分阶段填充状态表中的每个状态，最后将这个**递推填表的过程翻译成代码**，就是动态规划代码了。

```
回溯算法实现 - 定义状态 - 画递归树 - 找重复子问题 - 画状态转移表 - 根据递推关系填表 - 将填表过程翻译成代码
```

尽管大部分状态转移表都是二维的（简单问题可以使用一维），但是如果问题比较复杂需要很多变量，对应的状态表可能是高维度的，这种情况下就不适应用状态转移表法了。 一方面不容易画图表示，另外一方面人脑确实很不擅长思考高维度的问题。

## 状态转移方程

状态转移方程有点类似递归的解题思路，需要分析某个问题如何通过子问题来递归求解，也就是所谓**最优字结构**；
根据最优字结构写出递推公式，也就是所谓**状态转移方程**
最后就是用代码实现状态转移方程。一般也有两种代码实现方式，一种是递归加备忘录，一种是迭代递推。

```
找最优子结构 - 写状态转移方程 - 将状态转移方程翻译成代码
```

状态转移方程是解决问题的关键和难点，一般将方程翻译为代码是比较简单的。难度在于状态本身可能不好定义，方程也就更不好想到。

## 取舍

不是每个问题都适用这两种思路，有时一种会比另一种更清晰，需要结合具体题目来看。

# 贪心、分治、回溯、动态规划的区别和联系

相较而言，贪心、回溯、动态规划 可以划为一类，分治是另一类。前三个基本都可以抽象为多阶段决策最优解模型，分治也是最优解，但是一般不能抽象成多阶段决策。

回溯法相当于是穷举搜索，是万金油，基本是贪心和动态规划可以解决的问题都能使用回溯法解决。其问题是时间复杂度是指数级的，只能解决小规模问题。

与分治法最大的差别是：适合于用动态规划法求解的问题，经分解后得到的子问题往往不是互相独立的（即下一个子阶段的求解是建立在上一个子阶段的解的基础上，进行进一步的求解）。

## 动态规划问题的特征

动态规划效率很高，但是不是所有问题都能使用动态规划，需要满足三个条件：最优子结构、无后效性和贪心选择性（并非重复子问题）
贪心选择性是指通过局部最优的选择，能产生全局的最优选择。每个阶段我们都选择当前最优策略，所有阶段决策完成后，这些局部最优解构成全局最优解。
















