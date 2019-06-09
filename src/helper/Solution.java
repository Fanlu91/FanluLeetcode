package helper;

public class Solution {
    public String id;
    public String topic;
    public String level;
    public String source;
    public String tips;
    public String solutionPath;
    public String result;

    @Override
    public String toString() {
        return String.join("|", "", topic, id, source, solutionPath, result, "");
    }
}
