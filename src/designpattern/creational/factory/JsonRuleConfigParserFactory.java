package designpattern.creational.factory;

import designpattern.creational.simplefactory.IRuleConfigParser;
import designpattern.creational.simplefactory.JsonRuleConfigParser;

public class JsonRuleConfigParserFactory implements IRuleConfigParserFactory {
    @Override
    public IRuleConfigParser createParser() {
        return new JsonRuleConfigParser();
    }
}
