package helper;

import java.io.IOException;
import java.nio.file.Files;

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
    public static void main(String[] args) {
        String readme = String.join("\n",
                "# FanluLeetcode ",
                "",
                "## Leetcode solutions written in Java ",
                "Below table is generated using this [class](https://github.com/Fanlu91/FanluLeetcode/blob/master/src/utils/ReadMeGenerator.java)",
                " ",
                " ",
                "|Topic|Id|Title|Solution|Result|",
                "|---|---|---|---|---|");

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
//                            System.out.println(path);
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
                return o1.source.compareTo(o2.topic);
            }
        });

//        solutionList.sort(new Comparator<Solution>() {
//            @Override
//            public int compare(Solution o1, Solution o2) {
//                return o2.topic.compareTo(o1.topic);
//            }
//        });

        System.out.println(readme);
        solutionList.forEach(System.out::println);
//        System.out.println(readme);
    }
}
