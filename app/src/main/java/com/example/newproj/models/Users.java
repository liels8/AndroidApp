package com.example.newproj.models;

public class Users {
    private String Id;
    private String Name;
    private String LastName;
    private String Age;
    private String Email;
    private String Type;

    private String Password;

    public Users(){}

    public Users(Users usr){
        this.Name=usr.Name;
        this.LastName=usr.LastName;
        this.Age=usr.Age;
        this.Email=usr.Email;
        this.Type=usr.Type;
    }

    public Users(String name,String lastName,String age ,String email,String password){
        this.Name=name;
        this.LastName=lastName;
        this.Age=age;
        this.Email=email;
        this.Type="user";
        this.Password=password;
    }


    public String getPassword() { return Password; }

    public void setPassword(String password) { Password = password; }

    public String getName() {
        return Name;
    }

    public String getLastName() {
        return LastName;
    }

    public String getAge() {
        return Age;
    }

    public String getEmail() {
        return Email;
    }

    public String getType() {
        return Type;
    }

    public void setTypeManager(){
        this.Type="manager";
    }

    public void setName(String name) {
        Name = name;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setAge(String age) {
        Age = age;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setType(String type) {
        Type = type;
    }
}
