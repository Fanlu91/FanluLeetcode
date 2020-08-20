package helper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/***
 * This is a readme generator.
 * What it mainly does is walking through all java files to get information from each one's description
 * sorts all of them by some order(topic as for now)
 * finally creates the index table.
 */
public class ReadMeGenerator {
    public static void main(String[] args) throws IOException {
/*        String readme = String.join("\n",
                "# FanluLeetcode ",
                "",
                "## Leetcode solutions written in Java ",
                "Below table is generated using this [class](https://github.com/Fanlu91/FanluLeetcode/blob/master/src/utils/ReadMeGenerator.java)",
                " ",
                " ",
                "|Topic|Id|Title|Solution|Result|",
                "|---|---|---|---|---|",
                "");*/

        String readme = String.join("\n",
                "# FanluLeetcode ",
                "",
                "## Leetcode solutions written in Java ",
                "Below table is generated using this [class](https://github.com/Fanlu91/FanluLeetcode/blob/master/src/utils/ReadMeGenerator.java)",
                " ",
                " ",
                "");

        StringBuilder stringBuilder = new StringBuilder(readme);
        List<Solution> solutionList = new LinkedList<>();

        // 得到package list
        List<Path> packagesPathList = new LinkedList<>();
        Files.walk(Paths.get(System.getProperty("user.dir") + "/src/")).filter(Files::isDirectory).forEach(packagesPathList::add);


        for (Path packagePath : packagesPathList) {
            System.out.println(packagePath.getFileName());
            if (packagePath.getFileName().endsWith("src")) {
                continue;
            }


            stringBuilder.append(packagePath.getFileName()).append("\n");
            File readmeFile = new File(packagePath.toAbsolutePath() + "/README.md");
            /**
             * 如果没有 readme 则创建
             */
            if (readmeFile.exists()) {
                System.out.println(readmeFile.length());
            } else {
                readmeFile.createNewFile();
            }
            /**
             * 如果readme为空，提示暂无总结
             */
            String summary = "暂无总结";
            if (readmeFile.length() != 0) {
                summary = "[主题总结](https://github.com/Fanlu91/FanluLeetcode/blob/master/src/" + packagePath.toAbsolutePath() + "/README.md" + ")";
            }
            stringBuilder.append(summary).append("\n");

            stringBuilder.append("\n").append("|Topic|Id|Title|Solution|Result|").append("\n").append("|---|---|---|---|---|").append("\n");

            Files.walk(packagePath).filter(Files::isRegularFile).filter(f -> f.toString().endsWith(".java")).forEach(path -> {
                List<String> list = null;
                try {
                    list = Files.lines(path)
                            .filter(line -> line.matches("^//[ ].*"))
                            .collect(Collectors.toList());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(path);
                Solution solution = new Solution();
                solution.solutionPath = "[java](https://github.com/Fanlu91/FanluLeetcode/blob/master/src"
                        + path.toString().split("src")[1].trim() + ")";
                list.forEach(line -> {
                            if (line.contains("Id"))
                                solution.id = line.split(" : ")[1].trim();
                            if (line.contains("Topic"))
                                solution.topic = line.split(" : ")[1].trim();
                            if (line.contains("Source")) {
                                String sourceUri = line.split(" : ")[1].split("problems/")[1].trim();
                                solution.source = "[" + sourceUri.substring(0, sourceUri.length() - 1) + "]("
                                        + line.split(" : ")[1].trim() + ")";
                            }
                            if (line.contains("Result")) {
                                solution.result = line.split(" : ")[1].trim();
                                // Result is mandatory for a solution to be on the table.
                                solutionList.add(solution);
                            }
                        }
                );
            });

            /**
             * 给solution排序
             */
            solutionList.sort(new Comparator<Solution>() {
                @Override
                public int compare(Solution o1, Solution o2) {
                    return Integer.valueOf(o1.id) - Integer.valueOf(o2.id);
                }
            });
            solutionList.forEach(string -> stringBuilder.append(string).append("\n"));

        }



        /*Files.walk(Paths.get(System.getProperty("user.dir")))
                .filter(Files::isRegularFile)
                .filter(f -> f.toString().endsWith(".java"))
                .forEach(path -> {
                    List<String> list = null;
                    try {
                        list = Files.lines(path)
                                .filter(line -> line.matches("^//[ ].*"))
                                .collect(Collectors.toList());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(path);
                    Solution solution = new Solution();
                    solution.solutionPath = "[java](https://github.com/Fanlu91/FanluLeetcode/blob/master/src"
                            + path.toString().split("src")[1].trim() + ")";
                    list.forEach(line -> {
                                if (line.contains("Id"))
                                    solution.id = line.split(" : ")[1].trim();
                                if (line.contains("Topic"))
                                    solution.topic = line.split(" : ")[1].trim();
                                if (line.contains("Source")) {
                                    String sourceUri = line.split(" : ")[1].split("problems/")[1].trim();
                                    solution.source = "[" + sourceUri.substring(0, sourceUri.length() - 1) + "]("
                                            + line.split(" : ")[1].trim() + ")";
                                }
                                if (line.contains("Result")) {
                                    solution.result = line.split(" : ")[1].trim();
                                    // Result is mandatory for a solution to be on the table.
                                    solutionList.add(solution);
                                }
                            }
                    );
//                            System.out.println(solution);

                });*/

//        solutionList.sort(new Comparator<Solution>() {
//            @Override
//            public int compare(Solution o1, Solution o2) {
//                return Integer.valueOf(o1.id) - Integer.valueOf(o2.id);
//            }
//        });
//
//        solutionList.sort(new Comparator<Solution>() {
//            @Override
//            public int compare(Solution o1, Solution o2) {
//                return o1.topic.compareTo(o2.topic);
//            }
//        });


//        solutionList.forEach(string -> stringBuilder.append(string).append("\n"));
//        solutionList.forEach(System.out::println);
//        System.out.println(readme);


        File file = new File(System.getProperty("user.dir") + "/README.md");
        // try with resources, java 7 and above
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
