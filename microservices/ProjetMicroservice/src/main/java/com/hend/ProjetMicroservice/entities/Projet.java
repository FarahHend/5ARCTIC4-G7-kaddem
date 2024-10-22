package com.hend.ProjetMicroservice.entities;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "projets")
public class Projet {
    @Id
    private String idProjet;
    private String nomProjet;
    private double budget;
    private LocalDate deadline;
    private boolean budgetDepasse;
    private boolean enRetard;
    private ProjectStatus status;

    public Projet() {

    }

    public Projet(String nomProjet, double budget, LocalDate deadline, ProjectStatus status) {
        this.nomProjet = nomProjet;
        this.budget = budget;
        this.deadline = deadline;
        this.status = status;
    }

    // Getters and Setters
    public String getIdProjet() {
        return idProjet;
    }

    public void setIdProjet(String idProjet) {
        this.idProjet = idProjet;
    }

    public String getNomProjet() {
        return nomProjet;
    }

    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public boolean isBudgetDepasse() {
        return budgetDepasse;
    }

    public void setBudgetDepasse(boolean budgetDepasse) {
        this.budgetDepasse = budgetDepasse;
    }

    public boolean isEnRetard() {
        return enRetard;
    }

    public void setEnRetard(boolean enRetard) {
        this.enRetard = enRetard;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

}
