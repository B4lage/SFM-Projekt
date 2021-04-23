/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.model;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "days")
public class Day {
    @Id
    @GeneratedValue
    private int id;
    private LocalDate datum;
    private double kcal;
    private double protein;
    private double ch;
    private double fat;
    private int gramm;
    //private int goal;
    
    @ManyToOne
    @JoinColumn(name = "mealID")
    private Meal meal;
    
    @ManyToOne
    @JoinColumn(name = "userID")
    private UserAuthentication usr;

    public UserAuthentication getUsr() {
        return usr;
    }

    public void setUsr(UserAuthentication usr) {
        this.usr = usr;
    }

    

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }   
    
    public void setId(int id) {
        this.id = id;
    }
    
    
    public int getGramm() {
        return gramm;
    }

    public void setGramm(int gramm) {
        this.gramm = gramm;
    }
    

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public double getKcal() {
        return kcal;
    }

    public void setKcal(double kcal) {
        this.kcal = kcal;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getCh() {
        return ch;
    }

    public void setCh(double ch) {
        this.ch = ch;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    /*public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }*/
    
}
