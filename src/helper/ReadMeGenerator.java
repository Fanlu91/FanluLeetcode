package helper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/***
 * This is a readme generator.
 * What it mainly does is walking through all java files to get information
 * sorts all of them by some order(id as for now)
 * finally creates the index table.
 */
public class ReadMeGenerator {
    final public static HashMap<String, String> wordMap = new HashMap<String, String>() {
        {
            put("tree", "Tree");
            put("design", "Design");
            put("breadthfirstsearch", "Breadth-first Search");
            put("bitmanipulation", "Bit Manipulation");
            put("stringmatch", "String Match");
            put("orderedmap", "Ordered Map");
            put("twopointers", "Two Pointers");
            put("array", "Array");
            put("recursion", "Recursion");
            put("jianzhioffer", "Jianzhi Offer");
            put("unionfind", "Union Find");
            put("linkedlist", "Linked List");
            put("depthfirstsearch", "Depth-First Search");
            put("hashfunction", "Hash Function");
            put("binarysearch", "Binary Search");
            put("backtracking", "Backtracking");
            put("graph", "Graph");
            put("slidingwindow", "Sliding Window");
            put("details", "Details");
            put("minimax", "Minimax");
            put("gcd", "Greatest Common Divisor");
            put("binarysearchtree", "Binary Search Tree");
            put("math", "Math");
            put("concurrency", "Concurrency");
            put("trie", "Trie");
            put("heap", "Heap");
            put("greedy", "Greedy");
            put("divideandconquer", "Divide & Conquer");
            put("hashtable", "Hashtable");
            put("string", "String");
            put("geometry", "Geometry");
            put("stack", "Stack");
            put("dynamicprogramming", "Dynamic Programming");
            put("binarytree", "Binary Tree");
            put("matrix", "Matrix");
            put("morristraversal", "Morris Traversal");
        }
    };

    final public static String readme = String.join("\n",
            "# FanluLeetcode ",
            "",
            "Leetcode 解题记录。根据题目的标签进行了题目的归档和解题方法总结。",
            "- 题号： 四位数字方便搜索定位",
            "- 主题总结：以当前标签或主题为单位总结理论知识及解题套路和心得",
            "- 题目地址：包括leetcode.com 和 leetcode-cn.com",
            "- 难度：作者对当前题目难度的定义（主观），有明显差距的会在官方给定难度等级之上通过`+`,`-`进行标记",
            "- 代码：`Java` 解法的代码，一般会包含最符合题意的解及效率最高解。并会给出每种解的排名数据反馈",
            "- 结果：最优解的排名反馈数据",
            "- 标签：和本题直接相关的标签，可能超出官方给出的标签范围。",
            " ",
            " ",
            "");

    public static boolean skipPackages(Path path) {
//        Set<String> set = new HashSet<String>() {{
//            add("a");
//            add("b");
//        }};
        Set<String> set = Stream.of("designpattern", "helper", "jianzhioffer", "concurrent").collect(Collectors.toSet());

        String packagePath = path.toAbsolutePath().toString();
        for (String s : set) {
            if (packagePath.contains(s))
                return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        StringBuilder stringBuilder = new StringBuilder(readme);

        stringBuilder.append("### Helper").append("\n")
                .append("README 文件通过[此类](https://github.com/Fanlu91/FanluLeetcode/blob/master/src/helper/ReadMeGenerator.java)生成。")
                .append("\n").append("\n");
        /**
         * 得到package list
         *
         * 排除部分包
         */
        List<Path> packagesPathList = new LinkedList<>();
        Files.walk(Paths.get(System.getProperty("user.dir") + "/src/"))
                .filter(Files::isDirectory)
//                .filter(path -> skipPackages(path)) // below replace lambda with method reference
                .filter(ReadMeGenerator::skipPackages)
//                .forEach(f -> System.out.println(f.toAbsolutePath()));
                .forEach(packagesPathList::add);

        /**
         * 通过总结文章的长度决定展示的权重（先后）
         */
        packagesPathList.sort(new Comparator<Path>() {
            @Override
            public int compare(Path o1, Path o2) {
                File readmeFile1 = new File(o1.toAbsolutePath() + "/README.md");
                File readmeFile2 = new File(o2.toAbsolutePath() + "/README.md");
                return (int) (readmeFile2.length() - readmeFile1.length());
            }
        });

        for (Path packagePath : packagesPathList) {
//            System.out.println(packagePath.getFileName().toString());
//            System.out.println("-parent-" + packagePath.getParent().toString());
            if (packagePath.getFileName().endsWith("src")) {
                continue;
            }
            /**
             * 打印包名，即标签名
             */
            stringBuilder.append("### ").append(wordMap.get(packagePath.getFileName().toString())).append("\n");
            File readmeFile = new File(packagePath.toAbsolutePath() + "/README.md");

            /**
             * 如果没有readme， 创建空文件
             * 如果readme为空，提示暂无总结
             */
            if (!readmeFile.exists())
                readmeFile.createNewFile();
            String summary = "暂无总结";
            if (readmeFile.length() != 0) {
                summary = "[主题总结](https://github.com/Fanlu91/FanluLeetcode/blob/master/src/"
                        + packagePath.getFileName().toString()
                        + "/README.md)";
            }
            stringBuilder.append(summary).append("\n");

            /**
             * 构建题解表
             */
            stringBuilder.append("\n")
                    .append("|题号|题目（官网）|题目（中国）|难度|代码|结果|标签|")
                    .append("\n")
                    .append("|---|---|---|---|---|---|---|").append("\n");

            List<Solution> solutionList = new LinkedList<>();
            Files.walk(packagePath).filter(Files::isRegularFile)
                    .filter(f -> f.toString().endsWith(".java")).forEach(path -> {
                Solution solution = new Solution();
                solution.solutionPath = "[java](https://github.com/Fanlu91/FanluLeetcode/blob/master/src"
                        + path.toString().split("src")[1].trim() + ")";

                List<String> list = null;
                try {
                    list = Files.lines(path)
                            .filter(line -> line.matches("^//[ ].*"))
                            .collect(Collectors.toList());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(path);
                assert list != null;
                list.forEach(line -> {
                            if (line.contains("Id"))
                                solution.id = line.split(" : ")[1].trim();
                            else if (line.contains("Topic")) {
                                String topic = line.split(" : ")[1].trim();
                                solution.topic = wordMap.getOrDefault(topic, topic);
                            } else if (line.contains("Level")) {
                                solution.level = line.split(" : ")[1].trim();
                            } else if (line.contains("Source")) {
                                String sourceUri = line.split(" : ")[1].split("problems/")[1].trim();
                                solution.source = "[" + sourceUri.substring(0, sourceUri.length() - 1) + "]("
                                        + line.split(" : ")[1].trim() + ")";
                            } else if (line.contains("Result")) {
                                solution.result = line.split(" : ")[1].trim();
                                // Result is mandatory for a solution to be add on the table.
                                solutionList.add(solution);
                            }
                        }
                );
            });

            /**
             * solution 按 id 排序
             */
            solutionList.sort(new Comparator<Solution>() {
                @Override
                public int compare(Solution o1, Solution o2) {
//                    return Integer.valueOf(o1.id) - Integer.valueOf(o2.id);
                    return Integer.parseInt(o1.id) - Integer.parseInt(o2.id);
                }
            });
            solutionList.forEach(string -> stringBuilder.append(string).append("\n"));
            stringBuilder.append("\n");
        }

        /**
         * 增加结尾
         */
        File file = new File(System.getProperty("user.dir") + "/README.md");
        // try with resources, java 7 and above
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
