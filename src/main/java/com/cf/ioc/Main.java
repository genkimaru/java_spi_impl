package com.cf.ioc;

import com.cf.spi.Developer;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        BeanFactory<Developer> beanFactory = new BeanFactory<Developer>();
        String className = "com.cf.spi.Adeveloper";
        Developer bean = beanFactory.getBean(className);
        bean.print();

        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));


    }
}
