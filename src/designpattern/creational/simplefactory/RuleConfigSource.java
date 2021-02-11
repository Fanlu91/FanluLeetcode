package designpattern.creational.simplefactory;

/**
 * 简单工厂的第一种实现方式
 * <p>
 * 最直接的
 * <p>
 * 把createParser 方法剥离到一个独立的类里，让这个类只负责对象的创建。
 */
public class RuleConfigSource {
    public RuleConfig load(String ruleConfigFilePath) {
        String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);
        IRuleConfigParser parser = RuleConfigParserFactory1.createParser(ruleConfigFileExtension);
        if (parser == null) {
//            throw new InvalidRuleConfigException("Rule config file format is not supported: " + ruleConfigFilePath);
        }

        String configText = "";
        //从ruleConfigFilePath文件中读取配置文本到configText中
        RuleConfig ruleConfig = parser.parse(configText);
        return ruleConfig;
    }

    private String getFileExtension(String filePath) {
        //...解析文件名获取扩展名，比如rule.json，返回json
        return "json";
    }
}

class RuleConfigParserFactory1 {
    public static IRuleConfigParser createParser(String configFormat) {
        IRuleConfigParser parser = null;
        if ("json".equalsIgnoreCase(configFormat)) {
            parser = new JsonRuleConfigParser();
        } else if ("xml".equalsIgnoreCase(configFormat)) {
            parser = new XmlRuleConfigParser();
        } else if ("yaml".equalsIgnoreCase(configFormat)) {
            parser = new YamlRuleConfigParser();
        } else if ("properties".equalsIgnoreCase(configFormat)) {
            parser = new PropertiesRuleConfigParser();
        }
        return parser;
    }
}


//class IRuleConfigParser {
//
//    public RuleConfig parse(String configText) {
//        return new RuleConfig();
//    }
//}
//
//class JsonRuleConfigParser extends IRuleConfigParser {
//
//}
//
//class XmlRuleConfigParser extends IRuleConfigParser {
//
//}
//
//class YamlRuleConfigParser extends IRuleConfigParser {
//
//}
//
//class PropertiesRuleConfigParser extends IRuleConfigParser {
//
//}
