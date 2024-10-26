package tn.esprit.spring.kaddem.repositories;

import tn.esprit.spring.kaddem.entities.Contrat;

import java.util.*;

public class ContratRepositoryStub implements ContratRepository {
    private final Map<Integer, Contrat> database = new HashMap<>();
    private int currentId = 1;

    @Override
    public Integer getnbContratsValides(Date startDate, Date endDate) {
        return null;
    }

    @Override
    public List<Contrat> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public Iterable<Contrat> findAllById(Iterable<Integer> integers) {
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
    public Contrat findByIdContrat(Integer idContrat) {
        return null;
    }

    @Override
    public Contrat save(Contrat contrat) {
        if (contrat.getIdContrat() == null) {
            contrat.setIdContrat(currentId++);
        }
        database.put(contrat.getIdContrat(), contrat);
        return contrat;
    }

    @Override
    public <S extends Contrat> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Contrat> findById(Integer id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public void delete(Contrat contrat) {
        database.remove(contrat.getIdContrat());
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Contrat> entities) {

    }

    @Override
    public void deleteAll() {

    }

    // Implement any other required methods for ContratRepository interface
}
