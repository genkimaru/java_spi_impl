package com.cf.ioc;

import com.cf.spi.Developer;

public class BeanFactory<T> {
    public T getBean(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> aClass = Class.forName(className);
        T developer = (T) aClass.newInstance();
        return developer;
    }
}
