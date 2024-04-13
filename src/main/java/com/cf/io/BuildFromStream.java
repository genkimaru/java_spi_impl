package com.cf.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.UUID;

/**
 * usage: builder design pattern
 * refer: protocol buffer use builder design pattern for generated java code.
 * Date: 2024年04月13日19:28:32
 */
public class BuildFromStream {

    public static void main(String[] args) throws IOException {
        Person p = new PersonBuilder().newBuilder().setId("1").setName("Jean").setAge(10).build();

        System.out.println(p);

        ArrayList<Person> list = new ArrayList<>();
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        // System.out 是 PrintStream 类型。
        // System.in 是 InputStream 类型
        while (true) {
            PersonBuilder pb = new PersonBuilder().newBuilder();
            System.out.println("Enter name : ");
            String name = stdin.readLine();
            pb.setName(name);
            System.out.println("Enter age  ");
            String age = stdin.readLine();
            if (!"".equals(age)) {
                int ageInt = Integer.parseInt(age);
                pb.setAge(ageInt);
            }


            if (!"".equals(name) || !"".equals(age)) {
                pb.setId(String.valueOf(UUID.randomUUID()));
                list.add(pb.build());
            } else {
                break;
            }
        }

        for (Person p2 : list) {
            System.out.println(p2);
        }

    }
}

class Person {
    String id;
    String name;
    int age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class PersonBuilder {
    private Person person = new Person();

    public PersonBuilder newBuilder() {
        return this;
    }

    public PersonBuilder setId(String id) {
        person.setId(id);
        return this;
    }

    public PersonBuilder setName(String name) {
        person.setName(name);
        return this;
    }

    public PersonBuilder setAge(int age) {
        person.setAge(age);
        return this;
    }

    public Person build() {
        return this.person;
    }

}