package utils;

public class Solution {
    public String id;
    public String topic;
    public String source;
    public String tips;
    public String solutionPath;

    @Override
    public String toString() {
        return String.join("|","",topic,id,source,solutionPath,"");
    }
}
