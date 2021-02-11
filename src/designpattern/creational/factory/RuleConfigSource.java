package designpattern.creational.factory;

import designpattern.creational.simplefactory.IRuleConfigParser;
import designpattern.creational.simplefactory.RuleConfig;

/**
 * 为工厂类再创建一个简单工厂，也就是工厂的工厂，用来创建工厂类对象。
 */
public class RuleConfigSource {
    public RuleConfig load(String ruleConfigFilePath) {
        String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);
        IRuleConfigParserFactory parserFactory = RuleConfigParserFactoryMap.getParserFactory(ruleConfigFileExtension);
        if (parserFactory == null) {
//            throw new InvalidRuleConfigException("Rule config file format is not supported: " + ruleConfigFilePath);
        }
        IRuleConfigParser parser = parserFactory.createParser();
        String configText = ""; //从ruleConfigFilePath文件中读取配置文本到configText中
        RuleConfig ruleConfig = parser.parse(configText);
        return ruleConfig;
    }

    private String getFileExtension(String ruleConfigFilePath) {
        //...解析文件名获取扩展名，比如rule.json，返回json
        return "json";
    }
}
