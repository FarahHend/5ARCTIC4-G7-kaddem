package com.hend.EquipeMicroservice.entities;

import java.time.LocalDate;

public class ProjetResponse {
    private String idProjet;
    private String nomProjet;
    private double budget;
    private LocalDate deadline;
    private boolean budgetDepasse;
    private boolean enRetard;

    // Default constructor
    public ProjetResponse() {
    }
    public ProjetResponse(String idProjet, String nomProjet, double budget, LocalDate deadline,
                          boolean budgetDepasse, boolean enRetard) {
        this.idProjet = idProjet;
        this.nomProjet = nomProjet;
        this.budget = budget;
        this.deadline = deadline;
        this.budgetDepasse = budgetDepasse;
        this.enRetard = enRetard;
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

}
