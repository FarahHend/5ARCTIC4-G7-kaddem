package tn.esprit.spring.kaddem.repositories;

import tn.esprit.spring.kaddem.entities.Equipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EquipeRepositoryStub implements EquipeRepository {
    private final Map<Integer, Equipe> database = new HashMap<>();
    private int currentId = 1;

    @Override
    public List<Equipe> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public Iterable<Equipe> findAllById(Iterable<Integer> integers) {
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
    public Equipe save(Equipe equipe) {
        if (equipe.getIdEquipe() == null) {
            equipe.setIdEquipe(currentId++);
        }
        database.put(equipe.getIdEquipe(), equipe);
        return equipe;
    }

    @Override
    public <S extends Equipe> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Equipe> findById(Integer id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public void delete(Equipe equipe) {
        database.remove(equipe.getIdEquipe());
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Equipe> entities) {

    }

    @Override
    public void deleteAll() {

    }

    // Implement any other required methods for EquipeRepository interface
}
