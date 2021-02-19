package helper;

public class Solution {
    public String id;
    public String topic;
    public String level;
    public String source;
    public String tips;
    public String solutionPath;
    public String result;

    private String getCNSource() {
        if (source.contains("leetcode.com"))
            return source.replace("leetcode.com", "leetcode-cn.com");
        return source;
    }

    private String getENSource() {
        if (source.contains("leetcode-cn.com"))
            return source.replace("leetcode-cn", "leetcode.com");
        return source;
    }

    private String getFourDigitId() {
        try {
            return String.format("%04d", Integer.valueOf(id));
        } catch (NumberFormatException e) {
            return id;
        }
    }

    @Override
    public String toString() {
        return String.join("|", "", getFourDigitId(), getENSource(), getCNSource(), level, solutionPath, result, topic, "");
    }
}
