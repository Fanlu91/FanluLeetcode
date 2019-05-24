package utils;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Paths;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/***
 * This is a
 */
public class ReadMeGenerator {
    public static void main(String[] args) {
        String readme = String.join("\n",
                "# FanluLeetcode ",
                "",
                "### Leetcode solutions written in Java ",
                " ",
                " ",
                "| Topic | Id | Title | Solution |",
                "|---|---|---|---|");

        List<Solution> solutionList = new LinkedList<>();
        try {
            Files.walk(Paths.get(System.getProperty("user.dir")))
                    .filter(Files::isRegularFile)
                    .filter(f -> f.toString().endsWith(".java"))
                    .forEach(path -> {
                        try {
                            List<String> list = Files.lines(path)
                                    .filter(line -> line.matches("^//[ ].*"))
                                    .collect(Collectors.toList());
                            Solution solution = new Solution();
                            solution.solutionPath = "[java](https://github.com/Fanlu91/FanluLeetcode/blob/master/src"
                                    + path.toString().split("src")[1].trim() + ")";
                            list.forEach(line -> {
                                        if (line.contains("Id"))
                                            solution.id = line.split(" : ")[1].trim();
                                        if (line.contains("Topic"))
                                            solution.topic = line.split(" : ")[1].trim();
                                        if (line.contains("Source")) {
                                            solution.source = line.split(" : ")[1].trim();
                                            solutionList.add(solution);
                                        }
                                    }
                            );
//                            System.out.println(solution);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        solutionList.sort(new Comparator<Solution>() {
            @Override
            public int compare(Solution o1, Solution o2) {
                return o1.topic.compareTo(o2.topic);
            }
        });

        System.out.println(readme);
        solutionList.forEach(System.out::println);
//        System.out.println(readme);
    }
}
