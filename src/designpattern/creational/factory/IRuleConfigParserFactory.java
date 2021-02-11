package designpattern.creational.factory;


import designpattern.creational.simplefactory.*;

public interface IRuleConfigParserFactory {
    IRuleConfigParser createParser();
}

