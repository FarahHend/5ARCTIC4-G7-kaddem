package tn.esprit.spring.kaddem.entities;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProjet;
    private String nomProjet;
    private double budget;
    private LocalDate deadline;

    private boolean budgetDepasse;
    private boolean enRetard;

    @ManyToMany(mappedBy = "projets")
    private Set<Equipe> equipes;

    public Projet() {
    }

    public Projet(String nomProjet, double budget, LocalDate deadline, boolean budgetDepasse, boolean enRetard) {
        this.nomProjet = nomProjet;
        this.budget = budget;
        this.deadline = deadline;
        this.budgetDepasse = budgetDepasse;
        this.enRetard = enRetard;
    }

    // Getters et Setters

    public Integer getIdProjet() {
        return idProjet;
    }

    public void setIdProjet(Integer idProjet) {
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

    public Set<Equipe> getEquipes() {
        return equipes;
    }

    public void setEquipes(Set<Equipe> equipes) {
        this.equipes = equipes;
    }
}
