package com.example.user.sqlite.Model;

/**
 * Created by User on 09.10.2016.
 */
public class Person {


    public Person()
    {

    }
    public Person(int id, String name, String surname, int age)
    {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    private  int id;
    private  String name;
    private  String surname;
    private  int age;

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
