package tn.esprit.spring.kaddem.repositories;

import tn.esprit.spring.kaddem.entities.Etudiant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EtudiantRepositoryStub implements EtudiantRepository {
    private final Map<Integer, Etudiant> database = new HashMap<>();
    private int currentId = 1;

    @Override
    public List<Etudiant> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public Iterable<Etudiant> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public Etudiant save(Etudiant etudiant) {
        if (etudiant.getIdEtudiant() == null) {
            etudiant.setIdEtudiant(currentId++);
        }
        database.put(etudiant.getIdEtudiant(), etudiant);
        return etudiant;
    }

    @Override
    public Optional<Etudiant> findById(Integer id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public void delete(Etudiant etudiant) {
        database.remove(etudiant.getIdEtudiant());
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Etudiant> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Etudiant> findEtudiantsByDepartement_IdDepart(Integer idDepart) {
        // Implement as necessary
        return null; // Placeholder
    }

    @Override
    public Etudiant findByNomEAndPrenomE(String nomE, String prenomE) {
        return null;
    }

    @Override
    public <S extends Etudiant> List<S> saveAll(Iterable<S> entities) {
        // Implement as necessary
        return null; // Placeholder
    }

}
