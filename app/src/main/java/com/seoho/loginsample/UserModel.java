package com.seoho.loginsample;

import java.util.Objects;

public class UserModel {

    private String id;
    private String name;
    private int age;

    @Override
    public String toString() {
        return "UserModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return age == userModel.age &&
                Objects.equals(id, userModel.id) &&
                Objects.equals(name, userModel.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, age);
    }

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
}
