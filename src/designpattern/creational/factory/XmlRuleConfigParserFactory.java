package designpattern.creational.factory;

import designpattern.creational.simplefactory.IRuleConfigParser;
import designpattern.creational.simplefactory.XmlRuleConfigParser;

public class XmlRuleConfigParserFactory implements IRuleConfigParserFactory {
    @Override
    public IRuleConfigParser createParser() {
        return new XmlRuleConfigParser();
    }
}
