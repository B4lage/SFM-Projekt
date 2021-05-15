/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.model;

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
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int kor;
    private int nem;
    private int magassag;
    private double suly;
    private double aktivitas;
    
    @OneToOne
    @JoinColumn(name = "userId")
    private UserAuthentication felhasznalo;

    public UserAuthentication getFelhasznalo() {
        return felhasznalo;
    }

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

    public int getKor() {
        return kor;
    }

    public void setKor(int kor) {
        this.kor = kor;
    }

    public int getNem() {
        return nem;
    }

    public void setNem(int nem) {
        this.nem = nem;
    }

    public double getMagassag() {
        return magassag;
    }

    public void setMagassag(int magassag) {
        this.magassag = magassag;
    }

    public double getSuly() {
        return suly;
    }

    public void setSuly(double suly) {
        this.suly = suly;
    }

    public double getAktivitas() {
        return aktivitas;
    }

    public void setAktivitas(double aktivitas) {
        this.aktivitas = aktivitas;
    }

    public void setFelhasznalo(UserAuthentication felhasznalo) {
        this.felhasznalo = felhasznalo;
    }
    
    
}
