package com.example.revisionapp;

public class Etudiant {
    private int id;
    private String nom;
    private String email;
    private String password;
    private String prenom;

    public Etudiant(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Etudiant(String nom, String email, String password, String prenom) {
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.prenom = prenom;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
