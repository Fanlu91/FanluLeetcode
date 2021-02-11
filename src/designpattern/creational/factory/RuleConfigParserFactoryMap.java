package designpattern.creational.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * 为工厂类再创建一个简单工厂，也就是工厂的工厂，用来创建工厂类对象。
 * 创建工厂对象的工厂类
 */
public class RuleConfigParserFactoryMap {
    private static final Map<String, IRuleConfigParserFactory> cachedFactories = new HashMap<>();

    static {
        cachedFactories.put("json", new JsonRuleConfigParserFactory());
        cachedFactories.put("xml", new XmlRuleConfigParserFactory());
        cachedFactories.put("yaml", new YamlRuleConfigParserFactory());
        cachedFactories.put("properties", new PropertiesRuleConfigParserFactory());
    }

    // 返回缓存好的单例工厂对象
    public static IRuleConfigParserFactory getParserFactory(String type) {
        if (type == null || type.isEmpty()) {
            return null;
        }
        IRuleConfigParserFactory parserFactory = cachedFactories.get(type.toLowerCase());
        return parserFactory;
    }
}
