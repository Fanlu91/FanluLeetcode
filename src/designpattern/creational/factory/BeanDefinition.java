package designpattern.creational.factory;

import java.util.List;

public class BeanDefinition {
    public boolean isLazyInit() {
        return true;
    }

    public String getId() {
        return null;
    }

    public boolean isSingleton() {
        return true;
    }

    public String getClassName() {
        return null;
    }

    public List<BeanDefinition.ConstructorArg> getConstructorArgs() {
        return null;
    }

    public class ConstructorArg {
        public boolean getIsRef() {
            return true;
        }

        public String getArg() {
            return null;
        }

        public Class getType() {
            return null;
        }
    }
}
