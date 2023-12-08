package app.casinoroyale.Controller.FirebaseControllers;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import static app.casinoroyale.Model.DataModels.UserModels.Player.person;

/**
 *
 * @author user
 */
public class Person {
    private String Name;
    private String Email;
    private String Password;
    private int Age;
    private double Balance;


    public Person() {

    }
    public Person(String name, String email, String password, int age, double balance) {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }
    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }


    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        this.Age = age;
    }

    public void setBalance(double balance){ this.Balance = balance;}
    public double getBalance(){return Balance;}

}