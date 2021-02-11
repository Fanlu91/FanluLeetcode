package designpattern.creational.factory;

import designpattern.creational.simplefactory.IRuleConfigParser;
import designpattern.creational.simplefactory.PropertiesRuleConfigParser;

public class PropertiesRuleConfigParserFactory implements IRuleConfigParserFactory {
    @Override
    public IRuleConfigParser createParser() {
        return new PropertiesRuleConfigParser();
    }
}
