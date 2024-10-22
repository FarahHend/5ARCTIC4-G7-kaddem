package com.hend.EquipeMicroservice.services;

import com.hend.EquipeMicroservice.entities.Equipe;

import java.util.List;

public interface IEquipeService {
    public List<Equipe> retrieveAllEquipes();
    public Equipe addEquipe(Equipe e);
    public  void deleteEquipe(Integer idEquipe);
    public Equipe updateEquipe(Integer id, Equipe e);
    public Equipe retrieveEquipe(Integer equipeId);
    //public void evoluerEquipes();
    public double calculerEfficaciteEquipe(Integer idEquipe);
}

