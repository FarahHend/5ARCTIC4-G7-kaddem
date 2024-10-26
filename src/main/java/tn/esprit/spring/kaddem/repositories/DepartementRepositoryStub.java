package tn.esprit.spring.kaddem.repositories;

import tn.esprit.spring.kaddem.entities.Departement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DepartementRepositoryStub implements DepartementRepository {
    private final Map<Integer, Departement> database = new HashMap<>();
    private int currentId = 1;
    private final List<Departement> departements = new ArrayList<>();


    @Override
    public List<Departement> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public Iterable<Departement> findAllById(Iterable<Integer> integers) {
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
    public Departement save(Departement departement) {
        if (departement.getIdDepart() == null) {
            departement.setIdDepart(currentId++);
        }
        database.put(departement.getIdDepart(), departement);
        return departement;
    }

    @Override
    public <S extends Departement> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Departement> findById(Integer integer) {
        return Optional.empty();
    }


    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public void delete(Departement departement) {
        database.remove(departement.getIdDepart());
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Departement> entities) {

    }

    @Override
    public void deleteAll() {

    }

    // Implement any other required methods for DepartementRepository interface
}
