package app.service.outputports;

import app.domain.Pets;
import java.util.List;

public interface PetsRepository {

    Pets save(Pets pet);

    List<Pets> findAll();

    Pets findById(int id);

    Pets update(Pets pet);

    boolean deleteById(int id);
}