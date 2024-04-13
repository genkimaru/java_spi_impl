package com.cf.spi;

import java.util.ServiceLoader;

public class ServiceLoaderDemo {

    public static void main(String[] args) {
        ServiceLoader<Developer> serviceLoader = ServiceLoader.load(Developer.class);
        serviceLoader.forEach(
                developer -> {
                    developer.print();
                }
        );
    }
}
