package app.service.outputports;

import app.domain.Tutor;
import java.util.List;

public interface TutorRepository {

    Tutor save(Tutor tutor);

    List<Tutor> findAll();

    Tutor findById(int id);

    Tutor update(Tutor tutor);

    boolean deleteById(int id);
}
