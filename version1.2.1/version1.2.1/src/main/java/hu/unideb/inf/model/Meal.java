/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "meals")
public class Meal {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "name", nullable = false, unique = false)
    private String name;
    @Column(name = "kcal", nullable = false, unique = false)
    private double kcal;
    @Column(name = "ch", nullable = false, unique = false)
    private double ch;
    @Column(name = "protein", nullable = false, unique = false)
    private double protein;
    @Column(name = "fat", nullable = false, unique = false)
    private double fat;
    @Column(name = "portion", nullable = false, unique = false)
    private int portion;        // 1 portion how many g/ml for example: 1 portion is 100g

    
    /*@OneToMany
    @JoinColumn(name = "mealID")
    private Set<Day> day = new HashSet<>();*/

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getKcal() {
        return kcal;
    }

    public void setKcal(double kcal) {
        this.kcal = kcal;
    }

    public int getPortion() {
        return portion;
    }

    public void setPortion(int portion) {
        this.portion = portion;
    }

    public double getCh() {
        return ch;
    }

    public void setCh(double ch) {
        this.ch = ch;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }
    
    @Override
    public String toString()
    {
        return name;
    }

    /*public Set<Day> getDay() {
        return day;
    }

    public void setDay(Set<Day> day) {
        this.day = day;
    }*/


}
