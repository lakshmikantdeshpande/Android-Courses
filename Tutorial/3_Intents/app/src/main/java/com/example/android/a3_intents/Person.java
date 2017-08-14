package com.example.android.a3_intents;

import java.io.Serializable;

/**
 * Created by s on 15/8/17.
 */

public class Person implements Serializable {
    private String name;
    private Double salary;

    public Person(String name, Double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
