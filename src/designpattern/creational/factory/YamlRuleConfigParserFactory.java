package designpattern.creational.factory;

import designpattern.creational.simplefactory.IRuleConfigParser;
import designpattern.creational.simplefactory.YamlRuleConfigParser;

public class YamlRuleConfigParserFactory implements IRuleConfigParserFactory {
    @Override
    public IRuleConfigParser createParser() {
        return new YamlRuleConfigParser();
    }
}
